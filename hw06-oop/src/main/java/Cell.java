import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class Cell implements Cells, Comparable<Cell>{
    private static final Logger log = LoggerFactory.getLogger(Cell.class);
    private final Denomination denomination;
    private final Set<Banknote> banknotes;

    public Cell(Denomination denomination){
        this.denomination = denomination;
        this.banknotes = new HashSet<>();
    }

    public void add(Banknote banknote) {

        if (banknote.getDenomination() != this.denomination){
            log.info("Banknote doesn't fit by denomination. Cell denomination {}, banknote denomination is - {}", this.denomination, banknote.getDenomination());
            return;
        }
        banknotes.add(banknote);
    }

    public void del() {
        banknotes.remove(banknotes.toArray()[0]);
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public int getDenominationValue() {
        return denomination.getDenominationValue();
    }

    public int getBanknotesCount() {
        return banknotes.size();
    }

    @Override
    public String toString() {
        return "Cell " + this.getDenominationValue() + ": - " + this.getBanknotesCount() + "\n";
    }

    @Override
    public int compareTo(Cell o) {
        Integer currentDenValue = this.getDenominationValue();
        return currentDenValue.compareTo(o.getDenominationValue()) ;
    }
}
