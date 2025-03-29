package com.example.orders.service;

import com.example.orders.errors.exceptions.orderExceptions.orderNotFound;
import com.example.orders.models.Orders;
import com.example.orders.repository.OrderRepository;
import com.example.orders.service.Interfaces.OrderInterfaces;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderInterfaces
{
    @Autowired
    private OrderRepository orderRepository;

    @Async
    public Orders createOrder(Orders order)
    {
        return orderRepository.save(order);
    }

    @Async
    public Orders updateOrder(Orders order)
    {
        if(orderRepository.existsById(order.getId()))
        {
            return orderRepository.save(order);
        }
        throw new orderNotFound("Order not found by id:" + order.getId());
    }

    @Async
    public void deleteOrder(int id)
    {
        Optional<Orders> order = orderRepository.findById(id);

        if (order.isEmpty())
        {
            throw new orderNotFound("Order not found by id:" + id);
        }

        orderRepository.deleteById(id);
    }

    @Async
    public Orders findById(int id)
    {
        return orderRepository.findById(id)
        .orElseThrow(() -> new orderNotFound("Order not found by id:" + id));

    }

    @Async
    public List<Orders> getAll()
    {
        return orderRepository.findAll();
    }

    @Async
    public List<Orders> getByStatus(String status)
    {
        return orderRepository.getByStatus(status);
    }

    @Async
    public Orders findByNumber(int number)
    {
        return orderRepository.findByNumber(number);
    }
}