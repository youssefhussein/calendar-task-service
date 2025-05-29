package com.calendar.tasks.calendartaskservice.Controllers;

import com.calendar.tasks.calendartaskservice.Models.Task;
import com.calendar.tasks.calendartaskservice.Models.User;
import com.calendar.tasks.calendartaskservice.Repositories.TaskRepository;
import com.calendar.tasks.calendartaskservice.Repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import static org.springframework.http.HttpStatus.OK;

//CROSSING MY FINGERS
@RestController
@RequestMapping("/service/tasks")
public class TaskController {
    private final TaskRepository taskRepository;
    private final UserRepository UserRepository;
    private final UserRepository userRepository;
    
    public TaskController(TaskRepository taskRepository, UserRepository UserRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.UserRepository = UserRepository;
        this.userRepository = userRepository;
    }
  
    @GetMapping("")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskRepository.findAll());
    }
    // DONE
    @PostMapping("/store")
    public Task storeTask(@RequestBody @Valid Task task , @RequestParam Integer userId) {
        task.setUser(UserRepository.findById(userId).orElseThrow());
        return taskRepository.save(task);
    }
    @GetMapping("/findByUserAndDueDateBetween")
    public ResponseEntity<List<Task>> findByUserAndDueDateBetween(
            @RequestParam Integer userId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        User user = UserRepository.findById(userId).orElseThrow();
        List<Task> foundTasks = taskRepository.findByUserAndDueDateBetween(user, startDate, endDate);
    return new ResponseEntity<>(foundTasks , OK);
    }
    @GetMapping("/findByUserOrderByDueDateAsc")
    public ResponseEntity<List<Task>> findByUserOrderByDueDateAsc(@RequestParam Integer userId) {
    User incomingUser = userRepository.findById(userId).orElseThrow();
    List<Task> foundTasks = taskRepository.findByUserOrderByDueDateAsc(incomingUser);
    return new ResponseEntity<>(foundTasks , OK);
    }
    
    
    
}
