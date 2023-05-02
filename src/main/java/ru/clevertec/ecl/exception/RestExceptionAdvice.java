package ru.clevertec.ecl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.clevertec.ecl.web.response.ErrorResponse;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionAdvice {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(CustomException exception) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .timestamp(LocalDateTime.now())
                .build(), HttpStatus.NOT_FOUND);
    }
}
