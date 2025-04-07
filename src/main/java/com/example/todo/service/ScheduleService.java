package com.example.todo.service;

import com.example.todo.dto.ScheduleResponseDto;
import com.example.todo.entity.Schedule;
import com.example.todo.entity.User;
import com.example.todo.repository.ScheduleRepository;
import com.example.todo.repository.UserRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public ScheduleResponseDto save(Long userId, String title, String contents) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다")); //해당 id에 저장된 user 값 다 나옴 즉, user클래스
        Schedule schedule = new Schedule(user, title, contents);
        Schedule saveSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(user.getUsername(), saveSchedule.getTitle(), saveSchedule.getContents());


    }
    @Transactional
    public ScheduleResponseDto findById(Long id) {

        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        User user = schedule.getUser();


        return new ScheduleResponseDto(user.getUsername(), schedule.getTitle(), schedule.getContents());
    }
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, Long userId, String title, String contents) {


        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));//새로운 객체를 데이터베이스에 넣겠다고 말함
        //DB에 해당 id값이 있는지 확인한다.
        if (!schedule.getUser().getId().equals(userId)) {

            throw new IllegalArgumentException("권한이 없습니다.");
        }
        schedule.editScheduleForUser(title, contents);
        return new ScheduleResponseDto(schedule.getUser().getUsername(), schedule.getTitle(), schedule.getContents());
    }

    @Transactional
    public void deleteSchedule(Long id, Long userId) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (!schedule.getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("권한이 없습니다");
        }
        scheduleRepository.delete(schedule);//해당 id에 대한 객체를 넣어서 DB에 보낸다.
    }
//예외가 발생하면 throw에 있는 예외가 메세지를 가지고 위로 올라감, 그리고  exceptionhandler에서 맞는 클래스 찾음

}
