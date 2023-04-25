package com.word.library.request.service

import com.word.library.common.CommonConstant
import com.word.library.repository.WordRepository
import com.word.library.request.controller.WordController
import org.junit.Assert
import spock.lang.Specification

class WordServiceSpec extends Specification{

    private WordService wordService
    private WordRepository wordRepository = Mock(WordRepository.class)


    def setup() {
        wordService = new WordService(wordRepository)
    }

    def 'test retrieveWord '() {
        given:
        wordRepository.retrieveWord(_)>>1
        when:
        Integer wordCount = wordService.retrieveWord('food')
        then:
        Assert.assertEquals(wordCount, 1)
    }

    def 'test addWord'() {
        given:
        wordRepository.addWord(_) >> CommonConstant.WORD_ADDED_SUCCESSFULLY
        when:
        String response = wordService.addWord("flor")
        then:
        Assert.assertEquals(response, CommonConstant.WORD_ADDED_SUCCESSFULLY)
    }
}
