package com.example.anonymousletter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SaoMateController {
    @GetMapping("/saomate")
    public String saomatePage(Model model) {
        model.addAttribute("title", "Trạm Saomate");
        return "saomate"; // file templates/saomate.html
    }
}
