package com.example.notepad;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseStatusException extends RuntimeException {

    private final HttpStatus status;

    public ResponseStatusException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public ResponseStatusException(HttpStatus status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

}
