package com.food.delivery.orderservice.repository;

import com.food.delivery.orderservice.model.Cart;
import com.food.delivery.orderservice.model.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    @Transactional
    @Query("select c from cart_item c where c.cart_id =:cart_id")
    List<CartItem> findByCartId(int cart_id, Pageable paging);

    @Transactional
    @Modifying
    @Query("delete from cart_item c where c.cart_id =:cart_id")
    void deleteAllByCartId(int cart_id);
}
