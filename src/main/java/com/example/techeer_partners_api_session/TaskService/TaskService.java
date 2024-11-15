package com.example.techeer_partners_api_session.TaskService;

import com.example.techeer_partners_api_session.TaskRepository.TaskRepository;
import com.example.techeer_partners_api_session.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    //생성자 주입
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository; // 초기화
    }

    // Task 생성
    public Task createTask(Task task) { // 단일 객체로 반환
        return taskRepository.save(task);
    }

    // 모든 Task 조회
    public List<Task> getAllTasks() { // 리스트로 반환
        return taskRepository.findAll();
    }

    // 완료된 할 일 조회
    public List<Task> getCompletedTasks() {
        return taskRepository.findByIsDoneTrue();
    }

    // 미완료된 할 일 조회
    public List<Task> getIncompleteTasks() {
        return taskRepository.findByIsDoneFalse();
    }

    // ID로 특정 Task 일부 수정
    public Task partialUpdateTask(Long id, Map<String, Object> updates) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (updates.containsKey("title")) { // 요청에서 title 필드가 포함되어 있는지 확인.
            existingTask.setTitle((String) updates.get("title"));
        }
        if (updates.containsKey("isDone")) { // 요청에 isDone 필드가 포함되어 있는지 확인.
            existingTask.setIsDone((Boolean) updates.get("isDone"));
        }

        return taskRepository.save(existingTask); // 업데이트된 Task 저장
    }

    // ID로 특정 Task 삭제
    public void deleteTask(Long id) { // 반환 값 필요 없음.
        taskRepository.deleteById(id);
    }
}
