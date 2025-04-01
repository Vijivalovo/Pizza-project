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
    public ResponseEntity<ResponseClass<OrderItems>> createOrderItem(@RequestBody CreateOrderItemDTO request)
    {
        ResponseClass<OrderItems> response = new ResponseClass<>("Order created", orderItemService.createOrderItem(request));

        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/updateOrderItem")
    public ResponseEntity<ResponseClass<OrderItems>> updateOrderItem(@RequestBody OrderItems orderItems)
    {
        ResponseClass<OrderItems> response = new ResponseClass<>("Order created", orderItemService.updateOrderItem(orderItems));

        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/deleteOrderItem/{id}")
    public ResponseEntity<ResponseClass<Void>> deleteOrderItem(@PathVariable int id)
    {
        orderItemService.deleteOrderItem(id);

        ResponseClass<Void> response = new ResponseClass<>("Order created", null);

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ResponseClass<OrderItems>> findById(@PathVariable int id)
    {
        ResponseClass<OrderItems> response = new ResponseClass<>("Order created", orderItemService.findById(id));

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/findByOrderId/{id}")
    public ResponseEntity<ResponseClass<List<OrderItems>>> findByOrderId(@PathVariable int id)
    {
        ResponseClass<List<OrderItems>> response = new ResponseClass<>("Order created", orderItemService.findByOrderId(id));

        return ResponseEntity.status(200).body(response);
    }
}
