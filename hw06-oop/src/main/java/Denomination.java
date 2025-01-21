public enum Denomination {
    TEN(10),
    FIFTY(50),
    HUNDRED(100),
    FIVE_HUNDRED(500),
    THOUSAND(1000),
    FIVE_THOUSAND(5000);

    private int denominationValue;

    Denomination(int denominationVal) {
        this.denominationValue = denominationVal;
    }

    public int getDenominationValue() {
        return denominationValue;
    }
}
