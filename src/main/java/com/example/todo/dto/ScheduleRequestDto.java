package com.example.todo.dto;

import com.example.todo.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {

    @NotEmpty(message ="제목을 적어주세요")
    @Size(max = 30 , message = "제목은 30글자까지 가능합니다.")
    private String title;

    @NotEmpty(message ="내용을 적어주세요")
    private String contents;

}
