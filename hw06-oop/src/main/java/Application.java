import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {



        Banknote banknote100 = new Banknote(Denomination.HUNDRED, (int)(Math.random()*10000) );
        Banknote banknote1000 = new Banknote(Denomination.THOUSAND, (int)(Math.random()*10000));
        Banknote banknote1001 = new Banknote(Denomination.THOUSAND, (int)(Math.random()*10000));
        Banknote banknote101 = new Banknote(Denomination.HUNDRED, (int)(Math.random()*10000));
        Banknote banknote5000 = new Banknote(Denomination.FIVE_THOUSAND, (int)(Math.random()*10000));
        Banknote banknote10 = new Banknote(Denomination.TEN, (int)(Math.random()*10000));

        Set<Banknote> wad = new HashSet<>();
        wad.add(banknote101);
        wad.add(banknote101);
        wad.add(banknote100);
        wad.add(banknote1000);
        wad.add(banknote1001);
        wad.add(banknote5000);
        wad.add(banknote10);

        Atm atm1 = new Atm();
        atm1.takeBanknotes(wad);
        atm1.moneyAmountInfo();

        Banknote banknote102 = new Banknote(Denomination.HUNDRED, (int)(Math.random()*10000) );
        Banknote banknote1002 = new Banknote(Denomination.THOUSAND, (int)(Math.random()*10000));
        Banknote banknote103 = new Banknote(Denomination.HUNDRED, (int)(Math.random()*10000));
        Banknote banknote5001 = new Banknote(Denomination.FIVE_THOUSAND, (int)(Math.random()*10000));


        Set<Banknote> wad2 = new HashSet<>();
        wad2.add(banknote102);
        wad2.add(banknote1002);
        wad2.add(banknote5001);
        wad2.add(banknote103);

        atm1.takeBanknotes(wad2);
        atm1.moneyAmountInfo();

        atm1.giveBanknotes(1500);
        atm1.giveBanknotes(5000);
        atm1.giveBanknotes(0);
        atm1.giveBanknotes(1_000_000);
        atm1.moneyAmountInfo();


        Banknote banknote500 = new Banknote(Denomination.FIVE_HUNDRED, (int)(Math.random()*10000));

        Set<Banknote> wad3 = new HashSet<>();

        wad3.add(banknote500);
        atm1.takeBanknotes(wad3);
        atm1.moneyAmountInfo();
    }
}
