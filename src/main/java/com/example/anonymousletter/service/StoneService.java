package com.example.anonymousletter.service;

import com.example.anonymousletter.model.Stone;
import com.example.anonymousletter.repository.StoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class StoneService {
    @Autowired
    private StoneRepository stoneRepository;

    public List<Stone> getStones() {
        return stoneRepository.findAll();
    }

    public Stone getStoneById(int id) {
        return stoneRepository.findById(id).orElse(null);
    }

    public List<Stone> get5RandomStones() {
        List<Stone> stones = stoneRepository.findAll();
        Random random = new Random();
        List<Stone> copy = new java.util.ArrayList<>(stones);
        java.util.Collections.shuffle(copy, random);
        return copy.subList(0, 5);
    }
}

