package homework;

import java.util.*;

public class CustomerService {
    public final NavigableMap<Customer, String> customers = new TreeMap<>(Comparator.comparingLong(o -> o.getScores()));

    public Map.Entry<Customer, String> getSmallest() {
        var item = customers.firstEntry();
        return copy(item);
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        var item = customers.higherEntry(customer);
        return item == null ? null : copy(item);
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }

    private Map.Entry<Customer, String> copy(Map.Entry<Customer, String> entry) {
        Map<Customer, String> copyMap = new HashMap<>();
        copyMap.put(new Customer(entry.getKey().getId(), entry.getKey().getName(), entry.getKey().getScores()), entry.getValue());
        return copyMap.entrySet().stream().iterator().next();
    }
}
