package test.java;

import main.java.entity.Order;


public class OrderBookTestUtil {

    public Order createOrder(){
        Order order = new Order(999999999,20.00,20,"Buy");
        return order;
    }

}
