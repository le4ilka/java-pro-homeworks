public interface CellInterface {
    void add(Banknote banknote);

    void del();

    Denomination getDenomination();

    int getDenominationValue();

    int getBanknotesCount();
}
