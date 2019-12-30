package com.project.teamup.service;

import com.google.common.collect.Lists;
import com.project.teamup.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    @Qualifier("emailTemplateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private UserService userService;

    @Value("${spring.mail.username}")
    private String emailFromAddress;

    private MimeMessageHelper generateMimeMessage(String emailTemplate, String subject, List<String> recipients, Map<String,Object> variables) throws MessagingException {
        Context context = new Context();

        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,"UTF-8");

        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setFrom(emailFromAddress);
        mimeMessageHelper.setTo(recipients.toArray(new String[recipients.size()]));

        context.setVariable("title",subject);
        variables.forEach(context::setVariable);

        mimeMessageHelper.setText(templateEngine.process(emailTemplate,context),true);

        return mimeMessageHelper;
    }

    public void sendEmail(User user, EmailType emailType){
        Map<String, Object> variables = new HashMap<>();
        List<String> recipients = Lists.newArrayList(user.getEmail());

        switch (emailType){
            case FIRST_REGISTRATION_EN:
                variables.put("activationLink",userService.generateActivationLink(user));
                break;
            case FIRST_REGISTRATION_DE:
                variables.put("activationLink",userService.generateActivationLink(user));
                break;
            case FIRST_REGISTRATION_RO:
                variables.put("activationLink",userService.generateActivationLink(user));
                break;
        }

        try {
            MimeMessageHelper message = generateMimeMessage(emailType.template,emailType.getSubject(),recipients,variables);
            javaMailSender.send(message.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Getter
    @AllArgsConstructor
    public enum EmailType{

        FIRST_REGISTRATION_EN("html/firstRegistrationMailEn","Registration Confirmation"),
        FIRST_REGISTRATION_DE("html/firstRegistrationMailDe","Bestätigung der Anmeldung"),
        FIRST_REGISTRATION_RO("html/firstRegistrationMailRo","Confirmarea înregistrării");

        private String template;
        private String subject;
    }
}
