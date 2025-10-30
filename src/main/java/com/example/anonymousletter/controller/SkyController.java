package com.example.anonymousletter.controller;

import com.example.anonymousletter.model.Letter;
import com.example.anonymousletter.model.User;
import com.example.anonymousletter.service.LetterService;
import com.example.anonymousletter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SkyController {
    @Autowired
    private LetterService letterService;
    @Autowired
    private UserService userService;

    @RequestMapping("/sky-letter")
    public String skyLetter(Model model) {
        model.addAttribute("title", "ECHOSOUL - Bầu trời chung");
        model.addAttribute("page", "sky");
        model.addAttribute("intro", "");
        model.addAttribute("skyLetters", letterService.getLetters(true, 6));
        return "sky-letter";
    }
}
