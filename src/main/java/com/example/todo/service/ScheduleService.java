package com.example.todo.service;

import com.example.todo.dto.ScheduleResponseDto;
import com.example.todo.entity.Schedule;
import com.example.todo.entity.User;
import com.example.todo.repository.ScheduleRepository;
import com.example.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;


    public ScheduleResponseDto save(Long userId, String title, String contents) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다")); //해당 id에 저장된 user 값 다 나옴 즉, user클래스
        //optional 매서드 .orElseThrow로 예외처리를 바로 할 수 있음. optional을 안 써도 이건 optional로 반환하기 때문에 생략 가능
        Schedule schedule = new Schedule(user, title, contents);
        Schedule saveSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(user.getUsername(), saveSchedule.getTitle(), saveSchedule.getContents());

    }

    public ScheduleResponseDto findById(Long id) {

        Schedule optionalSchedule = scheduleRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        User user = optionalSchedule.getUser();


        return new ScheduleResponseDto(user.getUsername(), optionalSchedule.getTitle(), optionalSchedule.getContents());
    }

    public ScheduleResponseDto updateSchedule(Long id, Long userId, String title, String contents) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Schedule schedule = new Schedule(id, user, title, contents);

        Schedule updateSchedule = scheduleRepository.save(schedule);//DB에 있는 해당 Id의 모든 column값 가지고 나옴

        return new ScheduleResponseDto(user.getUsername(),updateSchedule.getTitle(), updateSchedule.getContents());
    }


    public void deleteSchedule(Long id) {

        scheduleRepository.findById(id);
    }


}
