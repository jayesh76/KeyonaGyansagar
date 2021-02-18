package io.keyona.gyansagar.web;


import io.keyona.gyansagar.domain.Book;
import io.keyona.gyansagar.domain.BookBlob;
import io.keyona.gyansagar.services.MapValidationErrorService;
import io.keyona.gyansagar.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;
    
    @Autowired
    private BookService bookBlobService;


    @Autowired
    private MapValidationErrorService mapValidationErrorService;


    @PostMapping("")
    public ResponseEntity<?> createNewBook(@Valid @RequestBody Book book, BindingResult result, Principal principal){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap!=null) return errorMap;

        Book book1 = bookService.saveOrUpdateBook(book, principal.getName());
        return new ResponseEntity<Book>(book1, HttpStatus.CREATED);
    }


    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable Long bookId){

        Book book = bookService.findBookById(bookId);

        return new ResponseEntity<Book>(book,HttpStatus.OK);
    }
    
    @GetMapping("/content/{bookId}")
    public ResponseEntity<?> getBookContent(@PathVariable Long bookId){

    	BookBlob bookBlob = bookBlobService.findBookBlobByBookId(bookId);

        return new ResponseEntity<BookBlob>(bookBlob,HttpStatus.OK);
    }
    


    @GetMapping("/all")
    public Iterable<Book> getAllBooks(){return bookService.findAllBooks();}
    
    @GetMapping("/allBooks")
    public Iterable<Book> getAllBooksByUser(Principal principal){return bookService.findAllBooksByUser(principal.getName());}
    
    @GetMapping("/name/{bookName}")
    public Iterable<Book> getAllBooksByName(@PathVariable String bookName){
    	return bookService.findAllBooksByName(bookName);
    }
    
    @GetMapping("/subject/{subject}")
    public Iterable<Book> getAllBooksBySubject(@PathVariable String subject){
    	return bookService.findAllBooksBySubject(subject);
    }
    
    @GetMapping("/actor/{actor}")
    public Iterable<Book> getAllBooksByActor(@PathVariable String actor){
    	return bookService.findAllBooksByActor(actor);
    }

    @GetMapping("/author/{author}")
    public Iterable<Book> getAllBooksByAuthor(@PathVariable String author){
        return bookService.findAllBooksByAuthor(author);
    }
    
    @GetMapping("/village/{village}")
    public Iterable<Book> getAllBooksByVillage(@PathVariable String village){
    	return bookService.findAllBooksByVillage(village);
    }
    
    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId){
        bookService.deleteBookById(bookId);

        return new ResponseEntity<String>("Book with Name: '"+bookId+"' was deleted", HttpStatus.OK);
    }
}
