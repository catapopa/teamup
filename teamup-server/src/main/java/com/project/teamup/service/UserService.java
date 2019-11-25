package com.project.teamup.service;

import com.project.teamup.dao.UserRepository;
import com.project.teamup.model.User;
import org.apache.commons.lang3.RandomStringUtils;
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
    @Autowired
    private MailService mailService;

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

    /**
     * Activates a blocked user by setting him
     * the number of login attempts to 0 and a new generated
     * password which will be sent by email.
     * @param adminUsername username of the admin who activates the blocked user
     * @param emplUsername username of the user that becomes a new password
     *                    via email from the admin
     * */
    public String activateUser(String adminUsername, String emplUsername){
        Optional<User> deactivatedUser = userRepository.findByUsername(emplUsername);
        Optional<User> admin = userRepository.findByUsername(adminUsername);
        if(deactivatedUser.isPresent() && admin.isPresent()){
            User userToBeActivated = deactivatedUser.get();
            String newPassword = generateNewPassword();
            String subject = mailService.newPasswordSubject();
            String message = mailService.sendMessageNewPassword(userToBeActivated,
                    admin.get(), newPassword);
            mailService.sendMail(deactivatedUser.get().getEmail(), subject, message);
            userToBeActivated.setFailedLoginAttempts(0); //activates the user
            userToBeActivated.setPassword(bcryptEncoder.encode(newPassword));
            userRepository.save(userToBeActivated);
            return "User was successfully activated and a mail with the new password was sent to him!";
        }
        return "The user is already activated!";
    }

    /**
     * Generates a random string of length 6
     * which represents the new password for
     * a user who must be activated.
     * */
    private String generateNewPassword(){
        int length = 6;
        boolean useLetters = true;
        boolean useNumbers = true;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }
}
