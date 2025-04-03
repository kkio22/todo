package com.example.todo.controller;

import com.example.todo.dto.UserRequestDto;
import com.example.todo.dto.UserResponseDto;
import com.example.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserResponseDto> save(@RequestBody UserRequestDto requestDto) {
        UserResponseDto userResponseDto = userService.save(
                requestDto.getUsername(),
                requestDto.getEmail(),
                requestDto.getPassword()
        );
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDto requestDto
    ) {
        UserResponseDto userResponseDto = userService.updateUser(
                id,
                requestDto.getPassword(),
                requestDto.getUsername(),
                requestDto.getEmail()
        );

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")//id는 해당 id가 있는 전체 행을 처리 가능하다.
    public ResponseEntity<String> deleteUser(
            @PathVariable Long id,
            @RequestBody UserRequestDto requestDto
    )
    {
        userService.deleteUser(
                id,
                requestDto.getPassword());

        return ResponseEntity.ok("사용자를 삭제했습니다.");
    }
}




