package main.java.interfaces;

import main.java.entity.Order;

public interface OrderBook {
    public static final String BUY = "Buy";
    public static final String SELL = "Sell";
    Order addOrder(double price, int quantity, String side);
    String deleteOrder(long id);
    Order modifyOrder(long id,int quantity);
    Order getOrder(long id);
    String displayOrderBook();
    String viewOrdersByPriceLevelAndSide(double priceLevel, String side);
    int executeMatchingOrders();
}
