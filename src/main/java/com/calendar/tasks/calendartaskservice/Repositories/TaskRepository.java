package com.calendar.tasks.calendartaskservice.Repositories;


import com.calendar.tasks.calendartaskservice.Models.Task;
import com.calendar.tasks.calendartaskservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository <Task , Long> {
    List<Task> findByUserAndDueDateBetween(User user, LocalDate startDate, LocalDate endDate);  // Changed from tdueDate to dueDate
    List<Task> findByUserOrderByDueDateAsc(User user);
}