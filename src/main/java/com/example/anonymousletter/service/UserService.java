package com.example.anonymousletter.service;

import com.example.anonymousletter.model.Role;
import com.example.anonymousletter.model.User;
import com.example.anonymousletter.repository.RoleRepository;
import com.example.anonymousletter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Đăng ký
    public User register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Tên đăng nhập đã tồn tại!");
        }

        Role defaultRole = roleRepository.findByRoleName("user")
                .orElseThrow(() -> new RuntimeException("Không tìm thấy vai trò mặc định"));

        user.setRole(defaultRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    // Check premium
    public boolean isPremium(User user) {
        if (user == null || user.getRole() == null) return false;

        String roleName = user.getRole().getRoleName().toLowerCase();
        return roleName.equals("premium") || roleName.equals("admin");
    }

    public User getUserByUsername(UserDetails userDetails) {
        return userRepository.findByUsername(userDetails.getUsername()).orElse(null);
    }
}
