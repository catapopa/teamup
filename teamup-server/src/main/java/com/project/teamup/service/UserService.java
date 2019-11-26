package com.project.teamup.service;

import com.project.teamup.dao.UserRepository;
import com.project.teamup.dao.VerificationTokenRepository;
import com.project.teamup.model.User;
import com.project.teamup.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final static int VERIFICATION_TOKEN_VALIDITY = 60 * 60 * 1000;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private VerificationTokenRepository tokenRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        System.out.println(user.getEmail() + " " + user.getPassword());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String generateActivationLink(User user){
        VerificationToken verificationToken = tokenRepository.findByUser(user);

        return "http://localhost:4200/registration/"+verificationToken.getToken();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public void createVerificationTokenForUser(User user) {
        VerificationToken verificationToken = tokenRepository.findByUser(user);

        if (verificationToken == null){
            tokenRepository.save(new VerificationToken(null, UUID.randomUUID().toString(), user, new Timestamp(new Date().getTime()+VERIFICATION_TOKEN_VALIDITY)));
        }
    }

    public boolean isValidToken(String token) {

        VerificationToken verificationToken = tokenRepository.findByToken(token);

        if (verificationToken != null && verificationToken.getExpiryDate().before(new Timestamp(new Date().getTime()))){
            tokenRepository.delete(verificationToken);
            return true;
        }

        return false;
    }
}
