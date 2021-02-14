package io.keyona.gyansagar.exceptions;

public class BookNotFoundExceptionResponse {

    private String BookNotFound;

    public BookNotFoundExceptionResponse(String bookNotFound) {
        BookNotFound = bookNotFound;
    }

    public String getBookNotFound() {
        return BookNotFound;
    }

    public void setBookNotFound(String bookNotFound) {
        BookNotFound = bookNotFound;
    }
}
