package com.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInResponseDto {

    private Long userId;
    private String username;
    private String messsage;
}
