package com.example.todo.controller;

import com.example.todo.dto.SignInRequestDto;
import com.example.todo.dto.SignInResponseDto;
import com.example.todo.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AuthController {//회원가입은 이미 끝남 -> 로그인 API를 보내서 내 이메일과 비밀번호가 있는지 확인하고, 있으면 로그인 성공!
    //=> 로그인 성공 시 세션을 생성(쿠키는 자동으로 spring이 만들어서 보내줌)

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<SignInResponseDto> signIn(@RequestBody SignInRequestDto requestDto, HttpServletRequest httpServletRequest) {
        SignInResponseDto signIn = authService.login(
                requestDto.getEmail(),
                requestDto.getPassword(),
                httpServletRequest

        );
        return new ResponseEntity<>(signIn, HttpStatus.OK);
    }

}
