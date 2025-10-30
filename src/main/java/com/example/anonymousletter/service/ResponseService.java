package com.example.anonymousletter.service;

import com.example.anonymousletter.model.Letter;
import com.example.anonymousletter.model.Response;
import com.example.anonymousletter.model.User;
import com.example.anonymousletter.repository.LetterRepository;
import com.example.anonymousletter.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private LetterRepository letterRepository;

    public List<Response> getResponsesByLetter(Long letterId) {
        return responseRepository.findByLetterId(letterId);
    }

    public void addResponse(Long letterId, String content, User user) {
        Letter letter = letterRepository.findById(letterId).orElseThrow();
        Response response = Response.builder()
                .content(content)
                .letter(letter)
                .user(user)
                .build();
        responseRepository.save(response);
    }
}

