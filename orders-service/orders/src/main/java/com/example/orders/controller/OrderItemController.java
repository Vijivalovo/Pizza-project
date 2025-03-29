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
        try
        {
            System.out.println("Проверка" + request);
            OrderItems  orderItemNew = orderItemService.createOrderItem(request);
            Map<String, Object> response = new HashMap<>();
            response.put("body", orderItemNew);
            response.put("message", "Продукт добавлен");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);

        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при создании продудкта");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PutMapping("/updateOrderItem")
    public ResponseEntity<Map<String, Object>> updateOrderItem(@RequestBody OrderItems orderItems)
    {
        try
        {
            OrderItems orderItemNew = orderItemService.updateOrderItem(orderItems);
            Map<String, Object> response = new HashMap<>();
            response.put("body", orderItemNew);
            response.put("message", "Продукт в заказе обновлён");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при обновлении продукта в заказе");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @DeleteMapping("/deleteOrderItem/{id}")
    public ResponseEntity<Map<String, Object>> deleteOrderItem(@PathVariable int id)
    {
        try
        {
            orderItemService.deleteOrderItem(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Продукт в заказе удалён");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при удалении продукта из заказа");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable int id)
    {
        try
        {
            OrderItems orderItem = orderItemService.findById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("body", orderItem);
            response.put("message", "Продукт из заказа найден");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при поиске продукта из заказа");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("/findByOrderId/{id}")
    public ResponseEntity<Map<String, Object>> findByOrderId(@PathVariable int id)
    {
        try
        {
            List<OrderItems> orderItem = orderItemService.findByOrderId(id);
            Map<String, Object> response = new HashMap<>();
            response.put("body", orderItem);
            response.put("message", "Продукт из заказа найден");
            response.put("statusCode", 200);

            return ResponseEntity.ok(response);
        }
        catch(Exception e)
        {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Произошла ошибка при поиске продукта из заказа");
            errorResponse.put("error", e.getMessage());
            errorResponse.put("statusCode", 500);

            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}
