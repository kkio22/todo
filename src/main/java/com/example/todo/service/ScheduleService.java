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

        Schedule optionalSchedule = scheduleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        User user = optionalSchedule.getUser();


        return new ScheduleResponseDto(user.getUsername(), optionalSchedule.getTitle(), optionalSchedule.getContents());
    }

    public ScheduleResponseDto updateSchedule(Long id, Long userId, String title, String contents) {
        //기존에 있는지 확인해야 함

        Schedule findschedule = scheduleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));//새로운 객체를 데이터베이스에 넣겠다고 말함

        if (findschedule.getUser().getId().equals(userId)) {//schedule 데이터베이스에 저장된 userId와  내가 집어 넣은 userId가 같은지 확인 처리
            //값을 setter하는건 좋지 않음
            //매서드를 만들어서 해야 함
            //if문이 ture일 때, 매서드 실행 매개변수로 원하는 값을 schedule Entity에 집어 넣음
            findschedule.editScheduleForUser(title, contents);
            //jpa에서 repository를 이용해 데이터를 조회하면 DB의 데이터를 Entity 객체로 변환해서 준다. 그래서 변수에는 해당 entity객체가 있어서 자료형도 객체 클래스가 됨. 그래서 클래스를 선언하면, 해당 클래스 매서드 쓸 수 있는 것 처럼 변수명.매서드명으로 사용가능한 것
            Schedule updateSchedule = scheduleRepository.save(findschedule);//DB에 있는 해당 Id의 모든 column값 가지고 나옴
            return new ScheduleResponseDto(findschedule.getUser().getUsername(), findschedule.getTitle(), findschedule.getContents());
        } else throw new IllegalArgumentException("권한이 없습니다.");

    }


    public void deleteSchedule(Long id, Long userId) {
        Schedule findSchedule = scheduleRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
       //optional에서 값을 꺼내고 없으면 예외처리
        if(!findSchedule.getUser().getId().equals (userId)){
            throw new IllegalArgumentException("권한이 없습니다");
        }
        scheduleRepository.delete(findSchedule);//해당 id에 대한 객체니까 가능!
    }


}
