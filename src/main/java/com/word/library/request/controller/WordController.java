package com.word.library.request.controller;

import com.word.library.exception.WordCounterException;
import com.word.library.request.service.WordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1.0/word")
@RestController
public class WordController {

    private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addWord(String word) throws WordCounterException {
        return ResponseEntity.ok().body(wordService.addWord(word));
    }

    @GetMapping("/retrieve/{word}")
    public ResponseEntity<Integer> retrieveWord(String Word) throws WordCounterException {
        return ResponseEntity.ok().body(wordService.retrieveWord(Word));
    }

}
