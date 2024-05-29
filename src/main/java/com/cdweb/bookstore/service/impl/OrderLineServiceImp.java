package com.cdweb.bookstore.service.impl;

import com.cdweb.bookstore.converter.OrderlineConverter;
import com.cdweb.bookstore.dto.OrderlineDTO;
import com.cdweb.bookstore.repository.OrderlineRepository;
import com.cdweb.bookstore.service.IOrderlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderLineServiceImp implements IOrderlineService {
    @Autowired
    private OrderlineRepository orderlineRepository;
    @Autowired
    private OrderlineConverter orderlineConverter;

    public void save(OrderlineDTO orderLine) {
        orderlineRepository.save(orderlineConverter.toEntity(orderLine));
    }
}
