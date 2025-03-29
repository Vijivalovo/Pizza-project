package com.example.orders.repository;

import com.example.orders.models.Orders;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer>
{

    List<Orders> getByStatus(String status);

    Orders findByNumber(int number);
}
