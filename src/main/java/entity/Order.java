package main.java.entity;

import java.util.Objects;

public class Order {
    private long id;
    private double price;
    private int quantity;
    private String side;

    public Order() {
    }

    public Order(long id, double price, int quantity, String side) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.side = side;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id;
    }


    @Override
    public String toString() {
        return "Order[" +
                "id=" + id +
                ", price=" + price +
                ", quantity=" + quantity +
                ", side='" + side + '\'' +
                ']';
    }
}
