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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/book")
@CrossOrigin
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
    @GetMapping("/print/{bookIds}")
    public void test(@PathVariable String bookIds) {
        StringTokenizer st = new StringTokenizer(bookIds, ",");
        List<Long> list = new ArrayList<Long>();
        while (st.hasMoreTokens()) {
            list.add(Long.parseLong(st.nextToken()));
        }
        Iterator<Long> iterator = list.iterator();

        while(iterator.hasNext()) {
            Long element = iterator.next();
            System.out.println( element );
        }

        System.out.println(bookIds);
    }

    @GetMapping("/printBooks/{bookIds}")
    public List<Book> getBooksForPrint(@PathVariable String bookIds){
        StringTokenizer st = new StringTokenizer(bookIds, ",");
        List<Long> list = new ArrayList<Long>();
        while (st.hasMoreTokens()) {
            list.add(Long.parseLong(st.nextToken()));
        }
        Iterable<Long> iterableIds  = list;


        return bookService.findBooksForPrint(iterableIds);
    }

    @GetMapping("/all")
    public Iterable<Book> getAllBooks(@RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "3") Integer pageSize,
                                      @RequestParam(defaultValue = "id") String sortBy){

        return bookService.findAllBooks(pageNo, pageSize, sortBy);
    }
    
    @GetMapping("/allMyBooks")
    public Iterable<Book> getAllBooksByUser(Principal principal){
        return bookService.findAllBooksByUser(principal.getName());
    }
    
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
