import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BanknoteTest {

    Set<Banknote> wad = new HashSet<>();
    Banknote banknote100 = new Banknote(Denomination.HUNDRED, (int) (Math.random() * 10000));
    Banknote banknote1000 = new Banknote(Denomination.THOUSAND, (int) (Math.random() * 10000));
    Banknote banknote1001 = new Banknote(Denomination.THOUSAND, (int) (Math.random() * 10000));
    Banknote banknote101 = new Banknote(Denomination.HUNDRED, (int) (Math.random() * 10000));
    Banknote banknote5000 = new Banknote(Denomination.FIVE_THOUSAND, (int) (Math.random() * 10000));
    Banknote banknote10 = new Banknote(Denomination.TEN, (int) (Math.random() * 10000));

    @BeforeEach
    void createBanknotes() {
        wad.add(banknote101);
        wad.add(banknote101);
        wad.add(banknote100);
        wad.add(banknote1000);
        wad.add(banknote1001);
        wad.add(banknote5000);
        wad.add(banknote10);
    }

    @DisplayName("Take banknotes denomination test")
    @Test
    void getDenominationTest() {
        assertThat(banknote10.getDenomination()).isEqualTo(Denomination.TEN);
    }

    @DisplayName("Take banknotes denomination Value test")
    @Test
    void getDenominationValueTest() {
        assertThat(banknote10.getDenominationValue()).isEqualTo(Denomination.TEN.getDenominationValue());
    }

}
