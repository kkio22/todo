package com.example.todo.dto;

import com.example.todo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private String  username;
    private String title;
    private String contents;

}
