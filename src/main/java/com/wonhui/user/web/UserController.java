package com.wonhui.user.web;

import com.wonhui.entity.User;
import com.wonhui.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String formUser(Model model){
        model.addAttribute("user", new User());
        return "user/signIn";
    }

    @GetMapping("/signUp")
    public String formSignUp(Model model){
        model.addAttribute("user", new User());
        return "user/signUp";
    }

    @PostMapping("/signUp")
    public String sendEmail(@ModelAttribute User user){
        userService.sendMail();
        return "user/emailConfirm";
    }
}
