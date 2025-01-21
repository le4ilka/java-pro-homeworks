import java.util.Set;

public interface Atms {
    boolean takeBanknotes(Set<Banknote> banknotes);
    boolean giveBanknotes(int askingSum);
    int moneyAmountInfo();
}
