import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Atm implements Atms {
    private static final Logger log = LoggerFactory.getLogger(Atm.class);
    public Set<Cell> cells = new TreeSet<>(Comparator.reverseOrder());

    public boolean takeBanknotes(Set<Banknote> banknotes){
        log.info("ENTERING {} BANKNOTES: {}", banknotes.size(), banknotes);
        for (Banknote one : banknotes) {
            if (!isDenominationCellExists(one.getDenominationValue())) {
                cells.add(new Cell(one.getDenomination()));
                log.debug("New cell {} added to ATM", one.getDenominationValue());
            }
            for (Cell c : cells) {
                if (one.getDenomination() == c.getDenomination()) {
                    c.add(one);
                }
            }
        }
        log.debug("CELLS IN ATM: \n{}", cells);
        countSum();
        return true;
    }

    public boolean giveBanknotes(int askingSum) {
        try {
            requestBanknotes(askingSum);
        } catch (NoMoneyException e) {
            log.info("No so much money in atm. In ATM = {}, client request = {}", countSum(), askingSum);
            return false;
        } catch (NoBanknotesException e){
            log.info("Can't give {}, there is no such banknotes. Can give {}.", askingSum, e.getAskingSumFromATM());
            return false;
        }
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

    public int moneyAmountInfo() {
        log.info("MONEY AMOUNT INFO: {}", countSum());
        return countSum();
    }

    private boolean isDenominationCellExists(int denominationValue) {
        for (Cell cell : cells) {
            int itDenominationValue = cell.getDenominationValue();
            if (itDenominationValue == denominationValue) {
                return true;
            }
        }
        return false;
    }

    private void requestBanknotes(int askingSum) throws NoMoneyException, NoBanknotesException {
        int askingSumFromATM = 0;
        log.info("Client request to give {} (in ATM - {})", askingSum, countSum());
        if (countSum() < askingSum) {
            throw new NoMoneyException(countSum(), askingSum);
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
            throw new NoBanknotesException(askingSum, askingSumFromATM);
        }
    }

    private int countSum() {
        int sum = 0;
        for (Cell entry : cells) {
            if (entry.getBanknotesCount() == 0) {
                continue;
            }
            sum += entry.getBanknotesCount() * entry.getDenominationValue();
        }
        log.info("MONEY AMOUNT: {}", sum);
        return sum;
    }
}