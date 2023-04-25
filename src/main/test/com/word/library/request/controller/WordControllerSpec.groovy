package com.word.library.request.controller

import com.word.library.common.CommonConstant
import com.word.library.request.service.WordService
import org.junit.Assert
import spock.lang.Specification

class WordControllerSpec extends Specification{

    private WordController wordController
    private WordService wordService = Mock(WordService.class)
    def setup() {
        wordController = new WordController(wordService)
    }

    def 'test retrieveWord '() {
        given:
        wordService.retrieveWord(_)>>1
        when:
        Integer wordCount = wordController.retrieveWord('food').getBody()
        then:
        Assert.assertEquals(wordCount, 1)
    }

    def 'test addWord'() {
        given:
        wordService.addWord(_) >> CommonConstant.WORD_ADDED_SUCCESSFULLY
        when:
        String response = wordController.addWord("flor").getBody()
        then:
        Assert.assertEquals(response, CommonConstant.WORD_ADDED_SUCCESSFULLY)
    }
}

