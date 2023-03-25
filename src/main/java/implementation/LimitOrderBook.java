package main.java.implementation;

import main.java.entity.Order;
import main.java.interfaces.OrderBook;

import java.util.*;
import java.util.stream.Collectors;

/** /
 * The LimitOrderBook does a concrete implementation of the OrderBook functions which are:
 * addOrder, deleteOrder, modifyOrder, getOrder, displayOrderBook, viewOrdersByPriceLevelAndSide.
 * The implementation uses a LinkedList Queue data structure to achieve the First IN First Out function,
 * and dynamic data storage where memory is allocated when needed reducing the risk of memory leaks.
 */
public class LimitOrderBook implements OrderBook {

    private Queue<Order> orderQueue = new LinkedList<Order>();
    private Random random = new Random();

    @Override
    public Order addOrder(double price, int quantity, String side) {
        if(quantity > 0 && (side.equalsIgnoreCase(BUY) || side.equalsIgnoreCase(SELL))){
            Order order = new Order(generateOrderId(), price,quantity,side);
            orderQueue.add(order);
            return order;
        }
        return null;
    }

    @Override
    public String deleteOrder(long id) {
        Order deleteOder = getOrder(id);
        if(deleteOder != null && orderQueue.remove(deleteOder)){
            return "Order Deleted!";
        }else {
            return String.format("Order with id %d not found!", id);
        }
    }

    @Override
    public Order modifyOrder(long id, int quantity) {
        Order modOder = null;
        if(quantity > 0){
            modOder = getOrder(id);
            if(modOder != null){
                orderQueue.remove(modOder);
                modOder.setQuantity(quantity);
                orderQueue.add(modOder);
            }
        }
        return modOder;
    }
    @Override
    public Order getOrder(long id){
        Optional<Order> optional = orderQueue.stream().filter(order -> order.getId() == id).findFirst();
        return optional.isPresent() ? optional.get() : null;
    }
    @Override
    public String displayOrderBook(){
        return orderQueue.toString();
    }

    @Override
    public String viewOrdersByPriceLevelAndSide(double priceLevel, String side) {
        if((priceLevel % 10) > 0){
            return "Invalid price level. Please use unit of 10th i.e. 10, 20, 30, 40.. 100 etc.";
        }
        double upperPriceBound = priceLevel + 10;
        List<Order> list = orderQueue.stream()
                .filter(order -> (order.getPrice() >= priceLevel && order.getPrice() <upperPriceBound) && order.getSide().equalsIgnoreCase(side))
                .collect(Collectors.toList());
        if(list.isEmpty()){
            return String.format("No order found for price level %d and '%s' side", priceLevel,side);
        }
        return list.toString();
    }

    private int generateOrderId(){
        return random.nextInt(999999999);
    }
}
