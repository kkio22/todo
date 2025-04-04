package com.example.todo.controller;

import com.example.todo.dto.SignInRequestDto;
import com.example.todo.dto.SignInResponseDto;
import com.example.todo.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<SignInResponseDto> signIn(@Valid @RequestBody SignInRequestDto requestDto, HttpServletRequest httpServletRequest) {
        SignInResponseDto signIn = authService.signIn(
                requestDto.getEmail(),
                requestDto.getPassword(),
                httpServletRequest

        );
        return new ResponseEntity<>(signIn, HttpStatus.OK);
    }

}
