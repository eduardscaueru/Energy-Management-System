package game;

import java.util.ArrayList;

public class MonthlyStat {
    private int month;
    private ArrayList<Integer> distributorsIds = new ArrayList<>();

    public MonthlyStat(final int month,
                       final ArrayList<Distributor> distributors) {
        this.month = month;
        for (Distributor distributor : distributors) {
            distributorsIds.add(distributor.getId());
        }
        distributorsIds.sort((Integer::compareTo));
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
