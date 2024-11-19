package com.example.techeer_partners_api_session.Controller;

import com.example.techeer_partners_api_session.TaskService.TaskService;
import com.example.techeer_partners_api_session.dto.TaskRequestDTO;
import com.example.techeer_partners_api_session.dto.TaskResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    // 생성자 주입
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Task 생성
    @PostMapping
    public ResponseEntity<Map<String, Object>> createTask(@Valid @RequestBody TaskRequestDTO taskRequestDTO) {
        taskService.createTask(taskRequestDTO);

        // 응답 메시지 구성
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "success");
        response.put("message", "할 일이 생성되었습니다.");

        // 201 Created 상태 코드와 함께 응답 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    // 모든 Task 조회
    @GetMapping // GET 요청이 오면 해당 메서드 실행
    public ResponseEntity<Map<String, Object>> getAllTasks() {
        List<TaskResponseDTO> alltaskDTO = taskService.getAllTasks();  // TaskDTO로 반환

        // 응답 데이터 구성
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "success");
        response.put("message", "모든 일이 조회되었습니다.");
        response.put("data", alltaskDTO);

        // 200 OK 상태 코드와 함께 반환
        return ResponseEntity.ok(response);
    }

    // 완료된 Task 조회
    @GetMapping("/completed") // /tasks/completed로 GET 요청이 들어오면 해당 메서드 호출
    public ResponseEntity<Map<String, Object>> getCompletedTasks() {
        List<TaskResponseDTO> completedTaskDTO = taskService.getCompletedTasks();

        // 응답 데이터 구성
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "success");
        response.put("message", "완료 된 일이 조회되었습니다.");
        response.put("data", completedTaskDTO);

        // 200 OK 상태 코드와 함께 반환
        return ResponseEntity.ok(response);
    }

    // 미완료된 Task 조회
    @GetMapping("/incomplete") // /tasks/incomplete로 GET 요청이 들어오면 해당 메서드 호출
    public ResponseEntity<Map<String, Object>> getIncompleteTasks() {

        List<TaskResponseDTO> incompleteTaskDTO = taskService.getIncompleteTasks();

        // 응답 데이터 구성
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "success");
        response.put("message", "미완료 된 일이 조회되었습니다.");
        response.put("data", incompleteTaskDTO);

        // 200 OK 상태 코드와 함께 반환
        return ResponseEntity.ok(response);
    }

    // Task 수정
    @PatchMapping("/{id}") // /tasks/{id}로 PATCH 요청이 들어오면 해당 메서드 호출
    public ResponseEntity<Map<String, Object>> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDTO taskDTO) {

        TaskRequestDTO partialUpdateTaskDTO = taskService.partialUpdateTask(id, taskDTO);

        // 응답 메시지 구성
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "success");
        response.put("message", "할 일이 수정되었습니다.");
        response.put("data", null);

        // 200 OK 상태 코드와 함께 응답 메시지 반환
        return ResponseEntity.ok(response);
    }

    // Task 삭제
    @DeleteMapping("/{id}") // /tasks/{id}로 DELETE 요청이 들어오면 해당 메서드 호출
    public ResponseEntity<Map<String, Object>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);

        // 응답 메시지 구성
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "success");
        response.put("message", "할 일이 삭제되었습니다.");
        response.put("data", null);

        // 200 OK 상태 코드와 함께 응답 메시지 반환
        return ResponseEntity.ok(response);
    }
}