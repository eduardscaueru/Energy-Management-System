package writer;

public class OutputConsumer {
    private int id;
    private boolean isBankrupt;
    private int budget;

    public OutputConsumer(final int id,
                          final boolean isBankrupt,
                          final int budget) {
        this.id = id;
        this.isBankrupt = isBankrupt;
        this.budget = budget;
    }

    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final boolean getIsBankrupt() {
        return isBankrupt;
    }

    public final void setIsBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public final int getBudget() {
        return budget;
    }

    public final void setBudget(final int budget) {
        this.budget = budget;
    }
}
