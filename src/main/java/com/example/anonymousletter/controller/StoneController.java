package com.example.anonymousletter.controller;

import com.example.anonymousletter.model.Stone;
import com.example.anonymousletter.model.User;
import com.example.anonymousletter.repository.StoneRepository;
import com.example.anonymousletter.service.StoneService;
import com.example.anonymousletter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/stones")
public class StoneController {

    @Autowired
    private StoneRepository stoneRepository;
    @Autowired
    private StoneService stoneService;
    @Autowired
    private UserService userService;

    // Trang danh sách các viên đá
    @GetMapping
    public String listStones(Model model) {
        List<Stone> stones = stoneRepository.findAll();
        model.addAttribute("intro", "");
        model.addAttribute("stones", stones);
        return "stone"; // templates/stones.html
    }

    // Trang chi tiết từng viên đá, lấy theo tên
    @GetMapping("/{stoneName}")
    public String stoneDetails(@PathVariable String stoneName, Model model) {
        Stone stone = stoneService.getStonesByName(stoneName);
        model.addAttribute("stone", stone);
        return "stone-detail"; // templates/stone-detail.html
    }

    @PostMapping("/select/{stoneId}")
    public String selectStone(@PathVariable int stoneId,
                              @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            User user = userService.getUserByUsername(userDetails);
            stoneService.assignStoneToUser(stoneId, user);
        }
        return "redirect:/stones"; // quay lại trang danh sách đá
    }
}

