package com.example.anonymousletter.controller;

import com.example.anonymousletter.model.User;
import com.example.anonymousletter.service.SaomateService;
import com.example.anonymousletter.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/saomate")
public class SaoMateController {

    @Autowired
    private SaomateService saoMateService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String saomateHome(Model model) {
        model.addAttribute("title", "ECHOSOUL - Kết nối SaoMate");
        model.addAttribute("page", "saomate");
        model.addAttribute("intro", "");
        model.addAttribute("waiting", false);
        return "saomate";
    }

    @PostMapping("/ready")
    public String ready(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails);
        Long roomId = saoMateService.findPartner(user.getUserId());

        if (roomId == null) {
            model.addAttribute("waiting", true);
            return "saomate";
        } else {
            return "redirect:/room/" + roomId;
        }
    }
}
