package com.example.anonymousletter.repository;

import com.example.anonymousletter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
