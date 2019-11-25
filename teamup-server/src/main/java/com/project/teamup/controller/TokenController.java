package com.project.teamup.controller;

import com.project.teamup.dto.UserDTO;
import com.project.teamup.event.OnRegistrationCompleteEvent;
import com.project.teamup.mapper.UserMapper;
import com.project.teamup.model.User;
import com.project.teamup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping(TokenController.BASE_URL)
public class TokenController extends HttpServlet {
    public static final String BASE_URL="/user";

    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
    public String registerUserAccount(@RequestBody UserDTO accountDTO, BindingResult result, HttpServletRequest request, Errors errors){

        User registered = userService.save(userMapper.toEntity(accountDTO));
        if (registered == null) {
            return "Error saving user in db.";
        }

        try {
            String appUrl = TokenController.BASE_URL+"/registration";
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
        } catch (Exception me) {
            return "Email Error";
        }

        return "User added successfully, Email sent successfully!";
    }
}

/*System.out.println(accountDTO.getEmail() + accountDTO.getUsername());
        if (result.hasErrors()){
            return new ModelAndView("registration", "user", accountDTO);
        }

        User registered = userService.save(userMapper.toEntity(accountDTO));
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }

        try {
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
        } catch (Exception me) {
            return new ModelAndView("emailError", "user", accountDTO);
        }
        return new ModelAndView("successRegister", "user", accountDTO);*/
