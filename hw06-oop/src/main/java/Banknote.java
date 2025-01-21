import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Banknote {
    private final Denomination denomination;
    private final int number;

    public Banknote(Denomination denomination, int number) {
        this.denomination = denomination;
        this.number = number;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public int getDenominationValue() {
        return denomination.getDenominationValue();
    }

    public String toString(){
        return "Banknote " + getDenomination().getDenominationValue();
    }
}
