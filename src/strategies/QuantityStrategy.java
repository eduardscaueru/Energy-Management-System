package strategies;

import game.Distributor;
import game.Producer;

import java.util.ArrayList;

public class QuantityStrategy implements EnergyChoiceStrategy {
    @Override
    public final void chooseProducer(final Distributor distributor,
                               final ArrayList<Producer> producers) {
        ArrayList<Producer> sortedProducers = new ArrayList<>(producers);

        sortedProducers.sort(((o1, o2) -> o2.getEnergyPerDistributor()
                - o1.getEnergyPerDistributor()));

        distributor.pickProducer(sortedProducers);
    }
}
