package com.example.anonymousletter.repository;

import com.example.anonymousletter.model.Stone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoneRepository extends JpaRepository<Stone, Integer> {}
