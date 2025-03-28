package com.example.orders.service.Interfaces;

import com.example.orders.models.OrderItems;

import java.util.List;

import com.example.orders.DTO.OrderItem.CreateOrderItemDTO;

public interface OrderItemInterfaces
{
     OrderItems createOrderItem(CreateOrderItemDTO request);
    OrderItems updateOrderItem(OrderItems orderItems);
    void deleteOrderItem(int id);
    OrderItems findById(int id);

    List<OrderItems> findByOrderId(int id);
}
