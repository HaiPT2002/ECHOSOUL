package com.example.anonymousletter.service;

import com.example.anonymousletter.model.Stone;
import com.example.anonymousletter.repository.StoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
