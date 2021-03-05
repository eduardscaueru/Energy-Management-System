package writer;

import game.MonthlyStat;

import java.util.ArrayList;

public class OutputProducer {
    private int id;
    private int maxDistributors;
    private double priceKW;
    private String energyType;
    private int energyPerDistributor;
    private ArrayList<MonthlyStat> monthlyStats;

    public OutputProducer(final int id, final String energyType, final int maxDistributors,
                          final double priceKW, final int energyPerDistributor,
                          final ArrayList<MonthlyStat> monthlyStats) {
        this.id = id;
        this.energyType = energyType;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
        this.monthlyStats = monthlyStats;
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

    public final ArrayList<MonthlyStat> getMonthlyStats() {
        return monthlyStats;
    }

    public final void setMonthlyStats(final ArrayList<MonthlyStat> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }
}
