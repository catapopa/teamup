package com.project.teamup.service;

import com.project.teamup.dao.UserRepository;
import com.project.teamup.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User createProfile(User user, Long id, Blob blob) {
        Optional<User> optionalUser = userRepository.findById(id);
        User userToUpdate = new User();
        if (optionalUser.isPresent()) {
            userToUpdate = optionalUser.get();
        }

        if (user.getPicture() != null) {
            try {
                byte[] bytes;
                bytes = blob.getBytes(1L, (int) blob.length());
                userToUpdate.setPicture(bytes);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (user.getLanguage() != null) {
            userToUpdate.setLanguage(user.getLanguage());
        }
        if (user.getSeniority() != null) {
            userToUpdate.setSeniority(user.getSeniority());
        }
        if (user.getLocation() != null) {
            userToUpdate.setLocation(user.getLocation());
        }
        if (user.getCompany() != null) {
            userToUpdate.setCompany(user.getCompany());
        }
        if (user.getSkills().size() != 0) {
            userToUpdate.setSkills(user.getSkills());
        }
        if (user.getProjectExperiences().size() != 0) {
            userToUpdate.setProjectExperiences(user.getProjectExperiences());
        }

        return userRepository.save(userToUpdate);
    }
}
