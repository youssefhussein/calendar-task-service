package com.calendar.tasks.calendartaskservice.Repositories;

import com.calendar.tasks.calendartaskservice.Models.Organization;
import com.calendar.tasks.calendartaskservice.Models.User;
import com.calendar.tasks.calendartaskservice.Models.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    Optional<User> findByUsername(String username);
    List<User> findByOrganization(Organization organization);
    
    List<User> findByOrganizationAndUserType(Organization organization, UserType userType);
}
