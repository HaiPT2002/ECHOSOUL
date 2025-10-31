package com.example.anonymousletter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserInfoController {

    @GetMapping("/profile")
    public String UserInfo(Model model) {
        model.addAttribute("title", "ECHOSOUL - Về Bản thân bạn");
        model.addAttribute("intro", "");
        return "user-info";
    }
}
