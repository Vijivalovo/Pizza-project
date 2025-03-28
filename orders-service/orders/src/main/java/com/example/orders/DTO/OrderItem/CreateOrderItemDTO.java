package com.example.orders.DTO.OrderItem;

import lombok.Data;

@Data
public class CreateOrderItemDTO {
    private Integer product_id;
    private Integer  order_id;
}