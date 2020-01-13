package com.project.teamup.dao;


import com.project.teamup.model.User;
import com.project.teamup.model.UserApprovalStatus;
import com.project.teamup.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "Select u from User u WHERE u.username = :username AND u.approvalStatus = 'APPROVED'")
    Optional<User> findByUsername(@Param("username") String username);
    Optional<List<User>> findUsersBySupervisorId(Long id);
    Optional<List<User>> findByRole(UserRole role);

}
