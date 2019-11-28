package com.project.teamup.controller;

import com.project.teamup.dto.UserDTO;
import com.project.teamup.mapper.UserMapper;
import com.project.teamup.model.User;
import com.project.teamup.service.EmailService;
import com.project.teamup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@CrossOrigin()
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/save")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO user, @RequestBody String token) {
        if (userService.isValidToken(token)) {
            return ResponseEntity.ok(userMapper.toDto(userService.save(userMapper.toEntity(user))));
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping(value = "/generateToken/{userId}")
    public ResponseEntity generateToken(@PathVariable Long userId) {
        User userEntity = userService.findById(userId).get();

        userService.createVerificationTokenForUser(userEntity);
        emailService.sendEmail(userEntity, EmailService.EmailType.FIRST_REGISTRATION);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<UserDTO> getAll() {
        return userMapper.toDtoList(userService.getAll());
    }

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .map(user -> userMapper.toDto(user))
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
