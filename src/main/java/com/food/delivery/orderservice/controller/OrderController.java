package com.food.delivery.orderservice.controller;

import com.food.delivery.orderservice.dto.OrderRequest;
import com.food.delivery.orderservice.model.Cart;
import com.food.delivery.orderservice.model.CartItem;
import com.food.delivery.orderservice.model.OrderDetails;
import com.food.delivery.orderservice.model.OrderItem;
import com.food.delivery.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/placeOrder")
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order created successfully!";
    }

    @PostMapping("/cart")
    public String createCart(@RequestBody Cart cart){
        orderService.createCart(cart);
        return "Cart created successfully!";
    }

    @DeleteMapping("/cart")
    public String removeCartItem(@RequestParam int cartItemId){
        orderService.removeCartItem(cartItemId);
        return "Item removed successfully!";
    }

    @PutMapping("/cart")
    public String updateCartItem(@RequestParam CartItem cartItem){
        orderService.updateCartItem(cartItem);
        return "Item updated successfully!";
    }

    @GetMapping("/cart")
    public Cart viewCart(@RequestParam int cartId){
        return orderService.viewCart(cartId);
    }

    @PostMapping("/addToCart")
    public String addToCart(@RequestBody CartItem item){
        orderService.addToCart(item);
        return "Item added to cart successfully!";
    }

    @GetMapping("/restaurantOrders")
    public List<OrderDetails> viewRestaurantOrders(@RequestParam String restaurantId){
        return orderService.getRestaurantOrders(restaurantId);
    }

    @GetMapping("/customerOrders")
    public List<OrderDetails> viewCustomerOrders(@RequestParam int page,
                                                 @RequestParam int size,
                                                 @RequestParam String userId){
        return orderService.getCustomerOrders(page, size, userId);
    }

    @PutMapping("/orderUpdate")
    public String updateOrderStatus(@RequestParam int orderId, @RequestParam String status){
        orderService.updateOrderStatus(orderId, status);
        return "Order status updated successfully!";
    }

    @GetMapping("/orderStatus")
    public String trackOrderStatus(@RequestParam int orderId){
        return orderService.trackOrderStatus(orderId);
    }

}
