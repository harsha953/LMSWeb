package com.library.entity;

import java.util.ArrayList;
import java.util.List;

public class Author implements BaseEntity{
	private int authorId;
	private String authorName;
	
	public Author(){}
	private List<Book> booksList;
	
	public Author(String authorName) {
		this.authorName = authorName;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public List<Book> getBooksList() {
		return booksList;
	}
	public void setBooksList(List<Book> booksList) {
		this.booksList = booksList;
	}
	public void add(Book book) {
		if (booksList==null)	booksList=new ArrayList<Book>();
		booksList.add(book);
	}
}
