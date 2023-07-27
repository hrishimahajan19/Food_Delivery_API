package com.geekster.Food_Delivery_Platform_API.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long orderId;

    private  String orderDescription;

    @OneToMany
    @JoinColumn(name = "fk_order_id")
    private List<Food> foods;

    @OneToOne
    @JoinColumn(name = "fk_user_id")
    private User user;





}
