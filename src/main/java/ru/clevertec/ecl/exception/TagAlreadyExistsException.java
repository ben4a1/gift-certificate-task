package ru.clevertec.ecl.exception;

public class TagAlreadyExistsException extends RuntimeException{
    private String field;
    private Integer code;
    private String message;

    public TagAlreadyExistsException(String name) {
        this.field = name;
        this.message = "Tag name is not unique";
        this.code = 40001;
    }

    @Override
    public String getMessage() {
        return message + " (name = " + field + ")";
    }
}
