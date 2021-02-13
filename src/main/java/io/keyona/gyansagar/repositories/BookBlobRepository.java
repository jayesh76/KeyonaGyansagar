package io.keyona.gyansagar.repositories;

import io.keyona.gyansagar.domain.Book;
import io.keyona.gyansagar.domain.BookBlob;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookBlobRepository extends JpaRepository<BookBlob, Long> {

	BookBlob findByBook(Book book);
	
}
