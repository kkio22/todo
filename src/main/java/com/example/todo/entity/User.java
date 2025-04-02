package com.example.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)//동명이인이 있을 수 있으니 unique = true로 하면 안 됨
    private String username;

    @Column(nullable = false, unique =true)
    private String email;


}
