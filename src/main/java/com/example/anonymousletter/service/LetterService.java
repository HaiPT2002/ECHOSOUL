package com.example.anonymousletter.service;

import com.example.anonymousletter.model.Letter;
import com.example.anonymousletter.repository.LetterRepository;
import com.example.anonymousletter.repository.StoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class LetterService {
    @Autowired
    private LetterRepository letterRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private StoneRepository stoneRepository;

    @Autowired
    private ResponseService responseService;


    public Map<String, String> analyzeLetter(String content) {
        String url = "http://localhost:5000/analyze";

        Map<String, String> request = Map.of("content", content);

        Map<String, String> response =
                restTemplate.postForObject(url, request, Map.class);

        return response; // {"sentiment": "...", "encouragement": "..."}
    }

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

    public List<Letter> getLetters(Boolean anonymous, int number) {
        Pageable pageable = PageRequest.of(0, number, Sort.by("createdAt").descending());
        if (anonymous != null && anonymous) {
            return letterRepository.findByAnonymousTrue(pageable);
        }
        return letterRepository.findAll(pageable).getContent();
    }

    public Letter getLetterById(Long id) {
        return letterRepository.findById(id).orElse(null);
    }
}
