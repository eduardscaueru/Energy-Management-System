package game;

public final class Factory {
    private Factory() {
    }

    /**
     * Returneaza instante de diferite tipuri pentru a popula clasele create cu datele din input.
     *
     * @param entityType                tipul de entitate pentru a rula factory
     * @param id                        id-ul consumatorului sau distributorului
     * @param contractLength            durata contractului in luni
     * @param initialBudget             bugetul initial
     * @param initialInfrastructureCost costul infrastructurii initiale
     * @param monthlyIncome             suma contractului platita in fiecare luna de consumator
     * @return                          un obiect de tipul Consumer sau Distributor initilizat
     * cu toate campurile citite din fisier
     */
    public static Entity createEntity(final String entityType, final int id,
                                      final int contractLength, final int initialBudget,
                                      final int initialInfrastructureCost,
                                      final int monthlyIncome,
                                      final String producerStrategy,
                                      final int energyNeededKW) {
        if (entityType.equals("consumer")) {
            return new Consumer(id, initialBudget, monthlyIncome);
        } else {
            return new Distributor(id, contractLength, initialBudget,
                    initialInfrastructureCost,
                    producerStrategy, energyNeededKW);
        }
    }
}
