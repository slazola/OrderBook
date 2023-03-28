package main.java.implementation;

import main.java.entity.Order;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class MatchingEngine {

    private static int ordersMatched;
    public static int executeOrders(Queue<Order> orderQueue) {
        ordersMatched = 0;
        Iterator<Order> orderIterator = orderQueue.iterator();
        //Starting from first order in queue examine each order for a match
        while (orderIterator.hasNext()){
            Order order0 = orderIterator.next();
            if(order0.getQuantity() > 0){    //Ignore orders that have been settled
                List<Order> orderList = orderQueue.stream().filter(order -> !order.equals(order0)).collect(Collectors.toList());
                //Loop through all other orders and search for matching order
                for(Order order1: orderList){
                    if(order0.getQuantity() > 0){
                        Order matchingOrder = findMatchingOrder(order0, order1);
                        if(matchingOrder != null){
                            if(order0.getQuantity() > matchingOrder.getQuantity()){
                                effectOrders(order0, matchingOrder);
                            }else {
                                effectOrders(matchingOrder, order0);
                            }
                        }
                    } else {
                        break;
                    }
                }
            }

        }
        return ordersMatched;
    }

    private static Order findMatchingOrder(Order order,Order order1){

        if((order1.getQuantity() > 0) && (order1.getPrice() == order.getPrice()) && !order1.getSide().equalsIgnoreCase(order.getSide())){
            return order1;
        }
        return null;
    }

    private static void effectOrders(Order order0, Order order1){
        int remainder = order0.getQuantity() - order1.getQuantity();
        order0.setQuantity(remainder);
        order1.setQuantity(0);
        if(ordersMatched == 0)
            ordersMatched++;
        ordersMatched++;
    }
}
