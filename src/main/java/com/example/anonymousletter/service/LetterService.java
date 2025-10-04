package com.example.anonymousletter.service;

import com.example.anonymousletter.model.Letter;
import com.example.anonymousletter.repository.LetterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class LetterService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, String> analyzeLetter(String content) {
        String url = "http://localhost:5000/analyze";

        Map<String, String> request = Map.of("content", content);

        Map<String, String> response =
                restTemplate.postForObject(url, request, Map.class);

        return response; // {"sentiment": "...", "encouragement": "..."}
    }

    @Autowired
    private LetterRepository letterRepository;

    public Map<String, String> createLetter(String content, boolean anonymous, int userId) {
        // gọi python phân tích
        var result = analyzeLetter(content);

        Letter letter = new Letter();
        letter.setContent(content);
        letter.setAnonymous(anonymous);
        letter.setUserId(userId);
        letterRepository.save(letter);

        return result;
    }
}
