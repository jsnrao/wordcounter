package com.word.library.request.client.external;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TranslatorClient {
    private static final Map<String, String> words;

    static {
        words = new ConcurrentHashMap<>();
        words.put("flor","flower");
        words.put("blume","flower");
    }
    public String translate(String word){
       return words.get(word.toLowerCase());
    }
}
