package game;

import reader.InputProducer;
import reader.InputProducerChanges;
import reader.InputGame;
import reader.InputConsumer;
import reader.InputMonthlyUpdates;
import reader.InputDistributor;
import reader.InputDistributorChanges;
import reader.InputData;
import strategies.EnergyChoiceStrategy;
import strategies.EnergyChoiceStrategyFactory;
import writer.Write;
import java.util.ArrayList;

public final class Game {
    private static Game instance = new Game();
    private ArrayList<Consumer> consumers;
    private ArrayList<Distributor> distributors;
    private ArrayList<InputMonthlyUpdates> monthlyUpdates;
    private ArrayList<Producer> producers;
    private int numberOfTurns;

    public ArrayList<Producer> getProducers() {
        return producers;
    }

    public void setProducers(ArrayList<Producer> producers) {
        this.producers = producers;
    }

    public ArrayList<InputMonthlyUpdates> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    public void setMonthlyUpdates(final ArrayList<InputMonthlyUpdates> monthlyUpdates) {
        this.monthlyUpdates = monthlyUpdates;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(final int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public ArrayList<Consumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(final ArrayList<Consumer> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<Distributor> getDistributors() {
        return distributors;
    }

    public void setDistributors(final ArrayList<Distributor> distributors) {
        this.distributors = distributors;
    }

    private Game() {
    }

    public static Game getInstance() {
        return instance;
    }

    /**
     * Populeaza listele de consumatori,de distribuitori si de update-uri lunare cu informatiile
     * specifice fiecaruia.
     *
     * @param inputGame datele din input
     */
    public void fillInputData(final InputGame inputGame) {
        InputData initialData = inputGame.getInitialData();

        consumers = new ArrayList<>();
        distributors = new ArrayList<>();
        producers = new ArrayList<>();
        monthlyUpdates = new ArrayList<>();
        monthlyUpdates.addAll(inputGame.getMonthlyUpdates());
        numberOfTurns = inputGame.getNumberOfTurns();

        for (InputConsumer inputConsumer : initialData.getConsumers()) {
            consumers.add((Consumer) Factory.createEntity("consumer", inputConsumer.getId(), 0,
                    inputConsumer.getInitialBudget(), 0,
                    inputConsumer.getMonthlyIncome(), "", 0));
        }
        for (InputDistributor inputDistributor : initialData.getDistributors()) {
            distributors.add((Distributor) Factory.createEntity("distributor",
                    inputDistributor.getId(), inputDistributor.getContractLength(),
                    inputDistributor.getInitialBudget(),
                    inputDistributor.getInitialInfrastructureCost(),
                    0,
                    inputDistributor.getProducerStrategy(),
                    inputDistributor.getEnergyNeededKW()));
        }
        for (InputProducer inputProducer : initialData.getProducers()) {
            producers.add(new Producer(inputProducer));
        }
    }

    /**
     * Se face simularea jocului incepand cu runda initiala, iar apoi continuand cu
     * numarul de unde precizate in input.
     *
     * @return Clasa de scriere in fisier cu informatiile pentru output
     */
    public Write start() {
        int bankruptDistributors;

        EnergyChoiceStrategyFactory factory = new EnergyChoiceStrategyFactory();

        for (Distributor distributor : distributors) {
            EnergyChoiceStrategy strategy = factory.createStrategy(
                    distributor.getProducerStrategy());
            distributor.setStrategy(strategy);
            distributor.findProducer(strategy, producers);
            distributor.calculateProductionCost();
            distributor.setContract(distributor.calculateContract());
        }

        for (Consumer consumer : consumers) {
            consumer.findDistributor(distributors);
            consumer.update();
        }

        for (Distributor distributor : distributors) {
            distributor.updateCosts(consumers);
        }

        int i = 0;
        for (InputMonthlyUpdates monthlyUpdate : monthlyUpdates) {
            i++;

            for (InputConsumer consumer : monthlyUpdate.getNewConsumers()) {
                consumers.add((Consumer) Factory.createEntity("consumer", consumer.getId(), 0,
                        consumer.getInitialBudget(), 0,
                        consumer.getMonthlyIncome(), "", 0));
            }

            for (InputDistributorChanges changes : monthlyUpdate.getDistributorChanges()) {
                Distributor d = distributors.get(changes.getId());
                if (!d.isBankrupt()) {
                    d.setInfrastructureCost(changes.getInfrastructureCost());
                }
            }

            for (Distributor distributor : distributors) {
                if (!distributor.isBankrupt()) {
                    distributor.calculateProductionCost();
                    distributor.setContract(distributor.calculateContract());
                }
            }

            for (Consumer consumer : consumers) {
                if (!consumer.isBankrupt()) {
                    if (consumer.getContractMonths() == 0 && consumer.getDistributorId() != -1) {
                        consumer.getDistributor().getClients().remove(consumer);
                        consumer.setDistributorId(-1);
                    }
                }
            }

            for (Consumer consumer : consumers) {
                if (!consumer.isBankrupt()) {
                    if (consumer.getDistributorId() == -1) {
                        consumer.findDistributor(distributors);
                    }
                }
            }

            for (Consumer consumer : consumers) {
                if (!consumer.isBankrupt()) {
                    consumer.update();
                }
            }

            bankruptDistributors = 0;
            for (Distributor distributor : distributors) {
                if (!distributor.isBankrupt()) {
                    distributor.updateCosts(consumers);
                } else {
                    bankruptDistributors += 1;
                }
            }
            if (bankruptDistributors == distributors.size()) {
                break;
            }

            for (InputProducerChanges changes : monthlyUpdate.getProducerChanges()) {
                Producer p = producers.get(changes.getId());
                p.setEnergyPerDistributor(changes.getEnergyPerDistributor());

                p.notifyDistributors();
            }

            for (Distributor distributor : distributors) {
                if (distributor.isNeedUpdate()) {
                    distributor.findProducer(distributor.getStrategy(), producers);
                    distributor.setNeedUpdate(false);
                }
            }

            for (Producer producer : producers) {
                producer.getMonthlyStats().add(new MonthlyStat(i, producer.getDistributors()));
            }
        }

        //Output info
        Write output = new Write();
        output.writeOutput(consumers, distributors, producers);
        return output;
    }
}
