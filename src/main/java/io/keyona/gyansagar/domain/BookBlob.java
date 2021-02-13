package io.keyona.gyansagar.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class BookBlob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="book_id",nullable = false)
    @MapsId
    @JsonIgnore
    private Book book;
   
    @Lob
    private String shlok;
    @Lob
    private String event;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}
	/**
	 * @param book the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}
	/**
	 * @return the shlok
	 */
	public String getShlok() {
		return shlok;
	}
	/**
	 * @param shlok the shlok to set
	 */
	public void setShlok(String shlok) {
		this.shlok = shlok;
	}
	/**
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}
	/**
	 * @param event the event to set
	 */
	public void setEvent(String event) {
		this.event = event;
	}
    
    
}
