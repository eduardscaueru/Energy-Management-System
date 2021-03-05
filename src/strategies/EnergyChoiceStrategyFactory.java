package strategies;

public class EnergyChoiceStrategyFactory {
    /**
     *
     *
     * @param strategyType numele strategiei
     * @return
     */
    public final EnergyChoiceStrategy createStrategy(final String strategyType) {
        if (strategyType.equals(EnergyChoiceStrategyType.GREEN.toString())) {
            return new GreenStrategy();
        } else {
            if (strategyType.equals(EnergyChoiceStrategyType.PRICE.toString())) {
                return new PriceStrategy();
            } else {
                return new QuantityStrategy();
            }
        }
    }
}
