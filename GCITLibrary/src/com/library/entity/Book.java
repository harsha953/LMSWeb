package com.library.entity;

import java.util.ArrayList;
import java.util.List;

public class Book implements BaseEntity{
	private int bookId;
	private String title;
	
	private Publisher publisher;
	private List<Author> authorsList;
	private List<Genre> genreList;
	
	public Book(){}
	public Book(String title, Publisher publisher) {
		this.title = title;
		this.publisher = publisher;
	}
	
	
	public int getBookId() {
		return bookId;
	}
	
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	public List<Author> getAuthorsList() {
		return authorsList;
	}
	public void setAuthorsList(List<Author> authorsList) {
		this.authorsList = authorsList;
	}
	public void add(Author author) {
		if(authorsList==null)	authorsList=new ArrayList<Author>();
		authorsList.add(author);
	}
	public List<Genre> getGenreList() {
		return genreList;
	}
	public void setGenreList(List<Genre> genreList) {
		this.genreList = genreList;
	}
	public void add(Genre genre) {
		if(genreList==null) genreList=new ArrayList<Genre>();
		genreList.add(genre);
	}
	
}
