package com.cdweb.bookstore.service.impl;


import com.cdweb.bookstore.converter.OrderConverter;
import com.cdweb.bookstore.dto.OrderDTO;
import com.cdweb.bookstore.entities.OrderEntity;
import com.cdweb.bookstore.repository.OrderRepository;
import com.cdweb.bookstore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImp implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderConverter orderConverter;

    @Override
    public OrderDTO save(OrderDTO order) {
        return orderConverter.toDTO(orderRepository.save(orderConverter.toEntity(order)));
    }

    @Override
    public List<OrderDTO> findAllByUserId(int id) {
        List<OrderEntity> list = orderRepository.findAllByUserUserID(id);
        List<OrderDTO> result = new ArrayList<>();
        for (OrderEntity e : list) {
            result.add(orderConverter.toDTO(e));
        }
        return result;
    }

    @Override
    public List<OrderDTO> findAll() {
        List<OrderEntity> list = orderRepository.findAll();
        List<OrderDTO> result = new ArrayList<>();
        for (OrderEntity e : list) {
            result.add(orderConverter.toDTO(e));
        }
        return result;
    }

    @Override
    public void update(OrderDTO order, int id) {
        orderRepository.updateOrder(id, order.getStatus(), order.getNote(), order.getUpdatedAt());
    }

    @Override
    public OrderDTO findById(int id) {
        return orderConverter.toDTO(orderRepository.findByOrderID(id));
    }

    @Override
    public void deleteById(int id) {
        orderRepository.deleteByOrderID(id);
    }
}
