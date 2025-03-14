package com.example.orders.controller;

import com.example.orders.models.Orders;
import com.example.orders.repository.OrderRepository;
import com.example.orders.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController
{

    private final OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    @PostMapping("api/orders/createOrder/")
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody Orders order)
    {
        try
        {
            Orders orderNew = orderService.createOrder(order);
            Map<String, Object> response = new HashMap<>();
            response.put("body", orderNew);
            response.put("message", "Заказ сформирован");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при формировании заказа");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PutMapping("api/orders/updateOrder")
    public ResponseEntity<Map<String, Object>> updateOrder(@RequestBody Orders order)
    {
        try
        {
            Orders orderNew = orderService.updateOrder(order);
            Map<String, Object> response = new HashMap<>();
            response.put("body", orderNew);
            response.put("message", "Заказ обновлён");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при обновлении заказа");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @DeleteMapping("api/orders/deleteOrder/{id}")
    public ResponseEntity<Map<String, Object>> deleteOrder(@PathVariable int id)
    {
        try
        {
            orderService.deleteOrder(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Заказ удалён");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при удалении заказа");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("api/orders/findById/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable int id)
    {
        try
        {
            Orders order = orderService.findById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("body", order);
            response.put("message", "Заказ найден");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при поиске заказа");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("api/orders/getAll")
    public ResponseEntity<Map<String, Object>> getAll(@PathVariable int id)
    {
        try
        {
            List<Orders> orders = orderService.getAll();
            Map<String, Object> response = new HashMap<>();
            response.put("body", orders);
            response.put("message", "Все заказы получены");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при получении всех заказов");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("api/orders/getByStatus/{status}")
    public ResponseEntity<Map<String, Object>> getByStatus(@PathVariable String status)
    {
        try
        {
            List<Orders> orders = orderService.getAll();
            Map<String, Object> response = new HashMap<>();
            response.put("body", orders);
            response.put("message", "Все заказы по их состоянию получены");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при получении заказов по статусу");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("api/orders/findByNumber/{number}")
    public ResponseEntity<Map<String, Object>> findByNumber(@PathVariable int number)
    {
        try
        {
            Orders order = orderService.findByNumber(number);
            Map<String, Object> response = new HashMap<>();
            response.put("body", order);
            response.put("message", "Заказ по номеру получен");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при поиске заказа по номеру");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}
