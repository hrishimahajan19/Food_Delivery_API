package com.geekster.Food_Delivery_Platform_API.controller;

import com.geekster.Food_Delivery_Platform_API.dto.SignInInput;
import com.geekster.Food_Delivery_Platform_API.dto.SignUpOutput;
import com.geekster.Food_Delivery_Platform_API.model.Order;
import com.geekster.Food_Delivery_Platform_API.model.User;
import com.geekster.Food_Delivery_Platform_API.service.AuthenticationService;
import com.geekster.Food_Delivery_Platform_API.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("user/signUp")
    public SignUpOutput signUpUser(@RequestBody User user) {
        return userService.signUpUser(user);

    }

    @PostMapping("user/signIn")
    public String signInUser(@RequestBody @Valid SignInInput signInInput) {
        return userService.signInUser(signInInput);
    }

    @PostMapping("CreateOrder")
    public String createOrder(@RequestBody Order order, String email, String token) {
        if (authenticationService.authenticate(email, token)) {
            userService.createOrder(order);
            return "Your Order is Preparing";
        } else {
            return "Order failed because of authentication";
        }
    }

    @GetMapping("getOrder/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return userService.getOrderById(orderId);
    }

    @DeleteMapping("order/cancel")
    public String cancelOrder(String email, String token) {

        if (authenticationService.authenticate(email, token)) {
            userService.cancelOrder(email);
            return "Order cancelled successfully";
        } else {
            return "Order Cancelled  failed because invalid authentication";
        }

    }
}
