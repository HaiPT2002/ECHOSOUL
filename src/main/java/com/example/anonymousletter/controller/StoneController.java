package com.example.anonymousletter.controller;

import com.example.anonymousletter.model.Stone;
import com.example.anonymousletter.service.StoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/stone")
public class StoneController {

    @Autowired
    private StoneService stoneService;

    @GetMapping("/detail")
    public String stoneDetail(@RequestParam("id") int id, Model model) {
        Stone stone = stoneService.getStoneById(id);
        model.addAttribute("stone", stone);
        return "stone_detail"; // stone_detail.html
    }
}

