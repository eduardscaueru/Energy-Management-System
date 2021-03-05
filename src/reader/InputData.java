package reader;

import java.util.ArrayList;

public class InputData {
    private ArrayList<InputConsumer> consumers;
    private ArrayList<InputDistributor> distributors;
    private ArrayList<InputProducer> producers;

    public final ArrayList<InputProducer> getProducers() {
        return producers;
    }

    public final void setProducers(final ArrayList<InputProducer> producers) {
        this.producers = producers;
    }

    public final ArrayList<InputConsumer> getConsumers() {
        return consumers;
    }

    public final void setConsumers(final ArrayList<InputConsumer> consumers) {
        this.consumers = consumers;
    }

    public final ArrayList<InputDistributor> getDistributors() {
        return distributors;
    }

    public final void setDistributors(final ArrayList<InputDistributor> distributors) {
        this.distributors = distributors;
    }
}
