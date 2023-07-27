package com.geekster.Food_Delivery_Platform_API.repository;

import com.geekster.Food_Delivery_Platform_API.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepo extends JpaRepository<Admin,Long> {
}
