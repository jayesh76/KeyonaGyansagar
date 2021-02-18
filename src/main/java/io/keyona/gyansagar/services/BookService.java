package io.keyona.gyansagar.services;

import io.keyona.gyansagar.domain.BookBlob;
import io.keyona.gyansagar.domain.User;
import io.keyona.gyansagar.domain.Book;
import io.keyona.gyansagar.exceptions.BookNameException;
import io.keyona.gyansagar.exceptions.BookNotFoundException;
import io.keyona.gyansagar.exceptions.ProjectNotFoundException;
import io.keyona.gyansagar.repositories.BookBlobRepository;
import io.keyona.gyansagar.repositories.BookRepository;
import io.keyona.gyansagar.repositories.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private UserRepository userRepository;
	    
	@Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BookBlobRepository bookBlobRepository;

    public Book saveOrUpdateBook(Book book, String userName){
    	
    /*	 if(book.getId() != null){
    		 Book  existingBook = findBookById(book.getId());
             if(!existingBook.getAuthor().equals(userName)){
                 throw new BookNotFoundException("Book not found in your account");
             }
         }  */
        try{
        	 User user = userRepository.findByUsername(userName);
             book.setUser(user);
             book.setOperator(user.getUsername());
        	 book.setBookName(book.getBookName().toUpperCase());
        	 if(book.getId()==null){
        		 BookBlob bookBlob = new BookBlob ();
                 book.setBookBlob(bookBlob);
                 bookBlob.setBook(book);
                 bookBlob.setShlok(book.getShlok());
        		 bookBlob.setEvent(book.getEvent());
             };
             if(book.getId()!=null){
            	 bookBlobRepository.findByBook(book).setEvent(book.getEvent());
            	 bookBlobRepository.findByBook(book).setShlok(book.getShlok());
             }
            return bookRepository.save(book);
        }catch (Exception e){
            throw new BookNameException("Book Name '"+book.getBookName().toUpperCase()+"' already exists");
        }
    }


    public Book findBookById(Long id){

        Optional<Book> book = bookRepository.findById(id);
        if(book == null){
            throw new BookNameException("Book Name '"+id+"' does not exist");
        }
        Optional<BookBlob> bookBlob = bookBlobRepository.findById(id);
        if(bookBlob != null){
        	book.get().setEvent(bookBlob.get().getEvent());
            book.get().setShlok(bookBlob.get().getShlok());
        }
        
        return book.get();
    }
    
    public BookBlob findBookBlobByBookId(Long id){

        Optional<BookBlob> bookBlob = bookBlobRepository.findById(id);
        if(bookBlob == null){
            throw new BookNameException("Book Content for '"+id+"' does not exist");
        }
        return bookBlob.get();
    }
    

    public Iterable<Book> findAllBooks(){
        return bookRepository.findAll();
    }
    
    public Iterable<Book> findAllBooksByName(String bookName){
        return bookRepository.findAllByBookName(bookName);
    }
    public Iterable<Book> findAllBooksBySubject(String subject){
        return bookRepository.findAllBySubject(subject);
    }
    public Iterable<Book> findAllBooksByActor(String actor){
        return bookRepository.findAllByActor(actor);
    }
    public Iterable<Book> findAllBooksByVillage(String village){
        return bookRepository.findAllByVillage(village);
    }
    public Iterable<Book> findAllBooksByUser(String userName){
        System.out.println("userName: "+userName);
        return bookRepository.findAllByOperator(userName);
    }
    public Iterable<Book> findAllBooksByAuthor(String author){
        return bookRepository.findAllByAuthor(author);
    }

    public void deleteBookById(Long id){
        Optional<Book> book = bookRepository.findById(id);

        if(book == null){
            throw  new  BookNameException("Cannot Book with Name '"+id+"'. This book does not exist");
        }

        bookRepository.deleteById(id);
    }

}
