package com.geekster.Food_Delivery_Platform_API.controller;

import com.geekster.Food_Delivery_Platform_API.model.Food;
import com.geekster.Food_Delivery_Platform_API.model.Order;
import com.geekster.Food_Delivery_Platform_API.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("food")
    public String addFood(@RequestBody Food food){
        return adminService.addFood(food);
    }

    @GetMapping("foods")
    public List<Food> getAllFoods(){
        return adminService.getAllFoods();

    }

    @PutMapping("update/{foodId}/{food}")
    public String updateFoodById(@PathVariable Long foodId , @RequestBody Food food){
        return adminService.updateFoodById(foodId,food);

    }


    @DeleteMapping("delete/{foodId}")
    public String removeFoodById(@PathVariable Long foodId){
        return adminService.removeFoodById(foodId);
    }

    @GetMapping("getAllOrders")
    public List<Order> getAllOrders(){
        return adminService.getAllOrders();

    }

}
