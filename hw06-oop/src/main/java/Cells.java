public interface Cells {
    void add(Banknote banknote);

    void del();

    Denomination getDenomination();

    int getDenominationValue();

    int getBanknotesCount();
}
