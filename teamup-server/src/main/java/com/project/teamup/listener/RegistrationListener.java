package com.project.teamup.listener;

import com.project.teamup.event.OnRegistrationCompleteEvent;
import com.project.teamup.model.User;
import com.project.teamup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private final UserService userService;

    private final MessageSource messages;

    private final JavaMailSender mailSender;

    public RegistrationListener(UserService userService, @Qualifier("messageSource") MessageSource messages, JavaMailSender mailSender) {
        this.userService = userService;
        this.messages = messages;
        this.mailSender = mailSender;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {
        this.confirmRegistration(onRegistrationCompleteEvent);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationTokenForUser(user, token);

        String recipientAddress = user.getEmail();
        System.out.println("Email: "+recipientAddress);
        String subject = "Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "/registrationConfirm/" + token;
        System.out.println("Reg Link: " + confirmationUrl);
        //String message = messages.getMessage("message.regSucc", null, event.getLocale());
        String message = "Your account was created succesfully. Activate your account by clicking the following link.\n";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\n" + "http://localhost:1212" + confirmationUrl);
        mailSender.send(email);
    }
}
