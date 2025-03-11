package com.example.orders.service.Interfaces;

import com.example.orders.models.OrderItems;

public interface OrderItemInterfaces
{
    OrderItems createOrderItem(OrderItems orderItem);
    OrderItems updateOrderItem(OrderItems orderItems);
    void deleteOrderItem(int id);
    OrderItems findById(int id);
}
