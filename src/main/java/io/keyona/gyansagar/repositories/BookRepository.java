package io.keyona.gyansagar.repositories;

import io.keyona.gyansagar.domain.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

	@Override
    List<Book> findAll();

    List<Book> findAllById(Iterable<Long> ids);

    Iterable<Book> findAllByBookName(String bookName);
    
    Iterable<Book> findAllByActor(String actor);
    
    Iterable<Book> findAllBySubject(String subject);
    
    Iterable<Book> findAllByVillage(String village);
    
    Iterable<Book> findAllByAuthor(String author);
    Iterable<Book> findAllByOperator(String operator);

}
