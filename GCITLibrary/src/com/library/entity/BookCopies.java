package com.library.entity;

public class BookCopies implements BaseEntity{
	private Book book;
	private LibraryBranch libraryBranch;
	
	private int noOfCopies;
	
	public BookCopies(){}
	public BookCopies(Book book, LibraryBranch libraryBranch, int noOfCopies) {
		this.book = book;
		this.libraryBranch = libraryBranch;
		this.noOfCopies = noOfCopies;
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
	public int getNoOfCopies() {
		return noOfCopies;
	}
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
}
