package com.word.library.request.controller;

import com.word.library.exception.WordCounterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestfulServiceErrorHandler {

    @ExceptionHandler(value = {WordCounterException.class})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> handleBadRequest(WordCounterException wordCounterException) {
        switch (wordCounterException.getErrorMessage().getErrorCode()) {
            case DATA_NOT_FOUND_EXCEPTION, BAD_WORD_EXCEPTION ->
                    new ResponseEntity<>(wordCounterException.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(wordCounterException.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
