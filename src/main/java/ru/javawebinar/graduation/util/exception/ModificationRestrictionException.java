package ru.javawebinar.graduation.util.exception;

import org.springframework.http.HttpStatus;

public class ModificationRestrictionException extends ApplicationException {

    public ModificationRestrictionException() {
        super("Admin/User modification is forbidden", HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }
}