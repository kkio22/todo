package com.example.todo.service;

import com.example.todo.dto.UserResponseDto;
import com.example.todo.entity.User;
import com.example.todo.repository.UserRepository;
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

    public UserResponseDto save(String username, String email) {

        User user = new User(username, email);

        User saveUser = userRepository.save(user);//값이 알아서 속성에 맞게 매핑되어있음

        return new UserResponseDto(saveUser.getId(), saveUser.getUsername(), saveUser.getEmail());

    }

    public UserResponseDto findById(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        User findUser = optionalUser.get();//optional에 내장된 매서드 get()은 optional에 저장된 객체(값)을 꺼낸다.

        return new UserResponseDto(findUser.getId(), findUser.getUsername(), findUser.getEmail());
    }


    public UserResponseDto updateUser(Long id, String username, String email) {

        User user = new User(id, username, email);//id를 넘거야 어떤 엔티티를 수정할지 식별할 수 있음

        User updateUser = userRepository.save(user);//JPA에서는 SAVE를 사용하면 자동으로 INSERT나 UPDATE를 수행한다.

        return new UserResponseDto(updateUser.getId(), updateUser.getUsername(), updateUser.getEmail());
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
