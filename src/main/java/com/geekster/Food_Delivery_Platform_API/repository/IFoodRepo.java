package com.geekster.Food_Delivery_Platform_API.repository;

import com.geekster.Food_Delivery_Platform_API.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodRepo extends JpaRepository<Food,Long> {
}
