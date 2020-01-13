package com.project.teamup.security;

import com.project.teamup.dao.UserRepository;
import com.project.teamup.model.UserRole;
import com.project.teamup.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginAttemptService loginAttemptService;
    @Autowired
    private MailService mailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.project.teamup.model.User> user = userRepository.findByUsername(username);
        if (loginAttemptService.isBlocked(user)) {
            Optional<List<com.project.teamup.model.User>> admins = userRepository.findByRole(UserRole.ADMIN);
            admins.ifPresent(users -> mailService.sendEmailDeactivation(users.get(0), user.get()));
            return new User(user.get().getUsername(), user.get().getPassword(), true, true, true, false, AuthorityUtils.NO_AUTHORITIES);
        }
        if (user.isPresent()) {
            return new User(user.get().getUsername(), user.get().getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}