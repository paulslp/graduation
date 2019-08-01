package ru.javawebinar.graduation.util.exception;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ApplicationException {

    //  http://stackoverflow.com/a/22358422/548473
    public NotFoundException(String arg) {
        super("Not found entity", HttpStatus.UNPROCESSABLE_ENTITY, arg);
    }
}