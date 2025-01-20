public class NoBanknotesException extends Exception{
    private int askingSum;
    private int askingSumFromATM;
    public NoBanknotesException(int askingSum, int askingSumFromATM){
        this.askingSum = askingSum;
        this.askingSumFromATM = askingSumFromATM;
    }

    public int getAskingSum() {
        return askingSum;
    }

    public int getAskingSumFromATM() {
        return askingSumFromATM;
    }
}
