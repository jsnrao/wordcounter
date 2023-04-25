package com.word.library.request.service;


import com.word.library.exception.WordCounterException;
import com.word.library.repository.WordRepository;
import org.springframework.stereotype.Service;

@Service
public class WordService {
    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public String addWord(String wordReference) throws WordCounterException {
       return wordRepository.addWord(wordReference);
    }

    public Integer retrieveWord(String word) throws WordCounterException {
        return wordRepository.retrieveWord(word);
    }

}
