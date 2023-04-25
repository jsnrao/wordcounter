package com.word.library.exception;


import com.word.library.common.ErrorMessage;
import com.word.library.common.WordErrorCode;
import lombok.*;

import java.util.Optional;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WordCounterException extends RuntimeException {

    private WordErrorCode errorCode;
    private ErrorMessage errorMessage;
    private Object data;

    public WordCounterException(WordErrorCode errorCode, ErrorMessage errorMessage) {
        super(Optional.ofNullable(errorMessage).map(ErrorMessage::getMessageToFrontEnd).orElse(null));
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
