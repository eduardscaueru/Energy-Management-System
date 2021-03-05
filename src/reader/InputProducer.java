package reader;

public class InputProducer {
    private int id;
    private String energyType;
    private int maxDistributors;
    private double priceKW;
    private int energyPerDistributor;

    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final String getEnergyType() {
        return energyType;
    }

    public final void setEnergyType(final String energyType) {
        this.energyType = energyType;
    }

    public final int getMaxDistributors() {
        return maxDistributors;
    }

    public final void setMaxDistributors(final int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public final double getPriceKW() {
        return priceKW;
    }

    public final void setPriceKW(final double priceKW) {
        this.priceKW = priceKW;
    }

    public final int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public final void setEnergyPerDistributor(final int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }
}
