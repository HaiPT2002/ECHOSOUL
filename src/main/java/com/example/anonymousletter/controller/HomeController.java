package com.example.anonymousletter.controller;

import com.example.anonymousletter.model.User;
import com.example.anonymousletter.service.LetterService;
import com.example.anonymousletter.service.StoneService;
import com.example.anonymousletter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private StoneService stoneService;
    @Autowired
    private UserService userService;

    // Trang chủ
    @GetMapping({"/", "/home"})
    public String home(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            User user = userService.getUserByUsername(userDetails);
            model.addAttribute("user", user);
            System.out.println(user.getUsername());
        }
        model.addAttribute("title", "ECHOSOUL - Trang Chủ");
        model.addAttribute("intro", "Nơi mà tâm sự của bạn sẽ được những vì sao đón nhận. Dù bao lâu đi nữa thì những tâm tư của bạn vẫn sẽ được ghi nhớ trên vũ trụ.");
        model.addAttribute("stones", stoneService.get5RandomStones());
        return "home"; // file templates/home.html
    }
}
