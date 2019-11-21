package com.project.teamup.service;

import com.project.teamup.dao.UserRepository;
import com.project.teamup.dto.UserDTO;
import com.project.teamup.mapper.UserMapper;
import com.project.teamup.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private UserMapper userMapper;


    public List<UserDTO> getAll() {
        return userMapper.toUserDtoList(userRepository.findAll());
    }

    public User save(UserDTO userDto) {
        User user = userMapper.toUser(userDto);
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
