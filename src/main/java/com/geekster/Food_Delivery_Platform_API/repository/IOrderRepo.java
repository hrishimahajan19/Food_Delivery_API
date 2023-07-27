package com.geekster.Food_Delivery_Platform_API.repository;

import com.geekster.Food_Delivery_Platform_API.model.Order;
import com.geekster.Food_Delivery_Platform_API.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepo extends JpaRepository<Order,Long> {

   public Order findFirstByUser(User user);
}
