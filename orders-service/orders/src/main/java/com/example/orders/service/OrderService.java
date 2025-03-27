package com.example.orders.service;

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
        throw new RuntimeException("Order not found");
    }

    @Async
    public void deleteOrder(int id)
    {
        Optional<Orders> order = orderRepository.findById(id);

        if (order.isEmpty()) {
            throw new RuntimeException("Order not found");
        }
        System.out.println(order);
        orderRepository.deleteById(id);
    }

    @Async
    public Orders findById(int id)
    {
        return orderRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Order not found"));

    }

    @Async
    public List<Orders> getAll()
    {
        return orderRepository.findAll();
    }

    @Async
    public List<Orders> getByStatus(String status)
    {
        List<Orders> orders = orderRepository.findAll();

        List<Orders> filteredOrders = new ArrayList<>();

        for (Orders order : orders)
        {
            if (status.equals(order.getStatus()))
            {
                filteredOrders.add(order);
            }
        }

        return filteredOrders;
    }

    @Async
    public Orders findByNumber(int number)
    {
        List<Orders> orders = orderRepository.findAll();

        for (Orders order : orders)
        {
            if (number == order.getNumber())
            {
                return order;
            }
        }

        throw new RuntimeException("Order not found");
    }
}