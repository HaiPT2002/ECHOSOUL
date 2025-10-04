package com.example.anonymousletter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Trang chủ
    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("title", "ECHOSOUL - Trang Chủ");
        return "index"; // file templates/index.html
    }

    // Gửi thư
    @GetMapping("/letter")
    public String letterPage(Model model) {
        model.addAttribute("title", "Gửi Thư");
        return "letter"; // file templates/letter.html
    }

    // Bến thư
    @GetMapping("/inbox")
    public String inboxPage(Model model) {
        model.addAttribute("title", "Bến Thư");
        return "inbox"; // file templates/inbox.html
    }

    // Trạm Saomate
    @GetMapping("/saomate")
    public String saomatePage(Model model) {
        model.addAttribute("title", "Trạm Saomate");
        return "saomate"; // file templates/saomate.html
    }

    // Đăng nhập
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("title", "Đăng Nhập");
        return "login"; // file templates/login.html
    }

    // Đăng ký
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("title", "Đăng Ký");
        return "register"; // file templates/register.html
    }
}
