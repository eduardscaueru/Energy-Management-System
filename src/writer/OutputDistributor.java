package writer;

import game.Consumer;

import java.util.ArrayList;
import java.util.List;

public class OutputDistributor {
    private int id;
    private int energyNeededKW;
    private int contractCost;
    private int budget;
    private String producerStrategy;
    private boolean isBankrupt;
    private ArrayList<Contract> contracts = new ArrayList<>();

    public OutputDistributor(final int id, final int budget, final boolean bankrupt,
                             final List<Consumer> clients, final int energyNeededKW,
                             final int contract, final String producerStrategy) {
        this.id = id;
        this.budget = budget;
        this.isBankrupt = bankrupt;
        for (Consumer consumer : clients) {
            contracts.add(new Contract(consumer.getId(), consumer.getMonthlyRate(),
                    consumer.getContractMonths()));
        }
        this.energyNeededKW = energyNeededKW;
        this.contractCost = contract;
        this.producerStrategy = producerStrategy;
    }

    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final int getBudget() {
        return budget;
    }

    public final void setBudget(final int budget) {
        this.budget = budget;
    }

    public final boolean getIsBankrupt() {
        return isBankrupt;
    }

    public final void setIsBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public final ArrayList<Contract> getContracts() {
        return contracts;
    }

    public final void setContracts(final ArrayList<Contract> contracts) {
        this.contracts = contracts;
    }

    public final int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public final void setEnergyNeededKW(final int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public final int getContractCost() {
        return contractCost;
    }

    public final void setContractCost(final int contractCost) {
        this.contractCost = contractCost;
    }

    public final String getProducerStrategy() {
        return producerStrategy;
    }

    public final void setProducerStrategy(final String producerStrategy) {
        this.producerStrategy = producerStrategy;
    }
}
