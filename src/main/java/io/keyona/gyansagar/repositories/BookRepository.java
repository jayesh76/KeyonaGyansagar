package io.keyona.gyansagar.repositories;

import io.keyona.gyansagar.domain.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Override
    List<Book> findAll();
	
    Iterable<Book> findAllByBookName(String bookName);
    
    Iterable<Book> findAllByActor(String actor);
    
    Iterable<Book> findAllBySubject(String subject);
    
    Iterable<Book> findAllByVillage(String village);
    
    Iterable<Book> findAllByAuthor(String author);
    Iterable<Book> findAllByOperator(String operator);

}
