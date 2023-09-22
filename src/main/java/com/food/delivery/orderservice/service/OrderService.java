package com.food.delivery.orderservice.service;

import com.food.delivery.orderservice.dto.OrderRequest;
import com.food.delivery.orderservice.model.Cart;
import com.food.delivery.orderservice.model.CartItem;
import com.food.delivery.orderservice.model.OrderDetails;
import com.food.delivery.orderservice.model.OrderItem;
import com.food.delivery.orderservice.repository.CartItemRepository;
import com.food.delivery.orderservice.repository.CartRepository;
import com.food.delivery.orderservice.repository.OrderItemRepository;
import com.food.delivery.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    OrderItemRepository orderItemRepository;

    public void placeOrder(OrderRequest orderRequest) {
        List<OrderItem> orderItems = orderRequest.getCart().getItems().stream().map(this::mapToDto).toList();
        int amount = orderItems.stream().mapToInt(i -> i.getPrice()*i.getQuantity()).sum();
        int restaurantId = orderRequest.getCart().getItems().get(0).getRestaurant_id();
        OrderDetails order = new OrderDetails(0, orderRequest.getCart().getUser_id(), restaurantId, amount, orderRequest.getDeliveryAddress(), LocalDateTime.now().toString(), "pending confirmation", null);

        OrderDetails orderDetails = orderRepository.save(order);

        orderItems.forEach(i -> i.setOrder_id(orderDetails.getOrder_id()));

        orderItemRepository.saveAll(orderItems);
        cartItemRepository.deleteAll(orderRequest.getCart().getItems());
    }

    private OrderItem mapToDto(CartItem cartItem){
        OrderItem item = new OrderItem();
        item.setItemId(cartItem.getItemId());
        item.setPrice(cartItem.getPrice());
        item.setQuantity(cartItem.getQuantity());
        return item;
    }

    public void addToCart(CartItem cartItem) {
        Pageable paging = PageRequest.of(0, 1);
        List<CartItem>  oldCartItem = cartItemRepository.findByCartId(cartItem.getCart_id(), paging);
        if(!oldCartItem.isEmpty() && oldCartItem.get(0).getRestaurant_id()!=cartItem.getRestaurant_id()) {
            cartItemRepository.deleteAllByCartId(cartItem.getCart_id());
        }
        cartItemRepository.save(cartItem);
    }

    public void updateCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    public void createCart(Cart cart) {
        cartRepository.save(cart);
    }

    public Cart viewCart(int cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if(cart.isPresent())
            return cart.get();
        return null;
    }

    public void removeCartItem(int cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public List<OrderDetails> getRestaurantOrders(String restaurantId) {
        return orderRepository.findAllByRestaurantId(restaurantId);
    }

    public List<OrderDetails> getCustomerOrders(int page, int size, String userId) {
        Pageable paging = PageRequest.of(page, size);
        return orderRepository.findAllByUserId(userId, paging);
    }

    public void updateOrderStatus(int orderId, String status) {
        OrderDetails orderDetail = orderRepository.findById(orderId).get();
        orderDetail.setStatus(status);
        orderRepository.save(orderDetail);
    }

    public String trackOrderStatus(int orderId) {
        String status = orderRepository.findOrderStatusById(orderId);
        return status;
    }
}
