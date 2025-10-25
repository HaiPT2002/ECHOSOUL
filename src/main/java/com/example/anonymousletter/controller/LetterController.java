package com.example.anonymousletter.controller;

import com.example.anonymousletter.model.Letter;
import com.example.anonymousletter.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.Map;

@Controller
@RequestMapping("/letters")
public class LetterController {

    @Autowired
    private LetterService letterService;

    // Form submit truyền thống (Thymeleaf render lại)
    @PostMapping("/send")
    public String sendLetter(@ModelAttribute Letter letter,
                             @RequestParam(defaultValue = "false") boolean anonymous,
                             HttpSession session,
                             Model model) {

        Integer userId = (Integer) session.getAttribute("userId");

        // Gọi service xử lý
        Map<String, String> result = letterService.createLetter(
                letter.getContent(),
                anonymous,
                userId
        );

        // Reset form + đưa kết quả ra model
        model.addAttribute("letter", new Letter());
        model.addAttribute("encouragement", result.get("encouragement"));
        model.addAttribute("isAnonymous", anonymous);

        return "write_letter"; // reload lại trang với dữ liệu
    }

    // API dành cho AJAX (fetch JSON)
    @PostMapping("/send-ajax")
    @ResponseBody
    public Map<String, String> sendLetterAjax(@RequestBody Letter letter,
                                             @RequestParam(defaultValue = "false") boolean anonymous,
                                              HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");

        return letterService.createLetter(letter.getContent(), anonymous, userId);
    }
}


