package com.project.teamup.controller;

import com.project.teamup.dto.UserDTO;
import com.project.teamup.dto.UserWithPictureDTO;
import com.project.teamup.mapper.UserMapper;
import com.project.teamup.model.FilterCriterias;
import com.project.teamup.model.User;
import com.project.teamup.model.UserLanguage;
import com.project.teamup.model.UserStatus;
import com.project.teamup.service.EmailService;
import com.project.teamup.security.JwtUserDetailsService;
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
import java.util.Map;


@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO user, @RequestBody String token) {
        if (userService.isValidToken(token)) {
            user.setStatus(UserStatus.VERIFIED);
            return ResponseEntity.ok(userMapper.toDto(userService.save(userMapper.toEntity(user))));
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PostMapping(value = "/save")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO user) {
        user.setStatus(UserStatus.NOT_VERIFIED);
        return ResponseEntity.ok(userMapper.toDto(userService.save(userMapper.toEntity(user))));
    }

    @PostMapping(value = "/generateToken/{userId}")
    public ResponseEntity generateToken(@PathVariable Long userId) {
        User userEntity = userService.findById(userId).get();

        userService.createVerificationTokenForUser(userEntity);

        if (userEntity.getLanguage() == null){
            emailService.sendEmail(userEntity, EmailService.EmailType.FIRST_REGISTRATION_EN);
        }else if (userEntity.getLanguage().equals(UserLanguage.ENGLISH)){
            emailService.sendEmail(userEntity, EmailService.EmailType.FIRST_REGISTRATION_EN);
        }else if (userEntity.getLanguage().equals(UserLanguage.GERMAN)){
            emailService.sendEmail(userEntity,EmailService.EmailType.FIRST_REGISTRATION_DE);
        }else if (userEntity.getLanguage().equals(UserLanguage.ROMANIAN)){
            emailService.sendEmail(userEntity,EmailService.EmailType.FIRST_REGISTRATION_RO);
        }


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

    @GetMapping("/{username}")
    public UserDTO findByUsername(@PathVariable("username") String username) {
        return userService.findByUsername(username)
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

    @GetMapping("/filter")
    public List<UserDTO> filterUsers(@RequestBody Map<FilterCriterias, String> criterias) {
        return userMapper.toDtoList(userService.filterUser(criterias));
    }

    @PostMapping(value = "/activate")
    public String activateUser(@RequestParam(name = "adminUsername") String adminUsername,
                               @RequestParam(name = "emplUsername") String emplUsername) {
        return userService.activateUser(adminUsername, emplUsername);
    }

    @GetMapping("/{id}/assignedEmployees")
    public List<UserDTO> getAssignedEmployees(@PathVariable Long id) {
        return userService.getAssignedUsers(id)
                .map(list -> userMapper.toDtoList(list))
                .orElse(null);
    }

    @PostMapping(value = "/update")
    public UserDTO updateUser(@RequestBody UserWithPictureDTO userWithPictureDTO) {
        //userWithPictureDTO workaround
        return userMapper.toDto(userService.updateUser(userMapper.toEntity(userWithPictureDTO.getUserToUpdate()), userWithPictureDTO.getProfilePicture()));
    }


    @GetMapping(value = "/getUserByUsername/{username}")
    public UserDTO getUserByUsername(@PathVariable String username) {
        return userMapper.toDto(userService.getUserByUsername(username));
    }
}
