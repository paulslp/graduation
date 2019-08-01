package ru.javawebinar.graduation.web;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.javawebinar.graduation.util.exception.ApplicationException;
import ru.javawebinar.graduation.util.exception.ErrorInfo;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionInfoHandler {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorInfo> applicationError(HttpServletRequest req, ApplicationException appEx) {
        return ResponseEntity.status(appEx.getHttpStatus()).body(new ErrorInfo(req.getRequestURL(), appEx.getMessageText(), appEx.getArgs()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleError(HttpServletRequest req, Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorInfo(req.getRequestURL(), e.getMessage(), null));
    }
}