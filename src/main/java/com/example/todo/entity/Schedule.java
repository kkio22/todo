package com.example.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)//엔티티 클래스명_id => 외래 키 생성하는 법
    private User user;//객체 지향 관점: user 객체 바로 사용 가능 user.getUsername(), DB에서는 user의 식별자 id 숫자가 저장된


    public Schedule() {//필수

    }

    public Schedule(User user, String title, String contents) {
        this.user = user;
        this.title = title;
        this.contents = contents;
    }

    public Schedule(Long id, User user, String title, String contents) {
        this.id = id;
        this.user=user;
        this.title = title;
        this.contents = contents;
    }

}

