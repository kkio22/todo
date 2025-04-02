package com.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;



}


