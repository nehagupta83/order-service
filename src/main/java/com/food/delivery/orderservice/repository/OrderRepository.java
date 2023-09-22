package com.food.delivery.orderservice.repository;

import com.food.delivery.orderservice.model.OrderDetails;
import com.food.delivery.orderservice.model.OrderItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetails, Integer> {
    @Transactional
    @Query("select o from order_detail o where user_id =:user_id")
    List<OrderDetails> findAllByUserId(String user_id, Pageable paging);

    @Transactional
    @Query("select o from order_detail o where restaurant_id =:restaurantId")
    List<OrderDetails> findAllByRestaurantId(String restaurantId);

    @Transactional
    @Query("select o.status from order_detail o where order_id =:orderId")
    String findOrderStatusById(int orderId);
}
