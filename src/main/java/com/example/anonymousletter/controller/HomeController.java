package com.example.anonymousletter.controller;

import com.example.anonymousletter.service.LetterService;
import com.example.anonymousletter.service.StoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    private StoneService stoneService;
    @Autowired
    private LetterService LetterService;

    // Trang chủ
    @GetMapping({"/", "/home"})
    public String home(Model model) {

        model.addAttribute("sky", LetterService.anonymousLetter());
        model.addAttribute("title", "ECHOSOUL - Trang Chủ");
        model.addAttribute("stones", stoneService.getStones());
        return "home"; // file templates/home.html
    }

    // Bến thư
    @GetMapping("/inbox")
    public String inboxPage(Model model) {
        model.addAttribute("title", "Bến Thư");
        return "inbox"; // file templates/inbox.html
    }

}
