package com.example.bistro.bistroapp.entity;

import com.example.bistro.bistroapp.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO orderToOrderDTO(Order order);

    @Mapping(target = "id", ignore = true)
    Order orderDTOtoOrder(OrderDTO placedOrderDTO);
}
