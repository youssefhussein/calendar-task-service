package com.calendar.tasks.calendartaskservice.Controllers;

import com.calendar.tasks.calendartaskservice.Models.Task;
import com.calendar.tasks.calendartaskservice.Models.User;
import com.calendar.tasks.calendartaskservice.Repositories.TaskRepository;
import com.calendar.tasks.calendartaskservice.Repositories.UserRepository;
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
    
    public TaskController(TaskRepository taskRepository, UserRepository UserRepository) {
        this.taskRepository = taskRepository;
        this.UserRepository = UserRepository;
    }
    
    @GetMapping("")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskRepository.findAll());
    }
    
    @PostMapping("/store")
    public ResponseEntity<Task> storeTask(@RequestBody Task newTask) {
        Task savedTask =  taskRepository.save(newTask);
         return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }
    @GetMapping("/findByUserAndDueDateBetween?")
    public ResponseEntity<List<Task>> findByUserAndDueDateBetween(
            @RequestParam Integer userId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        User user = UserRepository.findById(userId).orElseThrow();
        List<Task> foundTasks = taskRepository.findByUserAndDueDateBetween(user, startDate, endDate);
    return new ResponseEntity<>(foundTasks , OK);
    }
    @PostMapping("/findByUserOrderByDueDateAsc")
    public ResponseEntity findByUserOrderByDueDateAsc(@RequestBody Map<String,User> body) {
    User incomingUser = body.get("user");
        List<Task> foundTasks = taskRepository.findByUserOrderByDueDateAsc(incomingUser);
    return new ResponseEntity<>(foundTasks , OK);
    }
    
    /* from lab
    *  @PostMapping("Registration")
    public ResponseEntity createPost(@RequestBody Map<String,String> body){
        User myUser = new User();
        myUser.setName(body.get("name"));
        myUser.setUsername(body.get("username"));
        myUser.setPassword(body.get("password"));
        myUser.setDob(body.get("dob"));
        this.userRepositry.save(myUser);
        return new ResponseEntity(myUser, HttpStatus.CREATED);
    }
    *
    *  @GetMapping("")
    public ResponseEntity getUsers(){
        List<User> users= this.userRepositry.findAll();
        return new ResponseEntity(users, HttpStatus.OK);
    }
    * */
    
    
}
