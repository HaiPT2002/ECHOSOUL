package com.example.anonymousletter.controller;

import com.example.anonymousletter.model.Stone;
import com.example.anonymousletter.model.User;
import com.example.anonymousletter.service.StoneService;
import com.example.anonymousletter.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/profile")
public class UserInfoController {
    @Autowired
    private UserService userService;
    @Autowired
    private StoneService stoneService;

    @GetMapping
    public String userInfo(Model model, @AuthenticationPrincipal UserDetails userDetails,
    HttpServletRequest request) {
        User user = userService.getUserByUsername(userDetails);
        model.addAttribute("user", user);
        model.addAttribute("title", "ECHOSOUL - Về Bản thân bạn");
        model.addAttribute("intro", "");

        // CSRF token
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        model.addAttribute("_csrf", csrfToken);

        return "user-info";
    }

    @PostMapping("/edit")
    @ResponseBody
    public Map<String, Object> editProfile(@AuthenticationPrincipal UserDetails userDetails,
                                           @RequestBody Map<String, String> payload) {
        User user = userService.getUserByUsername(userDetails);
        Stone stone = stoneService.getStonesByName(payload.get("stone"));

        user.setUsername(payload.get("username"));
        user.setZodiac(payload.get("zodiac"));
        user.setHobby(payload.get("hobbies"));
        user.setStone(stone);

        userService.saveUser(user); // Lưu vào DB

        return Map.of("success", true);
    }

    @PostMapping("/avatar")
    public String uploadAvatar(@AuthenticationPrincipal UserDetails userDetails,
                               @RequestParam("avatar") MultipartFile file,
                               RedirectAttributes redirectAttributes) {
        User user = userService.getUserByUsername(userDetails);

        try {
            String url = userService.saveAvatar(user, file); // Lưu và trả về URL
            user.setAvatarUrl(url);
            userService.saveUser(user);
            redirectAttributes.addFlashAttribute("message", "Cập nhật ảnh đại diện thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Không thể tải ảnh lên");
        }

        return "redirect:/profile";
    }


}
