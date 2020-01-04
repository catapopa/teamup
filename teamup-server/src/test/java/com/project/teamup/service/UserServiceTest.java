package com.project.teamup.service;

import com.project.teamup.dao.UserRepository;
import com.project.teamup.dao.VerificationTokenRepository;
import com.project.teamup.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Junit Tests for the User Service.
 * User Repository is mocked.
 * @author Sonya
 * */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private VerificationTokenRepository tokenRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private MailService mailService;

    private User user1;
    private User user2;
    private VerificationToken verificationToken;

    @Test
    public void getAll() {
        when(userRepository.findAll()).thenReturn(null);
        assertNull(userService.getAll());
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        when(userRepository.findAll()).thenReturn(users);
        assertEquals(2, userService.getAll().size());

        User user3 = new User();
        users.add(user3);
        assertNotEquals(2, userService.getAll().size());
    }

    @Test
    public void save() {
        user1 = new User();
        user1.setId(1L);
        user1.setPassword("password");

        user2 = new User();
        user2.setId(2L);
        user2.setPassword("password");
        when(userRepository.save(user1)).thenReturn(user1);
        when(userRepository.save(user2)).thenReturn(user2);
        when(bCryptPasswordEncoder.encode(any())).thenReturn("encr pass");

        assertEquals("encr pass", userService.save(user1).getPassword());
        assertEquals("encr pass", userService.save(user2).getPassword());
    }

    @Test
    public void delete() {
        userService.delete(1L);
        userService.delete(2L);
        Mockito.verify(userRepository).deleteById(1L);
        Mockito.verify(userRepository, times(2)).deleteById(any());
    }

    @Test
    public void findById() {
        when(userRepository.findById(3L)).thenReturn(Optional.empty());
        user2 = new User();
        user2.setId(2L);
        user2.setUsername("admin");
        when(userRepository.findById(2L)).thenReturn(Optional.of(user2));

        assertTrue(userService.findById(2L).isPresent());
        assertEquals(user2.getUsername(), userService.findById(2L).get().getUsername());
        assertFalse(userService.findById(3L).isPresent());
    }

    @Test
    public void createProfile() throws SQLException {
        User user1 = new User();
        user1.setId(1L);
        user1.setPicture(null);
        user1.setLanguage(UserLanguage.ENGLISH);
        user1.setSeniority(UserSeniority.MANAGER);
        user1.setLocation(new Location(1L, "Romania", "Transilvania", "Cluj-Napoca"));
        user1.setCompany(new Company(1L, "Mhp"));
        List<UserSkill> skills = new ArrayList<>();
        skills.add(new UserSkill());
        user1.setSkills(skills);
        List<ProjectUserExperience> projectUserExperiences = new ArrayList<>();
        projectUserExperiences.add(new ProjectUserExperience());
        user1.setProjectExperiences(projectUserExperiences);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.save(user1)).thenReturn(user1);
        assertEquals(user1, userService.createProfile(user1, 1L, null));
        byte[] bytes;
        Blob blob = mockBlob();
        bytes = blob.getBytes(1L, (int) blob.length());
        user1.setPicture(bytes);
        assertEquals(user1, userService.createProfile(user1, 1L, blob));
    }

    @Test
    public void filterUser() {
    }

    @Test
    public void findByUsername() {
        user1 = new User();
        user1.setUsername("username");
        user1.setId(1L);
        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user1));
        when(userRepository.findByUsername("username 1")).thenReturn(Optional.empty());
        assertEquals("username", userService.findByUsername("username").get().getUsername());
        assertEquals(user1.getId(), userService.findByUsername("username").get().getId());
        assertFalse(userService.findByUsername("username 1").isPresent());
    }

    @Test
    public void activateUser() {
        User activatedUser = new User();
        activatedUser.setUsername("activated");
        activatedUser.setFailedLoginAttempts(0);
        User deactivatedUser = new User();
        deactivatedUser.setUsername("deactivated");
        deactivatedUser.setFailedLoginAttempts(3);
        when(bCryptPasswordEncoder.encode(any())).thenReturn("encr pass");
        User admin = new User();
        admin.setUsername("admin");

        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(admin));
        when(userRepository.findByUsername("activated")).thenReturn(Optional.of(activatedUser));
        when(userRepository.findByUsername("deactivated")).thenReturn(Optional.of(deactivatedUser));
        when(userRepository.findByUsername("invalid")).thenReturn(Optional.empty());

        assertEquals("The user is already activated!", userService.activateUser("admin","activated"));
        assertEquals("Invalid username of admin/employee!", userService.activateUser("admin","invalid"));
        assertEquals("User was successfully activated and a mail with the new password was sent to him!",
                userService.activateUser("admin","deactivated"));
    }

    @Test
    public void generateNewPassword(){
        assertNotNull(userService.generateNewPassword());
        assertEquals(6, userService.generateNewPassword().length());
    }

    @Test
    public void generateActivationLink() {
        user1 = new User();
        user1.setId(1L);
        verificationToken = new VerificationToken();
        verificationToken.setId(1L);
        verificationToken.setToken("abc");
        when(tokenRepository.findByUser(user1)).thenReturn(verificationToken);
        assertEquals("http://localhost:4200/registration/abc", userService.generateActivationLink(user1));
    }

    @Test
    public void createVerificationTokenForUser() {

    }

    @Test
    public void isValidToken() {
        String token1 = "akjqiojkm";
        String token2 = "jixkjfkewj";
        String token3 = "huheurjfik";
        VerificationToken verificationToken1 = new VerificationToken();
        verificationToken1.setId(1L);
        verificationToken1.setToken(token2);
        verificationToken1.setExpiryDate(new Timestamp(1451927902));
        VerificationToken verificationToken2 = new VerificationToken();
        verificationToken2.setId(2L);
        verificationToken2.setToken(token3);
        verificationToken2.setExpiryDate(new Timestamp(1609780702));

        when(tokenRepository.findByToken(token1)).thenReturn(null);
        when(tokenRepository.findByToken(token2)).thenReturn(verificationToken1);
        when(tokenRepository.findByToken(token3)).thenReturn(verificationToken2);

        assertFalse(userService.isValidToken(token1));
        assertTrue(userService.isValidToken(token2));
        assertTrue(userService.isValidToken(token3));
    }

    @Test
    public void getAssignedUsers() {
        user1 = new User();
        user1.setId(1L);
        user1.setUsername("supervisor");

        user2 = new User();
        user2.setId(2L);
        user2.setUsername("employee 1");

        User user3 = new User();
        user3.setId(3L);
        user3.setUsername("employee 2");

        user1.setSupervisor(user2);
        user1.setSupervisor(user3);
        user1.setRole(UserRole.CUSTOMER);
        List<User> assignedUsers = new ArrayList<>();
        assignedUsers.add(user2);
        assignedUsers.add(user3);
        when(userRepository.findById(5L)).thenReturn(Optional.empty());
        assertFalse(userService.getAssignedUsers(5L).isPresent());
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        assertFalse(userService.getAssignedUsers(1L).isPresent());
        user1.setRole(UserRole.SUPERVISOR);
        when(userRepository.findUsersBySupervisorId(1L)).thenReturn(Optional.of(assignedUsers));
        assertEquals(2, userService.getAssignedUsers(1L).get().size());
    }

    private Blob mockBlob(){
       return new Blob() {
            @Override
            public long length(){
                return 0;
            }

            @Override
            public byte[] getBytes(long pos, int length){
                return new byte[0];
            }

            @Override
            public InputStream getBinaryStream(){
                return null;
            }

            @Override
            public long position(byte[] pattern, long start){
                return 0;
            }

            @Override
            public long position(Blob pattern, long start){
                return 0;
            }

            @Override
            public int setBytes(long pos, byte[] bytes){
                return 0;
            }

            @Override
            public int setBytes(long pos, byte[] bytes, int offset, int len){
                return 0;
            }

            @Override
            public OutputStream setBinaryStream(long pos){
                return null;
            }

            @Override
            public void truncate(long len){

            }

            @Override
            public void free(){

            }

            @Override
            public InputStream getBinaryStream(long pos, long length){
                return null;
            }
        };
    }
}