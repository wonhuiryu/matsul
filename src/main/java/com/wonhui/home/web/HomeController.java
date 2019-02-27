package com.wonhui.home.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wonhuiryu on 2018-05-12.
 */
@Controller
@RequestMapping("")
public class HomeController {

    @GetMapping("")
    public String home() {
        return "home/home";
    }
}
