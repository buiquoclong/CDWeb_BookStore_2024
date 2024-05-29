package com.cdweb.bookstore.service;

import com.cdweb.bookstore.dto.OrderlineDTO;

public interface IOrderlineService {
    public void save(OrderlineDTO orderLine);
}
