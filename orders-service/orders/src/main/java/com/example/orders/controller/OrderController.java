package com.example.orders.controller;

import com.example.orders.config.MessageClass;
import com.example.orders.models.Orders;
import com.example.orders.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController
{
    @Autowired
    private OrderService orderService;
    
    @PostMapping("/createOrder")
    public ResponseEntity<ResponseClass<Orders>> createOrder(@RequestBody Orders order)
    {
        ResponseClass<Orders> response = new ResponseClass<>(MessageClass.ORDER_CREATED, orderService.createOrder(order));

        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/updateOrder")
    public ResponseEntity<ResponseClass<Orders>> updateOrder(@RequestBody Orders order)
    {
        ResponseClass<Orders> response = new ResponseClass<>(MessageClass.ORDER_UPDATED, orderService.updateOrder(order));

        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<ResponseClass<Void>> deleteOrder(@PathVariable int id)
    {
        orderService.deleteOrder(id);

        ResponseClass<Void> response = new ResponseClass<>(MessageClass.ORDER_DELETED, null);

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ResponseClass<Orders>> findById(@PathVariable int id)
    {
        ResponseClass<Orders> response = new ResponseClass<>(MessageClass.ORDER_FINDBYID + id, orderService.findById(id));

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseClass<List<Orders>>> getAll()
    {
        ResponseClass<List<Orders>> response = new ResponseClass<>(MessageClass.ORDER_GETALL, orderService.getAll());

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/getByStatus/{status}")
    public ResponseEntity<ResponseClass<List<Orders>>> getByStatus(@PathVariable String status)
    {
        ResponseClass<List<Orders>> response = new ResponseClass<>(MessageClass.ORDER_GETBYSTATUS + status, orderService.getByStatus(status));

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/findByNumber/{number}")
    public ResponseEntity<ResponseClass<Orders>> findByNumber(@PathVariable int number)
    {
        ResponseClass<Orders> response = new ResponseClass<>(MessageClass.ORDER_FINDBYNUMBER + number, orderService.findByNumber(number));

        return ResponseEntity.status(200).body(response);
    }
}
