package com.example.orders.controller;

import com.example.orders.models.Orders;
import com.example.orders.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController
{
    @Autowired
    private OrderService orderService;
    
    @PostMapping("/createOrder")
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody Orders order)
    {
        return ResponseEntity.status(201).body(
                                                Map.of(
                                                    "message", "Order created",
                                                    "order", orderService.createOrder(order)
                                                )
        );
    }

    @PutMapping("/updateOrder")
    public ResponseEntity<Map<String, Object>> updateOrder(@RequestBody Orders order)
    {
        return ResponseEntity.status(200).body(
                                                Map.of(
                                                    "message", "Order updated",
                                                    "order", orderService.updateOrder(order)
                                                )
        );
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<Map<String, Object>> deleteOrder(@PathVariable int id)
    {
        orderService.deleteOrder(id);

        return ResponseEntity.status(200).body(
                                                Map.of(
                                                    "message", "Order deleted"
                                                )
        );
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable int id)
    {
        return ResponseEntity.status(200).body(
                                                Map.of(
                                                    "message", "Find Order with id:" + id,
                                                    "order", orderService.findById(id)
                                                )
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<Map<String, Object>> getAll()
    {
        return ResponseEntity.status(200).body(
                                                Map.of(
                                                    "message", "Find all Orders",
                                                    "order", orderService.getAll()
                                                )
        );
    }

    @GetMapping("/getByStatus/{status}")
    public ResponseEntity<Map<String, Object>> getByStatus(@PathVariable String status)
    {
        return ResponseEntity.status(200).body(
                                                Map.of(
                                                    "message", "Find all Orders with status:" + status,
                                                    "order", orderService.getByStatus(status)
                                                )
        );
    }

    @GetMapping("/findByNumber/{number}")
    public ResponseEntity<Map<String, Object>> findByNumber(@PathVariable int number)
    {
        return ResponseEntity.status(200).body(
                                                Map.of(
                                                    "message", "Find Order with number:" + number,
                                                    "order", orderService.findByNumber(number)
                                                )
        );
    }
}
