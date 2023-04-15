package com.example.rateLimiter.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "sys_user")
public class User {
    @Id
    private Integer id;
    private String username;
    private String password;
    private String token;
}
