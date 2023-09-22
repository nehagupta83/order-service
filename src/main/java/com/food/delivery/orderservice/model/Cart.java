package com.food.delivery.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "cart_id")
    private int cart_id;

    @Column(name = "user_id")
    private int user_id;

    @OneToMany(targetEntity = CartItem.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private List<CartItem> items;
}
