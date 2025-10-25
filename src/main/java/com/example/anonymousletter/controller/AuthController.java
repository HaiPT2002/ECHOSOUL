package com.example.anonymousletter.controller;

import com.example.anonymousletter.model.User;
import com.example.anonymousletter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    @Autowired UserService userService;

    // Hiển thị form đăng ký
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // trả về tên file template: register.html
    }

    // Xử lý form đăng ký
    @PostMapping("/register")
    public String handleRegister(@ModelAttribute User user, Model model) {
        // Giả sử bạn có service lưu user
        try {
            userService.register(user);
            return "redirect:/login"; // sau khi đăng ký thì về trang login
        } catch (Exception e) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại!");
            return "register"; // hiển thị lại trang nếu có lỗi
        }
    }

    // Hiển thị form đăng nhập
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // login.html
    }
}



