package com.example.orders.controller;

import com.example.orders.models.OrderItems;
import com.example.orders.models.Orders;
import com.example.orders.service.OrderService;
import com.example.orders.repository.OrderItemRepository;
import com.example.orders.service.OrderItemService;
import com.example.orders.DTO.OrderItem.CreateOrderItemDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orderItems")
public class OrderItemController
{
    @Autowired
    private final OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemService orderItemService;

    OrderItemController(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @PostMapping("/createOrderItem")
    public ResponseEntity<Map<String, Object>> createOrderItem(@RequestBody CreateOrderItemDTO request)
    {
        return ResponseEntity.status(201).body(
                                                Map.of(
                                                    "message", "OrderItem created",
                                                    "order", orderItemService.createOrderItem(request)
                                                )
        );
    }

    @PutMapping("/updateOrderItem")
    public ResponseEntity<Map<String, Object>> updateOrderItem(@RequestBody OrderItems orderItems)
    {
        return ResponseEntity.status(200).body(
                                                Map.of(
                                                    "message", "OrderItem updated",
                                                    "order", orderItemService.updateOrderItem(orderItems)
                                                )
        );
    }

    @DeleteMapping("/deleteOrderItem/{id}")
    public ResponseEntity<Map<String, Object>> deleteOrderItem(@PathVariable int id)
    {
        orderItemService.deleteOrderItem(id);

        return ResponseEntity.status(200).body(
                                                Map.of(
                                                    "message", "OrderItem deleted"
                                                )
        );
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable int id)
    {
        return ResponseEntity.status(200).body(
                                                Map.of(
                                                    "message", "Find OrderItem with id:" + id,
                                                    "order", orderItemService.findById(id)
                                                )
        );
    }

    @GetMapping("/findByOrderId/{id}")
    public ResponseEntity<Map<String, Object>> findByOrderId(@PathVariable int id)
    {
        return ResponseEntity.status(200).body(
                                                Map.of(
                                                    "message", "Find all OrderItems with OrderId:" + id,
                                                    "order", orderItemService.findByOrderId(id)
                                                )
        );
    }
}
