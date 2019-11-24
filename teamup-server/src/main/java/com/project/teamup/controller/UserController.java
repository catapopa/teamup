package com.project.teamup.controller;

import com.project.teamup.dto.UserDTO;
import com.project.teamup.mapper.UserMapper;
import com.project.teamup.model.User;
import com.project.teamup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
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

    @PutMapping(value = "/createProfile/{id}")
    public UserDTO createUserProfile(@RequestBody UserDTO entity, @PathVariable("id") Long id, @RequestParam("bitmap") MultipartFile file) {
        try {
            byte[] array = file.getBytes();
            Blob blob = new SerialBlob(array);
            User user = userMapper.toEntity(entity);
            userService.createProfile(user, id, blob);
            return userMapper.toDto(userService.createProfile(user, id, blob));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
