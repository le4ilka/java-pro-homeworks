package homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        Customer customer1 = new Customer(1, "Ivan1", 233);
        Customer customer2 = new Customer(2, "Petr2", 11);
        Customer customer3 = new Customer(3, "Pavel3", 888);
        Customer customer4 = new Customer(4, "Oleg4", 555);
        Customer customer5 = new Customer(5, "Sergey5", 10);

        CustomerService cs = new CustomerService();
        cs.add(customer1, "data1");
        cs.add(customer2, "data2");
        cs.add(customer3, "data3");
        cs.add(customer4, "data4");
        cs.add(customer5, "data5");

        System.out.println("Smallest: " + cs.getSmallest().getKey().getScores());
        LOGGER.info("Smallest: {}", cs.getSmallest().getKey().getScores());

        Map.Entry<Customer, String> smallestScore = cs.getSmallest();
        LOGGER.info(String.valueOf(customer1));
        smallestScore.getKey().setName("Vasyl");
        LOGGER.info(String.valueOf(customer1));

        LOGGER.info("!!!Test must be 1 (next to 20 Scores): {}", cs.getNext(new Customer(10, "Key", 20)));
        LOGGER.info("!!!Test must be null (next to 888 Scores): {}", cs.getNext(customer3));
        LOGGER.info("Smallest scores: {}", cs.getSmallest());
    }
}
