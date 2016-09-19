package com.library.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.entity.Author;

public class AuthorDAO extends BaseDAO<Author> {

	public AuthorDAO(Connection con) {
		super(con);
	}

	@Override
	public void create(Author be) throws SQLException {
		save("insert into tbl_author(authorName) values(?)", new Object[] { be.getAuthorName() });
	}

	@Override
	public void update(Author be) throws SQLException {
		save("update tbl_author set authorName=? where authorId=?", new Object[] { be.getAuthorName(), be.getAuthorId() });
	}

	@Override
	public Author read(Integer[] pk) throws SQLException {
		return baseRead("select * from tbl_author where authorId=?", pk);
	}

	@Override
	public void delete(Author be) throws SQLException {
		save("delete from tbl_author where authorId=?", new Object[] { be.getAuthorId() });
	}

	@Override
	public List<Author> readResult(ResultSet rs) throws SQLException {
		List<Author> list = new ArrayList<Author>();
		while (rs.next()) {
			Author a = new Author(rs.getString("authorname"));
			a.setAuthorId(rs.getInt("authorId"));
			list.add(a);
		}
		return list;
	}

	@Override
	public List<Author> readAll() throws SQLException {
		return baseReadAll("select * from tbl_author", new Object[] {});
	}
	
	public List<Author> readAll(int pageNo, int pageSize, String searchString) throws SQLException{
		setPageNo(pageNo);
		setPageSize(pageSize);
		 if (searchString!=null && !"".equals(searchString)) {
			 	searchString="%"+searchString+"%";
				return baseSelect("select * from tbl_author where authorName like ?", new Object[]{searchString});
		 }else{
			 return baseSelect("select * from tbl_author", new Object[]{});
		 }
	}

	public int getCount() throws SQLException{
		return saveGetCount("select count(*) from tbl_author", new Object[]{});
	}

	public int getCountByName(String searchString) throws SQLException {
		searchString="%"+searchString+"%";
		return saveGetCount("select count(*) from tbl_author where authorName like ?", new Object[]{searchString});
	}

	public int getId(String trim) throws SQLException{
		return saveGetCount("select authorId from tbl_author where authorName=?", new Object[]{trim});
	}

}
