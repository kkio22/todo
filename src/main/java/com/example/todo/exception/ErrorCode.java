package com.example.todo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    //User
    USER_NOT_FOUND(404, "Not Found", "U001", "User Not Found"),
    EMAIL_DUPLICATION(400, "Bad Request", "U001", "Email is Duplicated"),
    //Schedule
    SCHEDULE_NOT_FOUND(404, "Not found", "U001", "User Not Found"),

    //Common
    INVALID_INPUT_VALUE(400, "Bad Request", "C001", "Invalid Input Value"),

    //login
    UNAUTHORIZED(401, "NOT unauthorised ", "E001", "Need Certification");


    private final int status;
    private final String error;
    private final String code;
    private final String message;
    }
