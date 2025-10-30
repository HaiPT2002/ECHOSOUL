package com.example.anonymousletter.controller;

import com.example.anonymousletter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class UserInfoController {
    @Autowired
    UserService userService;

    public String UserInfo(Model model) {
        model.addAttribute("title", "ECHOSOUL - Về Bản thân bạn");
        model.addAttribute("intro", "");
        return "user-info";
    }
}
