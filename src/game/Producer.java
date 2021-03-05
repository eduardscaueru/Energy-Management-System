package game;

import reader.InputProducer;

import java.util.ArrayList;
import java.util.Observable;

public class Producer extends Observable {
    private int id;
    private String energyType;
    private int maxDistributors;
    private double priceKW;
    private int energyPerDistributor;
    private int numDistributors;
    private ArrayList<Distributor> distributors = new ArrayList<>();
    private ArrayList<MonthlyStat> monthlyStats = new ArrayList<>();

    public Producer(final InputProducer inputProducer) {
        this.id = inputProducer.getId();
        this.energyType = inputProducer.getEnergyType();
        this.maxDistributors = inputProducer.getMaxDistributors();
        this.priceKW = inputProducer.getPriceKW();
        this.energyPerDistributor = inputProducer.getEnergyPerDistributor();
    }

    public final ArrayList<MonthlyStat> getMonthlyStats() {
        return monthlyStats;
    }

    public final void setMonthlyStats(final ArrayList<MonthlyStat> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }

    public final int getNumDistributors() {
        return numDistributors;
    }

    public final void setNumDistributors(final int numDistributors) {
        this.numDistributors = numDistributors;
    }

    public final ArrayList<Distributor> getDistributors() {
        return distributors;
    }

    public final void setDistributors(final ArrayList<Distributor> distributors) {
        this.distributors = distributors;
    }

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

    /**
     * Setarea tuturor distribuitorilor de a-si actualiza producatorii.
     */
    public final void notifyDistributors() {
        setChanged();
        notifyObservers();
    }
}
