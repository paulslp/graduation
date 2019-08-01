package ru.javawebinar.graduation.util.exception;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class ApplicationException extends RuntimeException {


    private final String messageText;
    private final HttpStatus httpStatus;
    private final String[] args;

    public ApplicationException(String message, HttpStatus httpStatus) {
        this(message, httpStatus, "");
    }

    public ApplicationException(String messageText, HttpStatus httpStatus, String... args) {
        super(String.format("message=%s, args=%s", messageText, Arrays.toString(args)));
        this.messageText = messageText;
        this.httpStatus = httpStatus;
        this.args = args;
    }

    public String getMessageText() {
        return messageText;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String[] getArgs() {
        return args;
    }
}
