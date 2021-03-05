package writer;

import game.Consumer;
import game.Distributor;
import game.Producer;

import java.util.ArrayList;

public class Write {
    private ArrayList<OutputConsumer> consumers = new ArrayList<>();
    private ArrayList<OutputDistributor> distributors = new ArrayList<>();
    private ArrayList<OutputProducer> energyProducers = new ArrayList<>();

    /**
     * Se iterezeaza prin consumatori si distribuitori si se creeaza clasele
     * pentru output cu informatiile corespunzatoare.
     *
     * @param finalConsumers    toti consumatorii de la finalul jocului
     * @param finalDistributors distribuitorii
     */
    public final void writeOutput(final ArrayList<Consumer> finalConsumers,
                                  final ArrayList<Distributor> finalDistributors,
                                  final ArrayList<Producer> finalProducers) {
        for (Consumer consumer : finalConsumers) {
            consumers.add(new OutputConsumer(consumer.getId(),
                    consumer.isBankrupt(), consumer.getBuget()));
        }
        for (Distributor distributor : finalDistributors) {
            distributors.add(new OutputDistributor(distributor.getId(),
                    distributor.getBuget(), distributor.isBankrupt(), distributor.getClients(),
                    distributor.getEnergyNeededKW(), distributor.getContract(),
                    distributor.getProducerStrategy()));
        }
        for (Producer producer : finalProducers) {
            energyProducers.add(new OutputProducer(producer.getId(), producer.getEnergyType(),
                    producer.getMaxDistributors(), producer.getPriceKW(),
                    producer.getEnergyPerDistributor(), producer.getMonthlyStats()));
        }
    }

    public final ArrayList<OutputConsumer> getConsumers() {
        return consumers;
    }

    public final void setConsumers(final ArrayList<OutputConsumer> consumers) {
        this.consumers = consumers;
    }

    public final ArrayList<OutputDistributor> getDistributors() {
        return distributors;
    }

    public final void setDistributors(final ArrayList<OutputDistributor> distributors) {
        this.distributors = distributors;
    }

    public final ArrayList<OutputProducer> getEnergyProducers() {
        return energyProducers;
    }

    public final void setEnergyProducers(final ArrayList<OutputProducer> energyProducers) {
        this.energyProducers = energyProducers;
    }
}
