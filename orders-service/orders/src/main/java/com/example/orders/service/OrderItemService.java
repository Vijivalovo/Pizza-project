package com.example.orders.service;

import com.example.orders.models.OrderItems;
import com.example.orders.models.Orders;
import com.example.orders.repository.OrderItemRepository;
import com.example.orders.service.Interfaces.OrderItemInterfaces;
import com.example.orders.repository.OrderRepository;
import com.example.orders.DTO.OrderItem.CreateOrderItemDTO;
import com.example.orders.errors.exceptions.orderExceptions.orderNotFound;
import com.example.orders.errors.exceptions.orderItemExceptions.orderItemNotFound;

import jakarta.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService implements OrderItemInterfaces
{
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Async
    public OrderItems createOrderItem(CreateOrderItemDTO request)
    {
        Orders order = orderRepository.findById(request.getOrder_id())
        .orElseThrow(() -> new orderNotFound("Order not found by id:" + request.getOrder_id()));

        OrderItems orderItem = modelMapper.map(request, OrderItems.class);
        orderItem.setOrder(order);

        return orderItemRepository.save(orderItem);
    }

    @Async
    public OrderItems updateOrderItem(OrderItems orderItem)
    {
        if(orderItemRepository.existsById(orderItem.getId()))
        {
            return orderItemRepository.save(orderItem);
        }
        throw new orderItemNotFound("OrderItem not found by id:" + orderItem.getId());
    }

    @Async
    public void deleteOrderItem(int id)
    {
        Optional<OrderItems> orderItem = orderItemRepository.findById(id);

        if(orderItem.isPresent())
        {
            orderItemRepository.deleteById(id);
        }
        throw new orderItemNotFound("OrderItem not found by id:" + id);
    }

    @Async
    public OrderItems findById(int id)
    {
        return orderItemRepository.findById(id)
        .orElseThrow(() -> new orderItemNotFound("OrderItem not found by id:" + id));
    }

    @Async
    public List<OrderItems> findByOrderId(int id)
    {
        return orderItemRepository.findByOrderId(id);
    }
}
