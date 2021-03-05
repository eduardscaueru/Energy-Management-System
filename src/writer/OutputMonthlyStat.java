package writer;

import game.Distributor;

import java.util.ArrayList;

public class OutputMonthlyStat {
    private int month;
    private ArrayList<Integer> distributorsIds = new ArrayList<>();

    public OutputMonthlyStat(int month, ArrayList<Distributor> distributors) {
        this.month = month;
        for (Distributor distributor : distributors) {
            distributorsIds.add(distributor.getId());
        }
    }

    public final int getMonth() {
        return month;
    }

    public final void setMonth(final int month) {
        this.month = month;
    }

    public final ArrayList<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    public final void setDistributorsIds(final ArrayList<Integer> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }
}
