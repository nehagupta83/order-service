package com.food.delivery.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "cart_item_id")
    private int cart_item_id;

    @Column(name = "price")
    private int price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "item_id")
    private int itemId;

    @Column(name = "restaurant_id")
    private int restaurant_id;

    @Column(name = "cart_id")
    private int cart_id;
}
