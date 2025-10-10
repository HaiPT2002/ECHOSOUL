package com.example.anonymousletter.controller;

import com.example.anonymousletter.service.SaomateService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/saomate")
public class SaoMateController {

    @Autowired
    private SaomateService SaomateService;
    @Autowired
    private SaomateService saomateService;

    @GetMapping
    public String saomateHome(Model model) {
        model.addAttribute("waitting", false);
        return "saomate_home"; // file templates/saomate_home.html
    }

    // When user press Start meeting
    @PostMapping
    public  String ready(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");

        String roomId = saomateService.findPartner(userId);

        if (roomId == null) {
            model.addAttribute("waitting", true);
            return "saomate_home"; // stay on the same page and show waiting message
        } else {
            return "redirect:/saomate/room/" + roomId;
        }
    }

    // Room chat
    @GetMapping("/room/{roomId}")
    public String chatRoom(@PathVariable String roomId, Model model) {
        model.addAttribute("roomId", roomId);
        return "saomate_room";
    }

    // End chat session
    @PostMapping("/end")
    public String endChat(@RequestParam String roomId) {
        saomateService.endChat(roomId);
        return "redirect:/saomate" + roomId;
    }
}
