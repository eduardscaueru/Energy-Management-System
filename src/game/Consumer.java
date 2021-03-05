package game;

import java.util.ArrayList;

public class Consumer extends Entity {
    private static final int MAX = 99999;
    private static final double PERCENTAGE = 1.2;

    private int distributorId;
    private int income;
    private Distributor distributor;
    private int contractMonths;
    private int monthlyRate;
    private int oldInvoice;
    private Distributor oldDistributor;
    private boolean postpone;

    public Consumer(final int id, final int initialBudget, final int monthlyIncome) {
        super(id, initialBudget);
        this.income = monthlyIncome;
        this.distributorId = -1;
        this.postpone = false;
    }

    public final boolean isPostpone() {
        return postpone;
    }

    public final void setPostpone(final boolean postpone) {
        this.postpone = postpone;
    }

    public final int getOldInvoice() {
        return oldInvoice;
    }

    public final void setOldInvoice(final int oldInvoice) {
        this.oldInvoice = oldInvoice;
    }

    public final int getMonthlyRate() {
        return monthlyRate;
    }

    public final void setMonthlyRate(final int monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public final int getIncome() {
        return income;
    }

    public final void setIncome(final int income) {
        this.income = income;
    }

    public final int getContractMonths() {
        return contractMonths;
    }

    public final void setContractMonths(final int contractMonths) {
        this.contractMonths = contractMonths;
    }

    public final Distributor getDistributor() {
        return distributor;
    }

    public final void setDistributor(final Distributor distributor) {
        this.distributor = distributor;
    }

    public final int getDistributorId() {
        return distributorId;
    }

    public final void setDistributorId(final int distributorId) {
        this.distributorId = distributorId;
    }

    /**
     * Asigneaza comsumatorului distribuitorul cu cel mai mic contract si aduga consumatorul
     * curent in lista de clienti a distribuitorului gasit.
     *
     * @param distributors toti distributorii
     */
    public final void findDistributor(final ArrayList<Distributor> distributors) {
        int min = MAX;
        int idDistributor = 0;

        for (Distributor d : distributors) {
            if (!d.isBankrupt()) {
                if (d.getContract() < min) {
                    min = d.getContract();
                    idDistributor = d.getId();
                }
            }
        }

        if (min != MAX) {
            distributorId = idDistributor;
            distributor = distributors.get(idDistributor);
            contractMonths = distributor.getContractLength();
            monthlyRate = min;
            distributors.get(idDistributor).getClients().add(this);
        }
    }

    /**
     * Consumatorul primeste salariul si isi plateste cheltuielile. De asemenea,
     * se decide daca acesta va amana sau nu factura curenta platind-o mai tarziu
     * fie tot aceluiasi distribuitor, fie o parte vechiului distribuitor si cealalta
     * celui nou.
     */
    public final void update() {
        if (!postpone) {
            setBuget(getBuget() + income - monthlyRate);
            if (getBuget() < 0) {
                setBuget(getBuget() + monthlyRate);
                oldInvoice = monthlyRate;
                oldDistributor = distributor;
                postpone = true;
            } else {
                distributor.setBuget(distributor.getBuget() + monthlyRate);
            }
        } else {
            if (oldDistributor == distributor) {
                setBuget(getBuget() + income
                        - (int) Math.round(Math.floor(PERCENTAGE * oldInvoice)) - monthlyRate);
                if (getBuget() < 0) {
                    setBuget(getBuget() + (int) Math.round(Math.floor(PERCENTAGE * oldInvoice))
                            + monthlyRate);
                    setBankrupt(true);
                } else {
                    postpone = false;
                    distributor.setBuget(distributor.getBuget() + monthlyRate);
                    oldDistributor.setBuget(oldDistributor.getBuget()
                            + (int) Math.round(Math.floor(PERCENTAGE * oldInvoice)));
                }
            } else {
                setBuget(getBuget() + income
                        - (int) Math.round(Math.floor(PERCENTAGE * oldInvoice)));
                if (getBuget() < 0) {
                    setBuget(getBuget() + (int) Math.round(Math.floor(PERCENTAGE * oldInvoice)));
                    setBankrupt(true);
                } else {
                    oldDistributor.setBuget(oldDistributor.getBuget()
                            + (int) Math.round(Math.floor(PERCENTAGE * oldInvoice)));
                    oldInvoice = monthlyRate;
                    oldDistributor = distributor;
                    postpone = true;
                }
            }
        }
        contractMonths -= 1;
    }
}
