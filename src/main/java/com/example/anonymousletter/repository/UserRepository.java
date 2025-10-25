package com.example.anonymousletter.repository;

import com.example.anonymousletter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByWaiting(boolean isWaiting);
    Optional<User> findByUsername(String username);
}
