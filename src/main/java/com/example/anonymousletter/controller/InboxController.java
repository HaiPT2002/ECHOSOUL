package com.example.anonymousletter.controller;

import com.example.anonymousletter.model.User;
import com.example.anonymousletter.service.LetterService;
import com.example.anonymousletter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InboxController {
    @Autowired
    private LetterService letterService;
    @Autowired
    private UserService userService;

    @RequestMapping("/inbox")
    public String inboxPage(Model model, User user) {
        if (!userService.isPremium(user)) {
            return "redirect:/login";
        }
        model.addAttribute("title", "Bến Thư");
        model.addAttribute("intro", "Nơi đây là nơi những tâm tư cần lời hồi đáp của ai đó, cũng là nơi bạn có thể giúp họ giải tỏa những cảm xúc chứa trong những bức thư ấy.");
        model.addAttribute("letters", letterService.getLetters(false, 12));
        return "inbox";
    }
}
