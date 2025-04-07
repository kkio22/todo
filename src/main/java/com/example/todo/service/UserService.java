package com.example.todo.service;

import com.example.todo.config.PasswordEncoder;
import com.example.todo.dto.UserResponseDto;
import com.example.todo.entity.User;
import com.example.todo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public UserResponseDto save(String username, String email, String password) {


        User user = new User(username, email, passwordEncoder.encode(password));

        User saveUser = userRepository.save(user);//값이 알아서 속성에 맞게 매핑되어있음

        return new UserResponseDto(saveUser.getId(), saveUser.getUsername(), saveUser.getEmail());

    }
    @Transactional
    public UserResponseDto findById(Long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));


        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }

    @Transactional
    public UserResponseDto updateUser(Long id, String password, String username, String email) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        if (!passwordEncoder.matches(user.getPassword(), password)) {

            throw new IllegalArgumentException("비밀번호가 다릅니다");

        }
        user.editUser(username, email);

        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }
    @Transactional
    public void deleteUser(Long id, String password) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        if (!passwordEncoder.matches(user.getPassword(), password)) {

            throw new IllegalArgumentException("비밀번호가 다릅니다");
        }
        userRepository.deleteById(id);



    }


}
