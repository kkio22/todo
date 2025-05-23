package com.example.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;
//DB와 매핑되는 객체" + "애플리케이션에서 데이터를 다루는 객체" =>Entity

@Getter
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)//동명인때문에 false로 지정
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;


    public User() {

    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void editUser(String username, String email) {
        this.username = username;
        this.email = email;
    }


}



