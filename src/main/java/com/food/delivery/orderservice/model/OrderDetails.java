package com.food.delivery.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "order_detail")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "order_id")
    private int order_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "restaurant_id")
    private int restaurant_id;

    @Column(name = "amount")
    private int amount;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "order_date")
    private String orderDate;

    @Column(name = "status")
    private String status;

    @OneToMany(targetEntity = OrderItem.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private List<OrderItem> items;
}
