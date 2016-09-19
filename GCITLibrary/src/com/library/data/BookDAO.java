package com.library.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.entity.Author;
import com.library.entity.Book;

public class BookDAO extends BaseDAO<Book>{

	public BookDAO(Connection con) {
		super(con);
	}
	public void createInBookAuthorsTbl(Book be) throws SQLException{
		if (be.getAuthorsList()!=null) {
		for (Author a : be.getAuthorsList()) {

			save("insert into tbl_book_authors(bookId, authorId) values(?,?)", 
					new Object[]{be.getBookId(), a.getAuthorId()});
		}	
		}
	}

	@Override
	public void create(Book be) throws SQLException {
		if (be.getPublisher()==null) {
			save("insert into tbl_book(title) values(?)", new Object[]{be.getTitle()});
		}
		save("insert into tbl_book(title, pubId) values(?,?)", 
				new Object[]{be.getTitle(), be.getPublisher().getPublisherId()});	
	}

	public int createAndGetKey(Book be) throws SQLException {
		if (be.getPublisher()==null) {
			return saveAndGetId("insert into tbl_book(title) values(?)", new Object[]{be.getTitle()});
		}
		return saveAndGetId("insert into tbl_book(title, pubId) values(?,?)", 
				new Object[]{be.getTitle(), be.getPublisher().getPublisherId()});
	}
	@Override
	public void update(Book be) throws SQLException {
		if (be.getPublisher()==null) {
			save("update tbl_book set title=?, pubId=null where bookId=?",
					new Object[] { be.getTitle(), be.getBookId() });
		}else{
			
			save("update tbl_book set title=?, pubId=? where bookId=?",
					new Object[] { be.getTitle(), be.getPublisher().getPublisherId(), be.getBookId() });
		}
	}


	@Override
	public void delete(Book be) throws SQLException {
		save("delete from tbl_book where bookId=?", new Object[]{be.getBookId()});

	}

	@Override
	public List<Book> readAll() throws SQLException {
		return baseReadAll("select * from tbl_book", new Object[]{});
	}

	@Override
	public List<Book> readResult(ResultSet rs) throws SQLException {
		List<Book> list=new ArrayList<Book>();
		PublisherDAO pub=new PublisherDAO(con);
		while (rs.next()) {
			Book b;
			if (rs.getInt("pubId")==0) {
				b=new Book(rs.getString("title"), null);
				b.setBookId(rs.getInt("bookId"));
				list.add(b);
			}else{
				b=new Book(rs.getString("title"), pub.read(new Integer[]{rs.getInt("pubId")}));
				b.setBookId(rs.getInt("bookId"));
				list.add(b);
			}
			b.setAuthorsList(getAuthorsListForBook(b.getBookId()));
		}
	
		return list;
	}
	
	private List<Author> getAuthorsListForBook(int bookId) throws SQLException {
		ResultSet rs=makeStatement("select authorId from tbl_book_authors where bookId=?", new Object[]{bookId});
		List<Author> list=new ArrayList<Author>();
		while (rs.next()) {
			AuthorDAO aDAO=new AuthorDAO(con);
			list.add(aDAO.read(new Integer[]{rs.getInt("authorId")}));
			
		}
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}
	public List<Book> readAll(int pageNo, int pageSize, String searchString) throws SQLException{
		setPageNo(pageNo);
		setPageSize(pageSize);
		 if (searchString!=null && !"".equals(searchString)) {
			 	searchString="%"+searchString+"%";
				return baseSelect("select * from tbl_book where title like ?", new Object[]{searchString});
		 }else{
			 return baseSelect("select * from tbl_book", new Object[]{});
		 }
	}
	public int getCount() throws SQLException{
		return saveGetCount("select count(*) from tbl_book", new Object[]{});
	}

	public int getCountByName(String searchString) throws SQLException {
		searchString="%"+searchString+"%";
		return saveGetCount("select count(*) from tbl_book where title like ?", new Object[]{searchString});
	}
	@Override
	public Book read(Integer[] pk) throws SQLException {
		return baseRead("select * from tbl_book where bookId=?", pk);
	}
	public void deleteBookAuthors(Book b) throws SQLException {
		save("delete from tbl_book_authors where bookId=?", new Object[]{b.getBookId()});

	}
	public void updateBookAuthors(Book b) throws SQLException{
		for (Author a : b.getAuthorsList()) {

			save("update tbl_book_authors set authorId=? where bookId=?", 
					new Object[]{a.getAuthorId(), b.getBookId()});
		}	

	}


}
