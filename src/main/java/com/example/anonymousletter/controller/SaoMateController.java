package com.example.anonymousletter.controller;

import com.example.anonymousletter.service.SaomateService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/saomate")
public class SaoMateController {

    @Autowired
    private SaomateService saoMateService;

    @GetMapping
    public String saomateHome(Model model) {
        model.addAttribute("title", "ECHOSOUL - Kết nối SaoMate");
        model.addAttribute("page", "saomate");
        model.addAttribute("intro", "");
        model.addAttribute("waiting", false);
        return "saomate";
    }

    @PostMapping("/ready")
    public String ready(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");

        Long roomId = saoMateService.findPartner(userId);

        if (roomId == null) {
            model.addAttribute("waiting", true);
            return "saomate";
        } else {
            return "redirect:/saomate/room/" + roomId;
        }
    }

    @GetMapping("/room/{roomId}")
    public String chatRoom(@PathVariable Long roomId, Model model) {
        model.addAttribute("roomId", roomId);
        return "saomate_room";
    }

    @PostMapping("/end")
    public String endChat(@RequestParam Long roomId, HttpSession session) {
        saoMateService.endChat(roomId);
        Long userId = (Long) session.getAttribute("userId");
        return "redirect:/saomate";
    }
}
