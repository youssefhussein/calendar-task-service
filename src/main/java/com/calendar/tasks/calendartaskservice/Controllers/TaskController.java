package com.calendar.tasks.calendartaskservice.Controllers;

import com.calendar.tasks.calendartaskservice.Models.Task;
import com.calendar.tasks.calendartaskservice.Repositories.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/tasks")
public class TaskController {
    private final TaskRepository taskRepository;
    
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @GetMapping("")
    public ResponseEntity getAllTasks() {
        return ResponseEntity.ok(taskRepository.findAll());
    }
}
