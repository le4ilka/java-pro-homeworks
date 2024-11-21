package homework;

import java.util.LinkedList;
import java.util.List;

public class CustomerReverseOrder {

   public final List<Customer> customers = new LinkedList<>();

    public void add(Customer customer) {
        customers.add(customer);
    }

    public Customer take() {
        return customers.removeLast();
    }
}
