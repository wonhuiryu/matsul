package com.wonhui.user.web;

import com.wonhui.entity.User;
import com.wonhui.user.repository.UserRepository;
import com.wonhui.user.service.UserService;
import com.wonhui.util.AES256Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String signIn(Model model){
        model.addAttribute("user", new User());
        return "user/signIn";
    }

    @GetMapping("/email")
    public String formEmail(Model model){
        model.addAttribute("user", new User());
        return "user/email";
    }

    //메일전송
    @PostMapping("/email")
    public String formEmailCompl(@ModelAttribute User user) throws GeneralSecurityException, UnsupportedEncodingException {
        userService.sendMail(user);
        return "user/emailCompl";
    }

    //메일 포워딩받기
    @GetMapping("/signUp/{signUpKey}")
    public String formSignUp(Model model, @PathVariable("signUpKey") String signUpKey) throws GeneralSecurityException, UnsupportedEncodingException {
        model.addAttribute("user", userRepository.findById(aes256Util.decrypt(signUpKey)).get());
        return "user/signUp";
    }

    //가입완료
    @PostMapping("/signUp")
    public String formSignUpCompl(@Valid User user, BindingResult bindingResult) throws GeneralSecurityException, UnsupportedEncodingException {
        if (bindingResult.hasErrors()) {
            return "user/signUp";
        }
/*        user.setEmail(aes256Util.encrypt(user.getEmail()));*/
        user.setPassword(aes256Util.encrypt(user.getPassword()));
        userRepository.save(user);
        return "user/signUpCompl";
    }
}
