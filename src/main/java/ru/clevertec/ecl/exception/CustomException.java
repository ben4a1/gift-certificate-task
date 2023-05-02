package ru.clevertec.ecl.exception;

import lombok.Getter;

@Getter
public class CustomException extends Exception {

    private final String message;
    private final String code;

    public CustomException(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
