package com.example.anonymousletter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AnonymousLetterApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnonymousLetterApplication.class, args);
    }
}
