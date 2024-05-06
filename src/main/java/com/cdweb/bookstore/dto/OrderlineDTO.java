package com.cdweb.bookstore.dto;

import java.text.DecimalFormat;

public class OrderlineDTO {
    private int id;
    private int quantity;
    private double totalPrice;
    private OrderDTO order;
    private BookDTO book;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public String getTotalFormat() {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(totalPrice) + "VND";
    }
}
