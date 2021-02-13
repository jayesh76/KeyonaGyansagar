package io.keyona.gyansagar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookNameException extends RuntimeException {

    public BookNameException(String message) {
        super(message);
    }
}
