package com.example.anonymousletter.repository;

import com.example.anonymousletter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByWaiting(boolean isWaiting);
    User findByUsername(String username);
}
