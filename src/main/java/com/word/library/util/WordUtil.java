package com.word.library.util;

import com.word.library.common.CommonConstant;
import io.micrometer.common.util.StringUtils;

public class WordUtil {

    public static String[] splitWords(String word) {
        if(StringUtils.isNotEmpty(word)) {
            return word.split(CommonConstant.REGEX_SPACE);
        }
       return null;
    }
}
