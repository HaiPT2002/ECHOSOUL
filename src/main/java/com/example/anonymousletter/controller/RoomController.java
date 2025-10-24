package com.example.anonymousletter.controller;

import com.example.anonymousletter.model.Message;
import com.example.anonymousletter.service.MessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/{roomId}")
    public String roomPage(@PathVariable Long roomId, Model model) {
        List<Message> messages = messageService.getMessages(roomId);
        model.addAttribute("messages", messages);
        model.addAttribute("roomId", roomId);
        return "saomate-room"; // templates/saomate-room.html
    }

    @PostMapping("/{roomId}/send")
    public String sendMessage(@PathVariable Long roomId,
                              @RequestParam String content,
                              HttpSession session) {
        Long senderId = (Long) session.getAttribute("userId");
        messageService.sendMessage(roomId, senderId, content);
        return "redirect:/room/" + roomId;
    }
}
