package com.project.teamup.service;

import com.google.common.collect.Lists;
import com.project.teamup.model.User;
import com.project.teamup.model.UserLanguage;
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

/**
 * Sends mails to different users of the application.
 * @author Sonya
 * @since 23.11.2019
 * */
@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    @Qualifier("emailTemplateEngine")
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String emailFromAddress;

    private MimeMessageHelper generateMimeMessage(String emailTemplate, String subject, List<String> recipients,
                                                  Map<String,Object> variables) throws MessagingException {
        Context context = new Context();

        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,"UTF-8");

        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setFrom(emailFromAddress);
        mimeMessageHelper.setTo(recipients.toArray(new String[recipients.size()]));

        context.setVariable("title",subject);
        variables.forEach(context::setVariable);

        mimeMessageHelper.setText(templateEngine.process(emailTemplate, context),true);

        return mimeMessageHelper;
    }

    /**
     * Sends an email to the blocked user when
     * an admin generates a new password for him.
     *
     * @param blockedUser user whose password must
     *                    be generated
     * @param admin the admin who generates the password
     * @param newPassword the new generated password
     * */
    void sendEmailActivation(User admin, User blockedUser, String newPassword){
        Map<String, Object> variables = new HashMap<>();
        List<String> recipients = Lists.newArrayList(blockedUser.getEmail());
        variables.put("firstName", blockedUser.getFirstName());
        variables.put("lastName", blockedUser.getLastName());
        variables.put("username", blockedUser.getUsername());
        variables.put("password", newPassword);
        variables.put("adminFirstName", admin.getFirstName());
        variables.put("adminLastName", admin.getLastName());
        EmailType emailType = EmailType.USER_ACTIVATION_EN; //default language
        UserLanguage language = blockedUser.getLanguage();
        if(language != null) {
            switch (language) {
                case GERMAN:
                    emailType = EmailType.USER_ACTIVATION_DE;
                    break;
                case ENGLISH:
                    emailType = EmailType.USER_ACTIVATION_EN;
                    break;
                case ROMANIAN:
                    emailType = EmailType.USER_ACTIVATION_RO;
                    break;
            }
        }
        sendMail(variables, recipients, emailType);
    }

    /**
     * Sends an email to the supervisor when an employee has
     * created his profile, so that he can validate/invalidate
     * it.
     *
     * @param user the user who has created his
     *                        profile
     * @param urlProfile the link where the superviser can
     *                   access the new created profile
     * */
    //TODO call this method when a profile is created
    void sendEmailProfileCreated(User user, String urlProfile){
        Map<String, Object> variables = new HashMap<>();
        User supervisor = user.getSupervisor();
        List<String> recipients = Lists.newArrayList(supervisor.getEmail());
        variables.put("firstName", supervisor.getFirstName());
        variables.put("lastName", supervisor.getLastName());
        variables.put("username", user.getUsername());
        variables.put("email", user.getEmail());
        variables.put("employeeLink", urlProfile);
        EmailType emailType = EmailType.PROFILE_CREATION_EN; //default language
        UserLanguage language = supervisor.getLanguage();
        if(language != null) {
            switch (language) {
                case GERMAN:
                    emailType = EmailType.PROFILE_CREATION_DE;
                    break;
                case ENGLISH:
                    emailType = EmailType.PROFILE_CREATION_EN;
                    break;
                case ROMANIAN:
                    emailType = EmailType.PROFILE_CREATION_RO;
                    break;
            }
        }
        try {
            MimeMessageHelper message = generateMimeMessage(emailType.template, emailType.getSubject(),
                    recipients,variables);
            mailSender.send(message.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Sends the email to an admin when a user's account
     * has been deactivated to inform him that he has to
     * generate a new password for him.
     *
     * @param blockedUser the user whose password must
     *                    be generated
     * @param admin the admin who has to generate
     *              a new password for the user
     * */
    public void sendEmailDeactivation(User admin, User blockedUser){
        Map<String, Object> variables = new HashMap<>();
        List<String> recipients = Lists.newArrayList(admin.getEmail());
        variables.put("firstName", admin.getFirstName());
        variables.put("lastName", admin.getLastName());
        variables.put("username", blockedUser.getUsername());
        variables.put("emailBlockedUser", blockedUser.getEmail());
        EmailType emailType = EmailType.USER_DEACTIVATION_EN; //default language
        UserLanguage language = blockedUser.getLanguage();
        if(language != null) {
            switch (language) {
                case GERMAN:
                    emailType = EmailType.USER_DEACTIVATION_DE;
                    break;
                case ENGLISH:
                    emailType = EmailType.USER_DEACTIVATION_EN;
                    break;
                case ROMANIAN:
                    emailType = EmailType.USER_DEACTIVATION_RO;
                    break;
            }
        }
        sendMail(variables, recipients, emailType);
    }

    void sendEmailNewAccount(User newUser){
        Map<String, Object> variables = new HashMap<>();
        List<String> recipients = Lists.newArrayList(newUser.getEmail());
        variables.put("username", newUser.getUsername());
        variables.put("password", newUser.getPassword());
        variables.put("link", "http://localhost:4200/login");
        EmailType emailType = EmailType.USER_NEW_ACCOUNT_EN; //default language
        UserLanguage language = newUser.getLanguage();
        if(language != null) {
            switch (language) {
                case GERMAN:
                    emailType = EmailType.USER_NEW_ACCOUNT_DE;
                    break;
                case ENGLISH:
                    emailType = EmailType.USER_NEW_ACCOUNT_EN;
                    break;
                case ROMANIAN:
                    emailType = EmailType.USER_NEW_ACCOUNT_RO;
                    break;
            }
        }
        sendMail(variables, recipients, emailType);
    }

    private void sendMail(Map<String, Object> variables, List<String> recipients, EmailType emailType) {
        try {
            MimeMessageHelper message = generateMimeMessage(emailType.template, emailType.getSubject(),
                    recipients, variables);
            mailSender.send(message.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Getter
    @AllArgsConstructor
    public enum EmailType{

        FIRST_REGISTRATION("html/firstRegistration","Registration Confirmation"),
        USER_ACTIVATION_DE("html/activationMailDe", "Konto Aktivierung Bestaetigung"),
        USER_ACTIVATION_EN("html/activationMailEn","Account Activation Confirmation"),
        USER_ACTIVATION_RO("html/activationMailRo","Confirmare activare cont"),
        USER_DEACTIVATION_DE("html/deactivationMailDe","Konto deaktiviert"),
        USER_DEACTIVATION_RO("html/deactivationMailRo","Cont dezactivat"),
        USER_DEACTIVATION_EN("html/deactivationMailEn","Account deactivated"),
        USER_NEW_ACCOUNT_DE("html/newAccountDe", "Neues Konto TeamUp"),
        USER_NEW_ACCOUNT_RO("html/newAccountRo", "Cont nou TeamUp"),
        USER_NEW_ACCOUNT_EN("html/newAccountEn", "New account TeamUp"),
        PROFILE_CREATION_DE("html/createdProfileDe", "Neues Mitarbeiter Konto Erstellung"),
        PROFILE_CREATION_EN("html/createdProfileEn", "New employee profile creation"),
        PROFILE_CREATION_RO("html/createdProfileRo", "Creare profil angajat nou");

        private String template;
        private String subject;
    }
}