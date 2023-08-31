package com.example.bistro.bistroapp.entity;

import com.example.bistro.bistroapp.dto.OrderDTO;
import com.example.bistro.bistroapp.entity.Order;
import com.example.bistro.bistroapp.entity.OrderMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDTO orderToOrderDTO(Order order) {
        if (order == null) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setTotalAmount(order.getTotalAmount());

        return orderDTO;
    }

    @Override
    public Order orderDTOtoOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            return null;
        }

        Order order = new Order();
        order.setTotalAmount(orderDTO.getTotalAmount());


        return order;
    }
}
