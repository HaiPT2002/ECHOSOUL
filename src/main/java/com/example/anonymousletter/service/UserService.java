package com.example.anonymousletter.service;

import com.example.anonymousletter.model.Role;
import com.example.anonymousletter.model.User;
import com.example.anonymousletter.repository.RoleRepository;
import com.example.anonymousletter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

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

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public String saveAvatar(User user, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File avatar trống");
        }

        // Tạo folder uploads/avatars nếu chưa tồn tại
        String uploadDir = "uploads/avatars/";
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        // Sinh tên file duy nhất
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String newFileName = UUID.randomUUID().toString() + extension;

        // Lưu file
        Path filePath = Paths.get(uploadDir, newFileName);
        Files.write(filePath, file.getBytes());

        // Trả về URL có thể dùng trong <img>
        return "/" + uploadDir + newFileName;
    }
}
