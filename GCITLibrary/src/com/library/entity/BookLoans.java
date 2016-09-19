package com.library.entity;

import java.sql.Date;


public class BookLoans implements BaseEntity{
	private Book book;
	private LibraryBranch libraryBranch;
	private Borrower borrower;
	private Date dateOut;
	private Date dueDate;
	private Date dateIn;
	
	public BookLoans(){}
	public BookLoans(Book book, LibraryBranch libraryBranch, Borrower borrower) {
		this.book = book;
		this.libraryBranch = libraryBranch;
		this.borrower = borrower;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public LibraryBranch getLibraryBranch() {
		return libraryBranch;
	}
	public void setLibraryBranch(LibraryBranch libraryBranch) {
		this.libraryBranch = libraryBranch;
	}
	public Borrower getBorrower() {
		return borrower;
	}
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
	public Date getDateOut() {
		return dateOut;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getDateIn() {
		return dateIn;
	}
	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}
	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}
	
}
