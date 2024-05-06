package com.cdweb.bookstore.dto;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class CartDTO {
    private int cartID;
    private int quantity;
    private UserDTO user;
    private BookDTO book;

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public double getTotalAmount() {
        return this.quantity * book.getPrice() * (1 - book.getDiscountPercent() / 100);
    }

    public String getTotalFormat() {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(getTotalAmount()) + "VND";
    }
}
