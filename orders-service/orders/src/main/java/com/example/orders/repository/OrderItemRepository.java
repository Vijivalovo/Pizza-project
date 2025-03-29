package com.example.orders.repository;

import com.example.orders.models.OrderItems;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItems, Integer> {

    List<OrderItems> findByOrderId(int id);
    
}
