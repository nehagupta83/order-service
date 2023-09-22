package com.food.delivery.orderservice.repository;

import com.food.delivery.orderservice.model.Cart;
import com.food.delivery.orderservice.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
