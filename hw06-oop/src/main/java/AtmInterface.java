import java.util.Set;

public interface AtmInterface {
    boolean takeBanknotes(Set<Banknote> banknotes);
    boolean giveBanknotes(int askingSum);
    int moneyAmountInfo();
}
