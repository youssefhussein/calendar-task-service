package com.calendar.tasks.calendartaskservice.Repositories;


import com.calendar.tasks.calendartaskservice.Models.Organization;
import com.calendar.tasks.calendartaskservice.Models.Task;
import com.calendar.tasks.calendartaskservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository <Task , Long> {
    List<Task> findByUserAndDueDateBetween(User user, LocalDate startDate, LocalDate endDate);  // Changed from tdueDate to dueDate
    List<Task> findByUserOrderByDueDateAsc(User user);
    
    @Query("SELECT t FROM Task t WHERE t.user.organization = :organization")
    List<Task> findByUsersInOrganization(Organization organization);
}