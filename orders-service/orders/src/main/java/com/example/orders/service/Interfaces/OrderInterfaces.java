package com.example.orders.service.Interfaces;

import com.example.orders.models.Orders;
import java.util.List;

public interface OrderInterfaces
{
    Orders createOrder(Orders order);
    Orders updateOrder(Orders order);
    void deleteOrder(int id);
    Orders findById(int id);

    List<Orders> getAll();
    List<Orders> getByStatus(String status);
    Orders findByNumber(int number);
}