package main.java;

import main.java.entity.Order;
import main.java.implementation.LimitOrderBook;
import main.java.interfaces.OrderBook;

import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static OrderBook orderBook= new LimitOrderBook();
    public static void main(String[] args) {

        int entry = 9;
        System.out.println("*******  Welcome Limit Order Book Menu ******");
        do{
            System.out.println("\nPlease enter number selection to option:");
            System.out.println("1. Add an order");
            System.out.println("2. Modify an order");
            System.out.println("3. Delete an order");
            System.out.println("4. View orders by price level and side");
            System.out.println("5. Display Limit Order Book");
            System.out.println("6. Match Orders");
            System.out.println("0. Exit");
            if(sc.hasNextInt()){
                entry = sc.nextInt();
                switch (entry) {
                    case 1:
                        addNewOrder();
                        break;
                    case 2:
                        modifyExistingOrder();
                        break;
                    case 3:
                        deleteExistingOrder();
                        break;
                    case 4:
                        viewOrdersByPriceLevelAndSide();
                        break;
                    case 5:
                        displayLimitOrderBook();
                        break;
                    case 6:
                        matchOrders();
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid entry, please try again.");
                }
            } else{
                System.out.println("Invalid entry!!!");
            }

        }while (entry != 0);
        System.out.println("Danko, please come again!");
    }

    private static void addNewOrder(){
        System.out.println("Please enter order price:");
        double price = sc.nextDouble();
        System.out.println("Please enter quantity:");
        int quantity = sc.nextInt();
        System.out.println("Please enter side option: 1 to 'Buy' or 2 to 'Sell' :");
        int sideOption = sc.nextInt();
        String side = "";
        if(sideOption == 1){
            side = orderBook.BUY;
        } else if(sideOption == 2){
            side = orderBook.SELL;
        }

        Order order = orderBook.addOrder(price,quantity,side);
        if(order != null){
            System.out.println("The following order has been added: " + order);
        } else {
            System.out.println("Error adding order!");
        }

    }

    private static void modifyExistingOrder(){
        System.out.println("Please enter order Id to be modified:");
        long id = sc.nextLong();
        System.out.println("Please enter new quantity: ");
        int quantity = sc.nextInt();
        Order order = orderBook.modifyOrder(id,quantity);
        if(order != null){
            System.out.println("Order had been modified successfully to: " + order);
        } else {
            System.out.println("Error modifying order!");
        }

    }

    private static void deleteExistingOrder(){
        System.out.println("Please enter order Id to be deleted:");
        long id = sc.nextLong();
        System.out.println(orderBook.deleteOrder(id));
    }

    private static void displayLimitOrderBook(){
        System.out.println("****   Limit Order Book    ****");
        System.out.println(orderBook.displayOrderBook());
    }

    private static void viewOrdersByPriceLevelAndSide(){
        System.out.println("Please enter order price level in unit of 10th i.e. 10, 20, 30, 40.. 100 etc.:");
        double price = sc.nextDouble();
        System.out.println("Please enter side option: 1 to 'Buy' or 2 to 'Sell' :");
        int sideOption = sc.nextInt();
        String side = "";
        if(sideOption == 1){
            side = orderBook.BUY;
        } else if(sideOption == 2){
            side = orderBook.SELL;
        }
        System.out.println(orderBook.viewOrdersByPriceLevelAndSide(price,side));
    }

    private static void matchOrders(){
        int ordersMatched = orderBook.executeMatchingOrders();
        System.out.println(String.format("There were %d orders matched.",ordersMatched));
        displayLimitOrderBook();
    }
}