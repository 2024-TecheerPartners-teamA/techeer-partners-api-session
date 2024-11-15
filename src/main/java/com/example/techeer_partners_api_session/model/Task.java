package com.example.techeer_partners_api_session.model;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public Boolean getIsDone() {
        return isDone;
    }
}
