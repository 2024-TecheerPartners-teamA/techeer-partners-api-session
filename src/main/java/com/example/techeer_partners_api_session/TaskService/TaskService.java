package com.example.techeer_partners_api_session.TaskService;

import com.example.techeer_partners_api_session.TaskRepository.TaskRepository;
import com.example.techeer_partners_api_session.domain.Task;
import com.example.techeer_partners_api_session.dto.TaskRequestDTO;
import com.example.techeer_partners_api_session.dto.TaskResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    // 생성자 주입
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository; // 초기화
    }

    // Task 생성
    public void createTask(TaskRequestDTO taskRequestDTO) {
        // Task 엔티티를 직접 생성
        Task task = new Task(taskRequestDTO.getTitle(), taskRequestDTO.getIsDone());

        // 엔티티 저장
        Task savedTask = taskRepository.save(task);
    }

    // 모든 Task 조회
    public List<TaskResponseDTO> getAllTasks() {
        // Task 리스트를 TaskDTO 리스트로 변환하여 반환
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> task.toResponseDTO())
                .toList();
    }

    // 완료된 할 일 조회
    public List<TaskResponseDTO> getCompletedTasks() {
        List<Task> tasks = taskRepository.findByIsDoneTrue();
        return tasks.stream()
                .map(task -> task.toResponseDTO())
                .toList();
    }

    // 미완료된 할 일 조회
    public List<TaskResponseDTO> getIncompleteTasks() {
        List<Task> tasks = taskRepository.findByIsDoneFalse();
        return tasks.stream()
                .map(task -> task.toResponseDTO())
                .toList();
    }

    // ID로 특정 Task 일부 수정
    public TaskRequestDTO partialUpdateTask(Long id, TaskRequestDTO taskDTO) {
        // 엔티티를 찾고, 없으면 예외 발생
        Task updatedTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // title 수정
        if (taskDTO.getTitle() != null) {
            updatedTask.updateTitle(taskDTO.getTitle());
        }

        // isDone 수정
        if (taskDTO.getIsDone() != null) {
            updatedTask.updateIsDone(taskDTO.getIsDone());
        }

        // 수정된 엔티티를 저장
        taskRepository.save(updatedTask);

        // DTO로 변환하여 반환
        return updatedTask.toRequestDTO();
    }

    // ID로 특정 Task 삭제
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
