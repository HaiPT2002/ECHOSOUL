package com.example.anonymousletter.service;

import com.example.anonymousletter.model.Stone;
import com.example.anonymousletter.model.User;
import com.example.anonymousletter.repository.StoneRepository;
import com.example.anonymousletter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class StoneService {
    @Autowired
    private StoneRepository stoneRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Stone> getStones() {
        return stoneRepository.findAll();
    }

    public Stone getStoneById(int id) {
        return stoneRepository.findById(id).orElse(null);
    }

    public List<Stone> get4RandomStones() {
        List<Stone> stones = stoneRepository.findAll();
        Random random = new Random();
        List<Stone> copy = new java.util.ArrayList<>(stones);
        java.util.Collections.shuffle(copy, random);
        return copy.subList(0, 4);
    }
    public Stone getStonesByName(String stoneName) {
        return stoneRepository.findByStoneName(stoneName);
    }



    public void assignStoneToUser(int stoneId, User user) {
        Stone stone = stoneRepository.findById(stoneId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy viên đá"));
        user.setStone(stone);
        userRepository.save(user);
    }
}

