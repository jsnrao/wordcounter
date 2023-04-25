package com.word.library.common;

public enum ErrorMessage {

    WORD_NOT_FOUND_EXCEPTION(1000, WordErrorCode.DATA_NOT_FOUND_EXCEPTION, "Word not found"),
    BAD_WORD_EXCEPTION(1001, WordErrorCode.BAD_WORD_EXCEPTION, "Entered word is not valid.");

    private final int id;
    private final WordErrorCode errorCode;
    private final String messageToFrontEnd;

    ErrorMessage(int id, WordErrorCode errorCode, String messageToFrontEnd) {
        this.id = id;
        this.errorCode = errorCode;
        this.messageToFrontEnd = messageToFrontEnd;
    }

    public int getId() {
        return id;
    }

    public WordErrorCode getErrorCode() {
        return errorCode;
    }

    public String getMessageToFrontEnd() {
        return messageToFrontEnd;
    }
}
