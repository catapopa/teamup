package com.project.teamup.controller;

import com.project.teamup.dto.UserDTO;
import com.project.teamup.mapper.UserMapper;
import com.project.teamup.model.User;
import com.project.teamup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @PostMapping(value = "/save")
    public UserDTO saveUser(@RequestBody UserDTO user) {
        return userMapper.toDto(userService.save(userMapper.toEntity(user)));
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

    @PostMapping(value = "/update")
    public UserDTO updateUser(@RequestBody UserDTO user) {
        return userMapper.toDto(userService.updateUser(userMapper.toEntity(user)));
    }

}
