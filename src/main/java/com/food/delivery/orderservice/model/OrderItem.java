package com.food.delivery.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "order_item_id")
    private int order_item_id;

    @Column(name = "price")
    private int price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "item_id")
    private int itemId;

    @Column(name = "order_id")
    private int order_id;
}
