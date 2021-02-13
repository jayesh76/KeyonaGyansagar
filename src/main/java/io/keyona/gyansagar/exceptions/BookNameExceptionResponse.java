package io.keyona.gyansagar.exceptions;

public class BookNameExceptionResponse {

    private String bookName;

    public BookNameExceptionResponse(String bookName) {
        this.bookName = bookName;
    }

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

    
}
