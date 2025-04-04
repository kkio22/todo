package com.example.todo.dto;

import jakarta.validation.constraints.*;//jakarta 경로에 있는 어노테이션이 자바에서 기본으로 제공한다.
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInRequestDto {

    @NotEmpty
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    @Size(min = 5, max = 35, message = "이메일 길이는 5글자에서 35글자여야 합니다.")
    private final String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 10, message = "비밀번호는 최소 10글자 이상이어야 합니다.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{10,}$",
            message = "비밀번호는 최소 10자 이상이며, 영문, 숫자, 특수문자를 포함해야 합니다."
    )
    private final String password;

}
