package homework;

import java.util.*;

public class CustomerService {

    public final Map<Customer, String> customers = new HashMap<>();

    public Map.Entry<Customer, String> getSmallest() {
        Map<Customer, String> smallScoresCustomers = new HashMap<>();

        NavigableMap<Long, Map.Entry<Customer, String>> sortedByScoresCustomers = new TreeMap<>();
        for (Map.Entry<Customer, String> entry : this.customers.entrySet()) {
            sortedByScoresCustomers.put(entry.getKey().getScores(), entry);
        }
        smallScoresCustomers.put(new Customer(sortedByScoresCustomers.firstEntry().getValue().getKey().getId(),
                        sortedByScoresCustomers.firstEntry().getValue().getKey().getName(),
                        sortedByScoresCustomers.firstEntry().getValue().getKey().getScores()),
                new String(sortedByScoresCustomers.firstEntry().getValue().getValue()));
        return smallScoresCustomers.entrySet().stream().iterator().next();
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map<Customer, String> nextScoresCustomers = new HashMap<>();

        NavigableMap<Customer, String> sortedByScores = new TreeMap<>(Comparator.comparingLong(o -> o.getScores()));
        sortedByScores.put(customer, null);
        sortedByScores.putAll(customers);
        if (sortedByScores.tailMap(customer, false).size() == 0)
            return null;

        nextScoresCustomers.put(new Customer(sortedByScores.tailMap(customer, false).firstKey().getId(),
                        sortedByScores.tailMap(customer, false).firstKey().getName(),
                        sortedByScores.tailMap(customer, false).firstKey().getScores()),
                sortedByScores.tailMap(customer, false).get(sortedByScores.tailMap(customer, false).firstKey()));

        return nextScoresCustomers.entrySet().stream().iterator().next();
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }
}
