package ru.clevertec.ecl.exception;

public class CustomException extends Exception {

    private final String message;
    private final String code;

    public CustomException(String message, String code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
