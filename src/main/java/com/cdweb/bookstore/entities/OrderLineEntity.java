package com.cdweb.bookstore.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "orderline")
public class OrderLineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int quantity;
    @Column(name = "total_price")
    private int totalPrice;
    @ManyToOne
    @JoinColumn(name = "orderID")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "bookID")
    private BookEntity book;

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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }
}
