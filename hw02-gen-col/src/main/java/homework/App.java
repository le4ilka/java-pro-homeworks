package homework;

import java.util.Map;

public class App {
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

        Map.Entry<Customer, String> smallestScore = cs.getSmallest();
        System.out.println(customer1);
        smallestScore.getKey().setName("Vasyl");
        System.out.println(customer1);

//        //cs.getNext(customer5);
//     //   System.out.println("Next for 5 (2, \"Petr2\", 11): " + cs.getNext(customer5).getKey().getScores() + " " + cs.getNext(customer5).getKey().getName() );
//        System.out.println("Next for 1 (id3 \"Pavel\", 888): " + cs.getNext(customer1).getKey().getScores() + " " + cs.getNext(customer1).getKey().getName() );
//
//        cs.getNext(customer2);
//        System.out.println("Next for 2 (id1 \"Ivan\", 233): " + cs.getNext(customer2).getKey().getScores() + " " + cs.getNext(customer2).getKey().getName() );
//
//        System.out.println("Next for 3 (null): " + cs.getNext(customer3));

        System.out.println("Test must be 1" + cs.getNext(new Customer(10, "Key", 20)));

        cs.getSmall();
        System.out.println(cs.getSmall());


    }
}
