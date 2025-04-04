package com.example.todo.service;

import com.example.todo.config.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto save(String username, String email, String password) {

        //encoder
        User user = new User(username, email, passwordEncoder.encode(password));

        User saveUser = userRepository.save(user);//값이 알아서 속성에 맞게 매핑되어있음

        return new UserResponseDto(saveUser.getId(), saveUser.getUsername(), saveUser.getEmail());

    }

    public UserResponseDto findById(Long id) {

        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        //optional에 내장된 매서드 get()은 optional에 저장된 객체(값)을 꺼낸다.

        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }


    public UserResponseDto updateUser(Long id, String password, String username, String email) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
//데이터베이스에서 데이터 가져올 때 무조건 OPTIONAL이나 예외 처리를 해야하는게 규칙인가?
// id를 넘거야 어떤 엔티티를 수정할지 식별할 수 있음

        if (!passwordEncoder.matches(user.getPassword(), password)) {
            //JPA에서는 SAVE를 사용하면 자동으로 INSERT나 UPDATE를 수행한다.
            throw new IllegalArgumentException("비밀번호가 다릅니다");

        }
        user.editUser(username, email);//user 클래스 값을 매서드로 바꿔줌

        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }

    public void deleteUser(Long id, String password) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        if (!passwordEncoder.matches(user.getPassword(), password)) {

            throw new IllegalArgumentException("비밀번호가 다릅니다");
        }
        userRepository.deleteById(id);



    }


}
