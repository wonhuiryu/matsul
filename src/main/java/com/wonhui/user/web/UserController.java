package com.wonhui.user.web;

import com.wonhui.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/v1/users")
public class UserController {

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
}
