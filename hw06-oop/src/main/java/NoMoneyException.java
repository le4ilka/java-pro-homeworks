public class NoMoneyException extends Exception {
    private int countSum;
    private int requestSum;

    public NoMoneyException(int countSum, int requestSum) {
        super("No money available (requested: %s, available in ATM: %s)".formatted(requestSum, countSum));
        this.countSum = countSum;
        this.requestSum = requestSum;
    }

    public int getCountSum(){
        return countSum;
    }

    public int getRequestSum() {
        return requestSum;
    }
}
