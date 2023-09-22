package com.food.delivery.orderservice.dto;

import com.food.delivery.orderservice.model.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class OrderRequest {
    private Cart cart;
    private String deliveryAddress;

}
