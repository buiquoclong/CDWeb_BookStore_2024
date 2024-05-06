package com.cdweb.bookstore.api.output;

import com.cdweb.bookstore.dto.CartDTO;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartOutput {
    private double total;
    private List<CartDTO> booksList = new ArrayList<>();

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<CartDTO> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<CartDTO> booksList) {
        this.booksList = booksList;
    }

    public double getTotalMoney() {
        //15k ship
        return total + 15000;
    }

    public String getFormat() {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(getTotalMoney()) +"VND";
    }

    public String getTotalFormat() {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(total) + "VND";
    }
}
