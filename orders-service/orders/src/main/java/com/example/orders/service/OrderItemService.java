package com.example.orders.service;

import com.example.orders.models.OrderItems;
import com.example.orders.repository.OrderItemRepository;
import com.example.orders.service.Interfaces.OrderItemInterfaces;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderItemService implements OrderItemInterfaces
{
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Async
    public OrderItems createOrderItem(OrderItems orderItem)
    {
        return orderItemRepository.save(orderItem);
    }

    @Async
    public OrderItems updateOrderItem(OrderItems orderItem)
    {
        if(orderItemRepository.existsById(orderItem.getId()))
        {
            return orderItemRepository.save(orderItem);
        }
        throw new RuntimeException("OrderItem not found");
    }

    @Async
    public void deleteOrderItem(int id)
    {
        Optional<OrderItems> orderItem = orderItemRepository.findById(id);

        if(orderItem.isPresent())
        {
            orderItemRepository.deleteById(id);
        }
        throw new RuntimeException("OrderItem not found");
    }

    @Async
    public OrderItems findById(int id)
    {
        return orderItemRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("OrderItem not found"));
    }
}
