package com.example.anonymousletter.controller;

import com.example.anonymousletter.model.User;
import com.example.anonymousletter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login"; // file login.html
    }

    @PostMapping
    public String login(@ModelAttribute User user, Model model) {
        boolean authenticated = userService.authenticate(user.getUsername(), user.getPassword());
        if (authenticated) {
            return "redirect:/"; // về trang chủ sau khi login thành công
        } else {
            model.addAttribute("error", "Sai tài khoản hoặc mật khẩu!");
            return "login";
        }
    }
}
