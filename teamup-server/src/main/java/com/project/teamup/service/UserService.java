package com.project.teamup.service;

import com.project.teamup.dao.UserRepository;
import com.project.teamup.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public User updateUser(User user){
        Optional<User> storedUser = userRepository.findById(user.getId());
        User userToBeUpdated;

        //can perform update only if user exists in db
        if (storedUser.isPresent()){
            userToBeUpdated = storedUser.get();

            if(user.getFirstName() != null && !user.getFirstName().equals("")){
                userToBeUpdated.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null && !user.getLastName().equals("")){
                userToBeUpdated.setLastName(user.getLastName());
            }
            if(user.getBirthDate() != null){
                userToBeUpdated.setBirthDate(user.getBirthDate());
            }
            if(user.getUsername() != null){
                userToBeUpdated.setUsername(user.getUsername());
            }
            if(user.getPicture() != null){
                userToBeUpdated.setPicture(user.getPicture());
            }
            if(user.getPassword() != null && !user.getPassword().equals("")){
                userToBeUpdated.setPassword(user.getPassword());
            }

            userRepository.save(userToBeUpdated);
            return userToBeUpdated;
        }
        return null;
    }
}
