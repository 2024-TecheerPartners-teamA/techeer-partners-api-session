package com.example.techeer_partners_api_session.dto;

// 서버가 응답을 주는 DTO (불변)
public class TaskResponseDTO {

    private final Long id; // 작업 식별자
    private final String title; // 할 일 제목
    private final Boolean isDone; // 완료 여부


    // 모든 필드를 받는 생성자 추가
    public TaskResponseDTO(Long id,String title, Boolean isDone) {
        this.id = id;
        this.title = title;
        this.isDone = isDone;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getIsDone() {
        return isDone;
    }

}
