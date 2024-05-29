package com.cdweb.bookstore.service;


import com.cdweb.bookstore.dto.OrderDTO;

import java.util.List;

public interface IOrderService {
    public OrderDTO save(OrderDTO order);

    public List<OrderDTO> findAllByUserId(int id);

    public List<OrderDTO> findAll();

    public OrderDTO findById(int id);

    public void update(OrderDTO order, int id);

    public void deleteById(int id);
}
