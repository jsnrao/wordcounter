package com.word.library.request.repository

import com.word.library.common.CommonConstant
import com.word.library.exception.WordCounterException
import com.word.library.repository.WordRepository
import com.word.library.repository.WordRepositoryStub
import com.word.library.request.client.external.TranslatorClient
import org.junit.Assert
import spock.lang.Specification

class WordRepositorySpec extends Specification {

    private WordRepository wordRepository

    private TranslatorClient translatorClient = new TranslatorClient()

    def setup() {
        wordRepository = new WordRepositoryStub(translatorClient)
    }

    def be

    def 'test retrieveWord '() {
        given:
        wordRepository.addWord(existingValues)
        when:
        Integer wordCount = wordRepository.retrieveWord(inputWord)
        then:
        Assert.assertEquals(wordCount, resultCount)
        where:
        inputWord || existingValues           || resultCount
        'flor'    || "flower flor blume goOd" || 3
        'good'    || "flower flor blume goOd" || 1
        'Honest'  || "Honest honsest12"       || 1
    }

   def 'test addWord'() {
     when:
        String response = wordRepository.addWord("flor")
        then:
        Assert.assertEquals(response, CommonConstant.WORD_ADDED_SUCCESSFULLY)
    }

    def 'test add multiple words'() {

        when:
        String response = wordRepository.addWord("flor flower blume")
        then:
        Assert.assertEquals(response, CommonConstant.WORD_ADDED_SUCCESSFULLY)
    }

    def 'test addWord with number input'() {

        when:
        wordRepository.addWord("flor123")
        then:
        thrown(WordCounterException)
    }

    def 'test addWord invalid characters input'() {
        when:
        wordRepository.addWord("flor123")
        then:
        thrown(WordCounterException)
    }
}
