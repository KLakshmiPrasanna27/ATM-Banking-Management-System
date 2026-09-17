package com.atm_project.exception;

public class InvalidOldPinException
        extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidOldPinException(String message) {
        super(message);
    }
}