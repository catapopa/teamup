package com.project.teamup.service;

import com.project.teamup.dao.UserRepository;
import com.project.teamup.dao.VerificationTokenRepository;
import com.project.teamup.model.User;
import com.project.teamup.model.UserRole;
import com.project.teamup.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final static int VERIFICATION_TOKEN_VALIDITY = 60 * 60 * 1000;
    private final static String WEB_APP_URL = "http://localhost:4200/registration/";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private VerificationTokenRepository tokenRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User save(@Valid User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String generateActivationLink(User user){
        VerificationToken verificationToken = tokenRepository.findByUser(user);

        return WEB_APP_URL+verificationToken.getToken();
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
        }else {
            if (verificationToken.getExpiryDate().after(new Timestamp(new Date().getTime()))){
                tokenRepository.delete(verificationToken);
                tokenRepository.save(new VerificationToken(null, UUID.randomUUID().toString(), user, new Timestamp(new Date().getTime()+VERIFICATION_TOKEN_VALIDITY)));
            }
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

    /**
     * Returns a list of users, to which the user with the corresponding ID is responsible of.
     * @param id {@link Long}, to which the entity is identified by and represents a user.
     * @return an {@link List<User>} of the corresponding User, if the {@link User} with the id exists and it is a {SUPERVISOR},
     * an {@link Optional} that is empty, if the user does not exist/is not a supervisor or is responsible of no one.
     * @author Sebastian
     */
    public Optional<List<User>> getAssignedUsers(Long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            if (user.get().getRole().equals(UserRole.SUPERVISOR)){
                return userRepository.findUsersBySupervisorId(id);
            }
        } else {
            return Optional.empty();
        }
        return Optional.empty();
    }
}
