package com.example.todo.controller;

import com.example.todo.dto.ScheduleRequestDto;
import com.example.todo.dto.ScheduleResponseDto;
import com.example.todo.entity.Schedule;
import com.example.todo.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;


    @PostMapping("/schedule")
    public ResponseEntity<ScheduleResponseDto> save(@Valid @RequestBody ScheduleRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        ScheduleResponseDto scheduleResponseDto = scheduleService.save(
                userId,
                requestDto.getTitle(),
                requestDto.getContents()

        );
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
        ScheduleResponseDto findSchedule = scheduleService.findById(id);
        return new ResponseEntity<>(findSchedule,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @Valid @RequestBody ScheduleRequestDto requestDto, HttpServletRequest request){
        HttpSession session = request.getSession();//HttpServletRequest으로 받으니까 getsession()매서드 사용 가능, HttpServletRequest 안에 있음
        Long userId = (Long) session.getAttribute("userId");
        ScheduleResponseDto scheduleResponseDto  = scheduleService.updateSchedule(
                id,
                userId,
                requestDto.getTitle(),
                requestDto.getContents()
        );

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id, HttpServletRequest request ) {//@RequestBody 없어도 됨
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        scheduleService.deleteSchedule(
                id,
                userId
        );
        return ResponseEntity.ok("일정을 삭제했습니다");
    }


}
