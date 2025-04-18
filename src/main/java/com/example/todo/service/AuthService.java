package com.example.todo.service;

import com.example.todo.config.PasswordEncoder;
import com.example.todo.dto.SignInResponseDto;
import com.example.todo.entity.User;
import com.example.todo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {
    //로그인 성공 시 세션 생성은 서비스에서 작성
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    //하나의 단위를 트랙잭션으로 묶어주는 기능으로, DB 작업 중간에 오류가 나면 자동으로 롤백되게 해주는 것이다.
    public SignInResponseDto signIn(String email, String password, HttpServletRequest httpServletRequest) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()
                        -> new ResponseStatusException(HttpStatus.NOT_FOUND, "이메일이 없습니다"));

        if (!passwordEncoder.matches(user.getPassword(), password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        HttpSession session = httpServletRequest.getSession(true);//세션 생성

        session.setAttribute("userId", user.getId());

        return new SignInResponseDto(user.getId(), user.getUsername(), "로그인에 성공했습니다.");


    }


}
