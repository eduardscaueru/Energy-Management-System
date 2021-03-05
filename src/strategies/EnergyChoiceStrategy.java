package strategies;

import game.Distributor;
import game.Producer;
import java.util.ArrayList;

public interface EnergyChoiceStrategy {
    /**
     * Metoda de sortare a producatorilor pentru ca distribuitorul sa-si recalculeze producatorii.
     *
     * @param distributor   distribuitorul care trebuie sa-si regaseasca producatori
     * @param producers     toti producatorii
     */
    void chooseProducer(Distributor distributor, ArrayList<Producer> producers);
}
