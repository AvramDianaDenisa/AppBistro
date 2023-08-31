package com.example.bistro.bistroapp.service;

import com.example.bistro.bistroapp.entity.Ingredient;
import com.example.bistro.bistroapp.entity.Order;
import com.example.bistro.bistroapp.entity.Product;
import jakarta.transaction.Transactional;

import java.util.List;

public interface OrderService {

    Order createOrder(Long customerId, List<Long> productIds);
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    void removeOrder(Long id);



}
