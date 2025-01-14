import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Atm implements AtmInterface {
    private static final Logger log = LoggerFactory.getLogger(Atm.class);
    public Set<Cell> cells = new TreeSet<>(Comparator.reverseOrder());
    private int sum;

    private boolean isDenominationCellExists(int denominationValue) {
        for (Cell cell : cells) {
            int itDenominationValue = cell.getDenominationValue();
            if (itDenominationValue == denominationValue) {
                return true;
            }
        }
        return false;
    }
    public boolean takeBanknotes(Set<Banknote> banknotes) {
        log.info("ENTERING {} BANKNOTES: {}", banknotes.size(), banknotes);
        for (Banknote one : banknotes) {
            if (!isDenominationCellExists(one.getDenominationValue())) {
                cells.add(new Cell(one.getDenomination()));
                log.debug("New cell {} added to ATM", one.getDenominationValue());
            }
            for (Cell c: cells) {
                if (one.getDenomination() == c.getDenomination()){
                    c.add(one);
                }
            }
        }
        log.debug("CELLS IN ATM: \n{}", cells);
        countSum();
        return true;
    }
    private boolean requestBanknotes(int askingSum) {
        int askingSumFromATM = 0;
        log.info("Client request to give {} (in ATM - {})", askingSum, this.sum);
        if (this.sum < askingSum) {
            log.info("No so much money in atm. In ATM = {}, client request = {}", this.sum, askingSum);
            return false;
        }
        for (Cell entry : cells) {
            int tempRes = askingSum / entry.getDenominationValue();
            if (askingSumFromATM < askingSum && tempRes >= 1) {
                int loops = Math.min(tempRes, entry.getBanknotesCount());
                for (int i = 0; i < loops; i++) {
                    loops--;
                    askingSumFromATM += entry.getDenominationValue();
                }
            }
            if (askingSumFromATM == askingSum)
                break;
        }
        if (askingSumFromATM != askingSum) {
            log.info("Can't give {}, there is no such banknotes. Can give {}.", askingSum, askingSumFromATM);
            return false;
        }
        return true;
    }
    public boolean giveBanknotes(int askingSum) {
        if (!requestBanknotes(askingSum))
            return false;
        int askingSumFromATM = 0;
        for (Cell entry : cells) {
            int tempRes = askingSum / entry.getDenominationValue();
            if (askingSumFromATM < askingSum && tempRes >= 1) {
                int loops = Math.min(tempRes, entry.getBanknotesCount());
                for (int i = 0; i < loops; i++) {
                    loops--;
                    entry.del();
                    askingSumFromATM += entry.getDenomination().getDenominationValue();
                }
            }
        }
        log.info("ATM gave {} to client", askingSum);
        countSum();
        return true;
    }
    private int countSum() {
        this.sum = 0;
        for (Cell entry : cells) {
            if (entry.getBanknotesCount() == 0) {
                continue;
            }
            sum += entry.getBanknotesCount() * entry.getDenominationValue();
        }
        log.info("MONEY AMOUNT: {}", sum);
        return sum;
    }
    public int moneyAmountInfo() {
        log.info("MONEY AMOUNT INFO: {}", sum);
        return sum;
    }

}