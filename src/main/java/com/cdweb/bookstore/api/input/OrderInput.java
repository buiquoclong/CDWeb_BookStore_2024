package com.cdweb.bookstore.api.input;

import java.util.ArrayList;
import java.util.List;

public class OrderInput {
    private String email;
    private String name;
    private String phone;
    private String address;
    private String note;
    private List<Integer> carts = new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Integer> getCarts() {
        return carts;
    }

    public void setCarts(List<Integer> carts) {
        this.carts = carts;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
