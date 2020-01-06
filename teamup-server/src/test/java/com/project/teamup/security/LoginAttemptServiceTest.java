package com.project.teamup.security;

import com.project.teamup.dao.UserRepository;
import com.project.teamup.model.User;
import com.project.teamup.model.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Junit Tests for the LoginAttempt Service.
 * UserRepository is being mocked.
 * @author Sonya
 * */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class LoginAttemptServiceTest {

    @InjectMocks
    private LoginAttemptService loginAttemptService;

    @Mock
    private UserRepository userRepository;


    @Test
    public void loginSucceeded() {
        User validUser = new User();
        validUser.setId(1L);
        validUser.setUsername("admin");
        validUser.setPassword("password");

        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(validUser));
        when(userRepository.findByUsername("invalid")).thenReturn(Optional.empty());
        loginAttemptService.loginSucceeded("admin");

        Mockito.verify(userRepository, times(1)).save(any());
        assertThrows(UsernameNotFoundException.class, () -> {
            loginAttemptService.loginSucceeded("invalid");
        });
    }

    @Test
    public void loginFailed() {
        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setPassword("password");
        user.setFailedLoginAttempts(null);
        user.setRole(UserRole.SUPERVISOR);

        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(user));
        loginAttemptService.loginFailed("admin");

        user.setRole(UserRole.ADMIN);
        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(user));
        loginAttemptService.loginFailed("admin");
        Mockito.verify(userRepository, times(1)).save(any());

        when(userRepository.findByUsername("invalid")).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> {
            loginAttemptService.loginFailed("invalid");
        });
    }

    @Test
    public void isBlocked() {
        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setPassword("password");
        user.setFailedLoginAttempts(null);

        assertFalse(loginAttemptService.isBlocked(Optional.of(user)));
        user.setFailedLoginAttempts(3);
        assertTrue(loginAttemptService.isBlocked(Optional.of(user)));
        assertThrows(UsernameNotFoundException.class, () -> {
            loginAttemptService.isBlocked(Optional.empty());
        });

    }
}