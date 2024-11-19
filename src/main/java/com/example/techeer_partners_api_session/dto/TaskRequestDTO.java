package com.example.techeer_partners_api_session.dto;

// 클라이언트에게 요청 받은 DTO (가변)
public class TaskRequestDTO {

    private String title; // 요청할 할 일 내용
    private Boolean isDone; // 완료 여부

    // 기본 생성자
    public TaskRequestDTO() {}

    // 모든 필드를 받는 생성자 추가
    public TaskRequestDTO(String title, Boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }
}
