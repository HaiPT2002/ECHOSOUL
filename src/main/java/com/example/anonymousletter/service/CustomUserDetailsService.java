package com.example.anonymousletter.service;

import com.example.anonymousletter.model.User;
import com.example.anonymousletter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword()) // phải là password đã được BCrypt mã hóa
                .roles(String.valueOf(user.getRole())) // VD: ADMIN, USER
                .build();
    }

    // Dùng để mã hóa mật khẩu khi cần
    public static String encodePassword(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }
}
