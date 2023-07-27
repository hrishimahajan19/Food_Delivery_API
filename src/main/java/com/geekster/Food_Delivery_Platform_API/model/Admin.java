package com.geekster.Food_Delivery_Platform_API.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    private String adminName;

    @Email(regexp = "^[A-Za-z0-9._%+-]+@hoteladmin\\.com$\n")
    private String adminEmail;






}
