package com.example.shixun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class pageController {

    @GetMapping("/")
    public String redirectToIndex() {
        return "redirect:/login.html";
    }
}
