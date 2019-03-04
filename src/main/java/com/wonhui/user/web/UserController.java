package com.wonhui.user.web;

import com.wonhui.entity.User;
import com.wonhui.user.repository.UserRepository;
import com.wonhui.user.service.UserService;
import com.wonhui.util.AES256Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@Controller
@Slf4j
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AES256Util aes256Util;

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
    public String sendEmail(@ModelAttribute User user) throws GeneralSecurityException, UnsupportedEncodingException {
        userService.sendMail(user);
        return "user/signUpStep1";
    }

    @GetMapping("/email/{signUpKey}")
    public String formSignUpSetPwd(Model model, @PathVariable("signUpKey") String signUpKey) throws GeneralSecurityException, UnsupportedEncodingException {
        model.addAttribute("user", userRepository.findById(aes256Util.decrypt(signUpKey)));
        return "user/signUpStep2";
    }
}
