package reader;

import java.util.ArrayList;

public class InputGame {
    private int numberOfTurns;
    private InputData initialData;
    private ArrayList<InputMonthlyUpdates> monthlyUpdates;

    public final int getNumberOfTurns() {
        return numberOfTurns;
    }

    public final void setNumberOfTurns(final int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public final InputData getInitialData() {
        return initialData;
    }

    public final void setInitialData(final InputData initialData) {
        this.initialData = initialData;
    }

    public final ArrayList<InputMonthlyUpdates> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    public final void setMonthlyUpdates(final ArrayList<InputMonthlyUpdates> monthlyUpdates) {
        this.monthlyUpdates = monthlyUpdates;
    }
}
