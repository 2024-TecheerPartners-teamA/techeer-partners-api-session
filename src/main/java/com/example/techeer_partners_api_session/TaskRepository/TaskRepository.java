package com.example.techeer_partners_api_session.TaskRepository;

import com.example.techeer_partners_api_session.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // 기본적인 CRUD 메서드는 JpaRepository에서 제공
    // 추가적인 쿼리 메서드는 필요에 따라 선언할 수 있음

    // 완료된 할 일만 조회
    List<Task> findByIsDoneTrue(); // isDone == true인 할 일 목록만 조회하는 메서드입니다.

    // 미완료된 할 일만 조회
    List<Task> findByIsDoneFalse(); // isDone == false인 할 일 목록만 조회하는 메서드입니다.

}