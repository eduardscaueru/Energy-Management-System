package game;

import strategies.EnergyChoiceStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Distributor extends Entity implements Observer {
    private static final double PERCENTAGE = 0.2;
    private static final int NUMBER = 10;

    private int contractLength;
    private int infrastructureCost;
    private int productionCost;
    private int profit;
    private int contract;
    private int energyNeededKW;
    private String producerStrategy;
    private EnergyChoiceStrategy strategy;
    private List<Consumer> clients = new ArrayList<>();
    private ArrayList<Producer> producers = new ArrayList<>();
    private boolean needUpdate;

    public Distributor(final int id, final int contractLength, final int initialBudget,
                       final int initialInfrastructureCost,
                       final String producerStrategy, final int energyNeededKW) {
        super(id, initialBudget);
        this.contractLength = contractLength;
        this.infrastructureCost = initialInfrastructureCost;
        this.producerStrategy = producerStrategy;
        this.energyNeededKW = energyNeededKW;
        this.needUpdate = true;
        setProfit();
    }

    public final EnergyChoiceStrategy getStrategy() {
        return strategy;
    }

    public final void setStrategy(final EnergyChoiceStrategy strategy) {
        this.strategy = strategy;
    }

    public final boolean isNeedUpdate() {
        return needUpdate;
    }

    public final void setNeedUpdate(final boolean needUpdate) {
        this.needUpdate = needUpdate;
    }

    public final ArrayList<Producer> getProducers() {
        return producers;
    }

    public final void setProducers(final ArrayList<Producer> producers) {
        this.producers = producers;
    }

    public final int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public final void setEnergyNeededKW(final int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public final String getProducerStrategy() {
        return producerStrategy;
    }

    public final void setProducerStrategy(final String producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public final void setContract(final int contract) {
        this.contract = contract;
    }

    public final int getContractLength() {
        return contractLength;
    }

    public final void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    public final int getInfrastructureCost() {
        return infrastructureCost;
    }

    public final void setInfrastructureCost(final int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public final int getProductionCost() {
        return productionCost;
    }

    public final void setProductionCost(final int productionCost) {
        this.productionCost = productionCost;
    }

    public final List<Consumer> getClients() {
        return clients;
    }

    public final void setClients(final List<Consumer> clients) {
        this.clients = clients;
    }

    public final int getProfit() {
        return profit;
    }

    /**
     * Calculeaza profitul pe baza costului de productie care poate fi actualizat.
     */
    public final void setProfit() {
        profit = (int) Math.round(Math.floor(PERCENTAGE * this.productionCost));
    }

    public final void setProfit(final int profit) {
        this.profit = profit;
    }

    public final int getContract() {
        return contract;
    }

    /**
     * Calcularea costului de productie
     */
    public final void calculateProductionCost() {
        double x = 0;
        for (Producer producer : producers) {
            x += producer.getEnergyPerDistributor() * producer.getPriceKW();
        }
        productionCost = (int) Math.round(Math.floor((x / NUMBER)));
        setProfit();
    }

    /**
     * Calcularea contractului care se poate actualizat daca,
     * costurile se modifica.
     *
     * @return pretul contractului
     */
    public final int calculateContract() {
        if (clients.size() == 0) {
            return infrastructureCost + productionCost + profit;
        } else {
            return (int) (Math.round(Math.floor((float) infrastructureCost / clients.size())
                    + productionCost + profit));
        }
    }

    /**
     * Aplicarea strategiei de a reactualiza producatorii.
     *
     * @param strategyy     strategia corespunzatoare
     * @param allProducers  toti producatorii
     */
    public final void findProducer(final EnergyChoiceStrategy strategyy,
                                   final ArrayList<Producer> allProducers) {
        this.strategy = strategyy;
        strategy.chooseProducer(this, allProducers);
    }

    /**
     * Distribuitorul isi plateste cheltuielile, sterge din lista de clienti
     * consumatorii faliti si daca acesta da faliment sterge toti clientii din lista.
     *
     * @param consumers consumatorii
     */
    public final void updateCosts(final ArrayList<Consumer> consumers) {
        List<Consumer> delete = new ArrayList<>();

        setBuget(getBuget() - infrastructureCost - productionCost * clients.size());

        for (Consumer client : clients) {
            if (client.isBankrupt()) {
                delete.add(client);
            }
        }

        for (Consumer bankruptClient : delete) {
            clients.remove(bankruptClient);
        }

        if (getBuget() < 0) {
            setBankrupt(true);
            for (Consumer consumer : consumers) {
                if (consumer.getDistributorId() == getId()) {
                    clients.remove(consumer);
                }
            }
        }
    }

    /**
     * Metoda de a notifica distribuitorii atunci cand se schimba energia producatorilor.
     *
     * @param o   producatorul care se modifica
     * @param arg toti producatorii
     */
    @Override
    public final void update(final Observable o, final Object arg) {
        needUpdate = true;
    }

    /**
     * Distributorul realege producatorii
     *
     * @param sortedProducers producatorii sortati dupa strategia distributorului
     */
    public final void pickProducer(final ArrayList<Producer> sortedProducers) {
        this.getProducers().clear();
        for (Producer producer : sortedProducers) {
            producer.getDistributors().remove(this);
            producer.deleteObserver(this);
        }

        int sum = 0;
        for (Producer producer : sortedProducers) {
            if (sum < this.getEnergyNeededKW()
                    && producer.getDistributors().size() < producer.getMaxDistributors()) {
                sum += producer.getEnergyPerDistributor();
                this.getProducers().add(producer);
                producer.getDistributors().add(this);
                producer.addObserver(this);
            }
        }
    }
}
