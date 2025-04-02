package com.example.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;
//DB와 매핑되는 객체" + "애플리케이션에서 데이터를 다루는 객체"

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

    public User() {

    }

    public User(String username, String email) {
        this.username=username;
        this.email=email;
    }

    public User(Long id, String username, String email) {
        this.id=id;
        this.username=username;
        this.email=email;
    }


}
