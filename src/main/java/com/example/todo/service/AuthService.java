package com.example.todo.service;

import com.example.todo.dto.SignInResponseDto;
import com.example.todo.entity.User;
import com.example.todo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
//로그인 성공 시 세션 생성은 서비스에서 작성
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    public AuthService(UserRepository userRepository, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.httpSession = httpSession;
    }

    public SignInResponseDto login(String email, String password, HttpServletRequest httpServletRequest) {
        User findEmail = userRepository.findByEmail(email)
                .orElseThrow(()
                        -> new ResponseStatusException(HttpStatus.NOT_FOUND, "이메일이 없습니다"));

        if (!findEmail.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        HttpSession session = httpServletRequest.getSession(true);//세션 생성
        //
        session.setAttribute("userId", findEmail.getId());

        return new SignInResponseDto(findEmail.getId(),findEmail.getUsername(), "로그인에 성공했습니다.");//로그인 성공하면 보내줄 값을 여기다 작성


    }


}
