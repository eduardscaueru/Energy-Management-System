package game;

abstract class Entity {
    private int id;
    private int buget;
    private boolean isBankrupt;

    public final boolean isBankrupt() {
        return isBankrupt;
    }

    public final void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    Entity(final int id, final int initialBudget) {
        this.id = id;
        this.buget = initialBudget;
        isBankrupt = false;
    }

    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final int getBuget() {
        return buget;
    }

    public final void setBuget(final int buget) {
        this.buget = buget;
    }
}
