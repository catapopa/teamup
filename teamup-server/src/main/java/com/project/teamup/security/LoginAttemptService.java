package com.project.teamup.security;

import com.project.teamup.dao.UserRepository;
import com.project.teamup.model.User;
import com.project.teamup.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginAttemptService {
    private final int MAX_ATTEMPTS = 3;
    @Autowired
    private UserRepository userRepository;


    public void loginSucceeded(String username) {
        Optional<com.project.teamup.model.User> tempUser = userRepository.findByUsername(username);
        if (tempUser.isPresent()) {
            User user = tempUser.get();
            user.setFailedLoginAttempts(0);
            userRepository.save(user);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public void loginFailed(String username) {
        Optional<com.project.teamup.model.User> tempUser = userRepository.findByUsername(username);
        if (tempUser.isPresent()) {
            User user = tempUser.get();
            if (user.getRole() == UserRole.ADMIN) {
                return;
            }
            if(user.getFailedLoginAttempts() == null) {
                user.setFailedLoginAttempts(0);
            }
            user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
            userRepository.save(user);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public boolean isBlocked(Optional<com.project.teamup.model.User> user) {
        if (user.isPresent()) {
            Integer failedAttempts = user.get().getFailedLoginAttempts();
            if (failedAttempts == null) {
                failedAttempts = 0;
            }
            return failedAttempts >= MAX_ATTEMPTS;
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
