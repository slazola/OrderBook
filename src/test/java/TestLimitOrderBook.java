package test.java;

import main.java.entity.Order;
import main.java.implementation.LimitOrderBook;
import main.java.interfaces.OrderBook;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * These test units test the methods in the LimitOrder implementation class
 * */
public class TestLimitOrderBook {
    private  OrderBook orderBook= new LimitOrderBook();
    private OrderBookTestUtil orderBookTestUtil = new OrderBookTestUtil();
    @Test
    public void testAddOrder(){
        assertEquals(orderBookTestUtil.createOrder().getPrice(),orderBook.addOrder(20.00,20,"Buy").getPrice());
    }
@Test
    public void testDeleteOrder(){
        Order order = orderBook.addOrder(20.00,50,"Buy");
        assertEquals("Order Deleted!",orderBook.deleteOrder(order.getId()));
    }

    @Test
    public void testModifyOrder(){
        Order order = orderBook.addOrder(33.33,10,"Buy");
        assertEquals(20,orderBook.modifyOrder(order.getId(),20).getQuantity());

    }

    @Test
    public void testViewOrdersByPriceLevelAndSide(){
        Order order = orderBook.addOrder(33.33,50,"Buy");
        assertEquals(String.format("[%s]",order.toString()),orderBook.viewOrdersByPriceLevelAndSide(30,"Buy"));
    }

}
