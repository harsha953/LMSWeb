package com.library.entity;

public class Genre implements BaseEntity{
	private int genreId;
	private String genreName;
	
	public Genre(String genreName) {
		this.genreName = genreName;
	}
	public int getGenreId() {
		return genreId;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
}
