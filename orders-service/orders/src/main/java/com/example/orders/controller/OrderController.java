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
public class OrderController
{
    @Autowired
    private OrderService orderService;
    
    @PostMapping("api/orders/createOrder/")
    public Orders createOrder(@RequestBody Orders order)
    {
        return orderService.createOrder(order);
    }

    @PutMapping("api/orders/updateOrder")
    public Orders updateOrder(@RequestBody Orders order)
    {
        return orderService.updateOrder(order);
    }

    @DeleteMapping("api/orders/deleteOrder/{id}")
    public void deleteOrder(@PathVariable int id)
    {
        try
        {
            orderService.deleteOrder(id);
        }
        catch(Exception e)
        {
            
        }
    }

    @GetMapping("api/orders/findById/{id}")
    public Orders findById(@PathVariable int id)
    {
            Orders order = orderService.findById(id);

            return order;
    }

}
