package com.project.teamup.security;

import com.project.teamup.dao.UserRepository;
import com.project.teamup.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Junit Tests for the JwtUserDetails Service.
 * UserRepository and LoginAttemptService are
 * being mocked.
 * @author Sonya
 * */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class JwtUserDetailsServiceTest {

    @InjectMocks
    private JwtUserDetailsService jwtUserDetailsService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LoginAttemptService loginAttemptService;

    @Test
    public void loadUserByUsername() {
        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setPassword("password");

        User blockedUser = new User();
        blockedUser.setId(2L);
        blockedUser.setUsername("supervisor");
        blockedUser.setPassword("password");

        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(user));
        when(userRepository.findByUsername("empl")).thenReturn(Optional.empty());
        when(userRepository.findByUsername("supervisor")).thenReturn(Optional.of(blockedUser));
        when(loginAttemptService.isBlocked(Optional.of(blockedUser))).thenReturn(true);
        when(loginAttemptService.isBlocked(Optional.of(user))).thenReturn(false);

        assertTrue(jwtUserDetailsService.loadUserByUsername("supervisor").isAccountNonExpired());
        assertEquals("admin", jwtUserDetailsService.loadUserByUsername("admin").getUsername());
        assertEquals("password", jwtUserDetailsService.loadUserByUsername("admin").getPassword());
        assertThrows(UsernameNotFoundException.class, () -> {
            jwtUserDetailsService.loadUserByUsername("empl");
        });
    }
}