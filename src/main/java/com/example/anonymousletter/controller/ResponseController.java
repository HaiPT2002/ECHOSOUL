package com.example.anonymousletter.controller;

import com.example.anonymousletter.model.Letter;
import com.example.anonymousletter.model.User;
import com.example.anonymousletter.service.LetterService;
import com.example.anonymousletter.service.ResponseService;
import com.example.anonymousletter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/letters")
public class ResponseController {

    @Autowired
    private LetterService letterService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private UserService userService;

    @GetMapping("/reply/{id}")
    public String showReplyForm(@PathVariable("id") Long id, Model model) {
        Letter letter = letterService.getLetterById(id);
        model.addAttribute("title", "Phản hồi thư");
        model.addAttribute("intro", "");
        model.addAttribute("letter", letter);
        model.addAttribute("responses", responseService.getResponsesByLetter(id));
        return "reply";
    }

    @PostMapping("/reply/{id}")
    public String submitReply(@PathVariable("id") Long id,
                              @RequestParam("replyContent") String replyContent,
                              @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails);
        responseService.addResponse(id, replyContent, user);
        return "redirect:/letters/reply/" + id;
    }
}

