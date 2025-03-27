package com.example.orders.service;

import com.example.orders.models.OrderItems;
import com.example.orders.models.Orders;
import com.example.orders.repository.OrderItemRepository;
import com.example.orders.service.Interfaces.OrderItemInterfaces;
import com.example.orders.repository.OrderRepository;
import com.example.orders.DTO.OrderItem.CreateOrderItemDTO;

import jakarta.persistence.EntityNotFoundException;

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

    @Async
    public OrderItems createOrderItem(CreateOrderItemDTO request)
    {
        Orders order = orderRepository.findById(request.getOrder_id())
        .orElseThrow(() -> new RuntimeException("Order not found"));

        System.out.println("Проверка" + order);

        OrderItems orderItem = new OrderItems();
        orderItem.setProduct_id(request.getProduct_id());
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

    @Async
    public List<OrderItems> findByOrderId(int id)
    {
        List<OrderItems> orderItems = orderItemRepository.findAll();

        List<OrderItems> filteredOrderItems = new ArrayList<>();

        for (OrderItems orderItem : orderItems)
        {
            if (orderItem.getOrder().getId() == id)
            {
                filteredOrderItems.add(orderItem);
            }
        }

        return filteredOrderItems;
    }
}
