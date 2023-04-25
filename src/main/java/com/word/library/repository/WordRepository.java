package com.word.library.repository;

import com.word.library.exception.WordCounterException;


public interface WordRepository {
    Integer retrieveWord(String reference) throws WordCounterException;
    String addWord(String reference) throws WordCounterException;

}
