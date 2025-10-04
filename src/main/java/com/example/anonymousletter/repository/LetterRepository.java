package com.example.anonymousletter.repository;

import com.example.anonymousletter.model.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterRepository extends JpaRepository<Letter, Long> {}
