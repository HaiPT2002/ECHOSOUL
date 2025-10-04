package com.example.anonymousletter.controller;

import com.example.anonymousletter.model.User;
import com.example.anonymousletter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // file register.html
    }

    @PostMapping
    public String register(@ModelAttribute User user, Model model) {
        try {
            userService.register(user);
            return "redirect:/login"; // đăng ký xong thì chuyển về trang login
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}


