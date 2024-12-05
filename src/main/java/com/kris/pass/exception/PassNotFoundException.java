package com.kris.pass.exception;


public class PassNotFoundException extends RuntimeException {

    public static final String MESSAGE = "Pass not found with id: ";

    public PassNotFoundException(Long id) {
        super(MESSAGE + id);
    }
}
