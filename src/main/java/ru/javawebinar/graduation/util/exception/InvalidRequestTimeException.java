package ru.javawebinar.graduation.util.exception;

import org.springframework.http.HttpStatus;

public class InvalidRequestTimeException extends ApplicationException {

    public InvalidRequestTimeException(String msg) {
        super(msg, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}