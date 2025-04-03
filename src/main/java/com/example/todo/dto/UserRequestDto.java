package com.example.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestDto {
    //요청이 들어오는건 하나여서 요청 메세지에 body에 들어오는 데이터들은 하나의 requestDto에서 받아야한다.

    private String username;

    private String email;

    private String password;
}
