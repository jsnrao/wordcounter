package com.word.library.repository;


import com.word.library.common.CommonConstant;
import com.word.library.common.ErrorMessage;
import com.word.library.common.WordErrorCode;
import com.word.library.exception.WordCounterException;
import com.word.library.request.client.external.TranslatorClient;
import com.word.library.util.WordUtil;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class WordRepositoryStub implements WordRepository {

    private final TranslatorClient translator;

    public WordRepositoryStub(TranslatorClient translator) {
        this.translator = translator;
    }

    private static Map<String, Integer> words;

    public static synchronized Map<String, Integer> getMap() {
        if (words == null) {
            words = new ConcurrentHashMap<>();
        }
        return words;
    }

    public Integer retrieveWord(String reference) throws WordCounterException {
        String wordToSearch = stringTrimAndConvertToLowerCase(reference);
        if (StringUtils.isNotBlank(wordToSearch) && (words != null) && Objects.nonNull(words.get(wordToSearch)) || isWordAvailableInTranslator(wordToSearch)) {
            return isWordAvailableInTranslator(wordToSearch) ? words.get(translator.translate(wordToSearch)) : words.get(wordToSearch);
        } else {
            throw new WordCounterException(WordErrorCode.DATA_NOT_FOUND_EXCEPTION, ErrorMessage.WORD_NOT_FOUND_EXCEPTION);
        }
    }

    @Override
    public synchronized String addWord(String inputValue) throws WordCounterException {
        int count = 0;
        String[] splitWords = WordUtil.splitWords(inputValue);
        if (StringUtils.isEmpty(inputValue) || validateWord(inputValue) && splitWords.length == 1) {
            throw new WordCounterException(WordErrorCode.BAD_WORD_EXCEPTION, ErrorMessage.BAD_WORD_EXCEPTION);
        } else {
            String confirmation_message = CommonConstant.WORD_ADDED_SUCCESSFULLY;
            for (String reference : splitWords) {
                String wordInOtherLanguage = retrieveWordFromTranslator(reference);
                boolean isWordAlreadyPresent = StringUtils.isNotEmpty(wordInOtherLanguage) ? isWordAvailable(translator.translate(reference)) : isWordAvailable(reference);
                String wordToAdd = StringUtils.isNotEmpty(wordInOtherLanguage) ? translator.translate(reference) : reference;
                if (getMap().isEmpty() || !isWordAlreadyPresent) {
                    words.put(wordToAdd.toLowerCase(), CommonConstant.ONE);
                } else if (words.get(reference) != null || isWordAvailableInTranslator(reference)) {
                    count = words.get(wordToAdd.toLowerCase());
                    words.put(wordToAdd.toLowerCase(), ++count);
                }
            }
            return confirmation_message;
        }
    }

    private boolean isWordAvailable(String word) {
        return getMap().containsKey(word.toLowerCase());
    }

    private boolean isWordAvailableInTranslator(String word) {
        return StringUtils.isNotEmpty(translator.translate(word));
    }

    private String retrieveWordFromTranslator(String word) {
        return translator.translate(word.toLowerCase());
    }

    private boolean validateWord(String word) {
        Pattern pattern = Pattern.compile(CommonConstant.SPECIAL_CHARACTER_REGULAR_EXPRESSION);
        Matcher matcher = pattern.matcher(word);
        return matcher.find();
    }

    private String stringTrimAndConvertToLowerCase(String word) {
     return StringUtils.isNotEmpty(word) ? word.toLowerCase().trim(): word;
    }

}
