package homework;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    public Map<Customer, String> customers = new HashMap<>();

    public Map.Entry<Customer, String> getSmallest() {
        Map<Customer, String> minCustomers = new HashMap<>();
        Customer minScoresCustomer;
        long minScores = Long.MAX_VALUE;
        for (Map.Entry<Customer, String> entry : this.customers.entrySet()) {

            Customer customerSmallestScore = entry.getKey();
            if (customerSmallestScore.getScores() < minScores) {
                minScores = customerSmallestScore.getScores();
                minCustomers.clear();
                minScoresCustomer = new Customer(entry.getKey().getId(), entry.getKey().getName(), entry.getKey().getScores());
                minCustomers.put(minScoresCustomer, entry.getValue());
            }
        }
        return minCustomers.entrySet().stream().iterator().next();
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map<Customer, String> nextCustomers = new HashMap<>();
        Customer nextScoresCustomer;
        TreeMap<Customer, String> sortedByScores = new TreeMap<>(Comparator.comparingLong(o -> o.getScores()));
        sortedByScores.put(customer, null);
        sortedByScores.putAll(customers);
        int counter = 0;
        for (Map.Entry<Customer, String> entry : sortedByScores.tailMap(customer).entrySet()) {
            if (counter == 1) {
                nextScoresCustomer = new Customer(entry.getKey().getId(), entry.getKey().getName(), entry.getKey().getScores());
                nextCustomers.put(nextScoresCustomer, entry.getValue());
                return nextCustomers.entrySet().stream().iterator().next();
            }
            counter++;
        }
        return null;
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }
}
