package com.geekster.Food_Delivery_Platform_API.service;

import com.geekster.Food_Delivery_Platform_API.model.Food;
import com.geekster.Food_Delivery_Platform_API.model.Order;
import com.geekster.Food_Delivery_Platform_API.repository.IAdminRepo;
import com.geekster.Food_Delivery_Platform_API.repository.IFoodRepo;
import com.geekster.Food_Delivery_Platform_API.repository.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    IFoodRepo foodRepo;

    @Autowired
    IOrderRepo orderRepo;
    public String addFood(Food food) {
        foodRepo.save(food);
        return "Food Added";

    }

    public List<Food> getAllFoods() {
        return foodRepo.findAll();
    }

    public String removeFoodById(Long foodId) {
        foodRepo.deleteById(foodId);
        return "Food Removed for Id " + foodId;
    }

    public String updateFoodById(Long foodId, Food food) {
        Optional<Food> fo = foodRepo.findById(foodId);
        Food foo  = null;
        if(fo.isPresent()){
            foo = fo.get();
        }else{
            return "food not found ";
        }
        foo.setFoodName(food.getFoodName());
        foo.setFoodPrice(food.getFoodPrice());


        foodRepo.save(foo);
        return "Food Updated";
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }
}
