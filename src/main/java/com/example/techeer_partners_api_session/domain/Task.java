package com.example.techeer_partners_api_session.domain;

import com.example.techeer_partners_api_session.dto.TaskRequestDTO;
import com.example.techeer_partners_api_session.dto.TaskResponseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {

    @Id // 데이터베이스 테이블의 기본키임을 나타냄.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성 ID
    private Long id;
    private String title; // 할 일 내용
    private Boolean isDone = false; // 기본값 : false

    // 기본 생성자
    public Task() {
    }

    // 모든 필드를 받는 생성자
    public Task(String title, Boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public TaskRequestDTO toRequestDTO() {
        return new TaskRequestDTO(this.title, this.isDone);
    }

    public TaskResponseDTO toResponseDTO() {
        return new TaskResponseDTO(this.id, this.title, this.isDone);
    }

    // title 수정(비지니스 로직)
    public void updateTitle(String newTitle) {
        this.title = newTitle;
    }

    // isDone 수정(비지니스 로직)
    public void updateIsDone(Boolean newIsDone) {
        this.isDone = newIsDone;
    }

}
