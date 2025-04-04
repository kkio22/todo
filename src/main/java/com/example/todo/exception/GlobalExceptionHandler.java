package com.example.todo.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.ArrayList;
import java.util.List;

import static com.example.todo.exception.ErrorCode.INVALID_INPUT_VALUE;

@Slf4j
@RestControllerAdvice// HTTPSERVLETRequest를 받을 수 있게 해줌
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("MethodArgumentNotValidException: {}", e.getMessage());//내부 디버그용 전체적인 메세지
        ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        String errorMessage = e.getBindingResult().getFieldError() != null ?// e.getBindingResult().getFieldError().getDefaultMessage(): 내가 valid에 적어놓은 에러 메세지 가져오는 매서드
       e.getBindingResult().getFieldError().getDefaultMessage() : errorCode.getMessage();
        ErrorResponse response = ErrorResponse.builder()//이걸 통해 자동으로 시간 생성
                .status(errorCode.getStatus())
                .code(errorCode.getCode())
                .error(errorCode.getError())
                .message(errorMessage)
                .build();
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));

    }
}

