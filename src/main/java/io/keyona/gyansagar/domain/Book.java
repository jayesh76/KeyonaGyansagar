package io.keyona.gyansagar.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Book name is required")
	// @Column(updatable = false, unique = true)
	private String bookName;
	@NotBlank(message = "Book edition is required")
	private String bookEdition;
	private String pageNumber;
	private String subject;
	private String subSubject;
	@Transient
    private String shlok;
	@Transient
    private String event;
	private String reference;
	private String actor;
	private String village;
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date eventDate;
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date birthDate;
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date deathDate;
	private String eventDateGuj;
	private String birthDateGuj;
	private String deathDateGuj;
	@JsonFormat(pattern = "yyyy-mm-dd")
	@Column(updatable = false)
	private Date created_At;
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date updated_At;
	private String author;
	private String comments;

	@OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "book")
	@JsonIgnore
	private BookBlob bookBlob;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JsonIgnore
	 private User user;

	
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

	

	/**
	 * @return the bookBlob
	 */
	public BookBlob getBookBlob() {
		return bookBlob;
	}

	/**
	 * @param bookBlob the bookBlob to set
	 */
	public void setBookBlob(BookBlob bookBlob) {
		this.bookBlob = bookBlob;
	}

	public Book() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookEdition() {
		return bookEdition;
	}

	public void setBookEdition(String bookEdition) {
		this.bookEdition = bookEdition;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	public String getEventDateGuj() {
		return eventDateGuj;
	}

	public void setEventDateGuj(String eventDateGuj) {
		this.eventDateGuj = eventDateGuj;
	}

	public String getBirthDateGuj() {
		return birthDateGuj;
	}

	public void setBirthDateGuj(String birthDateGuj) {
		this.birthDateGuj = birthDateGuj;
	}

	public String getDeathDateGuj() {
		return deathDateGuj;
	}

	public void setDeathDateGuj(String deathDateGuj) {
		this.deathDateGuj = deathDateGuj;
	}

	public Date getCreated_At() {
		return created_At;
	}

	public void setCreated_At(Date created_At) {
		this.created_At = created_At;
	}

	public Date getUpdated_At() {
		return updated_At;
	}

	public void setUpdated_At(Date updated_At) {
		this.updated_At = updated_At;
	}
	
	
	public String getAuthor() {
		return author;
	}

	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the subSubject
	 */
	public String getSubSubject() {
		return subSubject;
	}

	/**
	 * @param subSubject the subSubject to set
	 */
	public void setSubSubject(String subSubject) {
		this.subSubject = subSubject;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	@PrePersist
	protected void onCreate() {
		this.created_At = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updated_At = new Date();
	}

}
