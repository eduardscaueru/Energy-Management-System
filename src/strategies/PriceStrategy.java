package strategies;

import game.Distributor;
import game.Producer;

import java.util.ArrayList;

public class PriceStrategy implements EnergyChoiceStrategy {
    @Override
    public final void chooseProducer(final Distributor distributor,
                               final ArrayList<Producer> producers) {
        ArrayList<Producer> sortedProducers = new ArrayList<>(producers);

        sortedProducers.sort((o1, o2) -> {
            if (o1.getPriceKW() != o2.getPriceKW()) {
                return Double.compare(o1.getPriceKW(), o2.getPriceKW());
            } else {
                return Integer.compare(o2.getEnergyPerDistributor(),
                        o1.getEnergyPerDistributor());
            }
        });

        distributor.pickProducer(sortedProducers);
    }
}
