package com.library.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.library.entity.BookCopies;

public class BookCopiesDAO extends BaseDAO<BookCopies> {

	public BookCopiesDAO(Connection con) {
		super(con);
	}

	@Override
	public void create(BookCopies be) throws SQLException {
		save("insert into tbl_book_copies(bookId, branchId, noOfCopies) values(?,?,?)", new Object[] { be.getBook().getBookId(),
				be.getLibraryBranch().getBranchId(), be.getNoOfCopies() });
	}

	@Override
	public void update(BookCopies be) throws SQLException {
		save("update tbl_book_copies set noOfCopies=? where bookId=? and branchId=?", new Object[] { be.getNoOfCopies(), be.getBook().getBookId(),
				be.getLibraryBranch().getBranchId() });
	}

	@Override
	public void delete(BookCopies be) throws SQLException {
		save("delete from tbl_book_copies where bookId=? and branchId=?", new Object[] { be.getBook().getBookId(),
				be.getLibraryBranch().getBranchId() });

	}

	@Override
	public BookCopies read(Integer[] pk) throws SQLException {
		return baseRead("select * from tbl_book_copies where bookId=? and branchId=?", pk);
	}

	@Override
	public List<BookCopies> readAll() throws SQLException {
		return baseReadAll("select * from tbl_book_copies", new Object[] {});
	}

	@Override
	public List<BookCopies> readResult(ResultSet rs) throws SQLException {
		List<BookCopies> list = new ArrayList<BookCopies>();
		BookDAO bDaAO = new BookDAO(con);
		LibraryBranchDAO lbDAO = new LibraryBranchDAO(con);
		while (rs.next()) {
			BookCopies bc = new BookCopies(bDaAO.read(new Integer[] { rs.getInt("bookId") }), lbDAO.read(new Integer[] { rs.getInt("branchId") }),
					rs.getInt("noOfCopies"));
			list.add(bc);
		}
		return list;
	}

	public List<BookCopies> getListOfBooks(int branchId) throws SQLException {
		return baseReadAll("select * from tbl_book_copies where branchId=? and noOfCopies>0", new Object[] { branchId });
	}

}
