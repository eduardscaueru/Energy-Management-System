package reader;

import java.util.ArrayList;

public class InputMonthlyUpdates {
    private ArrayList<InputConsumer> newConsumers;
    private ArrayList<InputDistributorChanges> distributorChanges;
    private ArrayList<InputProducerChanges> producerChanges;

    public final ArrayList<InputProducerChanges> getProducerChanges() {
        return producerChanges;
    }

    public final void setProducerChanges(final ArrayList<InputProducerChanges> producerChanges) {
        this.producerChanges = producerChanges;
    }

    public final ArrayList<InputConsumer> getNewConsumers() {
        return newConsumers;
    }

    public final void setNewConsumers(final ArrayList<InputConsumer> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public final ArrayList<InputDistributorChanges> getDistributorChanges() {
        return distributorChanges;
    }

    public final void setDistributorChanges(final ArrayList<InputDistributorChanges>
                                                    distributorChanges) {
        this.distributorChanges = distributorChanges;
    }
}
