package com.library.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.entity.BookLoans;

public class BookLoansDAO extends BaseDAO<BookLoans> {

	public BookLoansDAO(Connection con) {
		super(con);
	}

	@Override
	public void create(BookLoans be) throws SQLException {
		save("insert into tbl_book_loans(bookId, branchId, cardNo, dateOut, dueDate) values(?,?,?, curdate(), date_add(curdate(), interval 7 day))", 
				new Object[]{be.getBook().getBookId(), be.getLibraryBranch().getBranchId(), be.getBorrower().getCardNo()});
	}

	@Override
	public void update(BookLoans be) throws SQLException {
		save("update tbl_book_loans set dateIn=curdate() where bookId=? and branchId=? and cardNo=?", 
				new Object[]{be.getBook().getBookId(), be.getLibraryBranch().getBranchId(), be.getBorrower().getCardNo()});
		
	}
	public void updateByDueDate(BookLoans be) throws SQLException {
		//System.out.println("DAODate "+be.getDueDate()+be.getBook().getBookId()+be.getLibraryBranch().getBranchId()+be.getBorrower().getCardNo());
		save("update tbl_book_loans set dueDate=? where bookId=? and branchId=? and cardNo=?", 
				new Object[]{be.getDueDate(), be.getBook().getBookId(), be.getLibraryBranch().getBranchId(), be.getBorrower().getCardNo()});
		
	}
	@Override
	public void delete(BookLoans be) throws SQLException {
		save("delete from tbl_book_loans where bookId=? and branchId=? and cardNo=?", 
				new Object[]{be.getBook().getBookId(), be.getLibraryBranch().getBranchId(), be.getBorrower().getCardNo()});	
	}

	@Override
	public BookLoans read(Integer[] pk) throws SQLException {
		return baseRead("select * from tbl_book_loans where bookId=? and branchId=? and cardNo=?", pk);
	}

	@Override
	public List<BookLoans> readAll() throws SQLException {
		return baseReadAll("select * from tbl_book_loans", new Object[]{});
	}

	@Override
	public List<BookLoans> readResult(ResultSet rs) throws SQLException {
		List<BookLoans> list=new ArrayList<BookLoans>();
		BookDAO bDAO=new BookDAO(con);
		LibraryBranchDAO lbDAO=new LibraryBranchDAO(con);
		BorrowerDAO brDAO=new BorrowerDAO(con);
		while (rs.next()) {
			BookLoans bl=new BookLoans(bDAO.read(new Integer[]{rs.getInt("bookId")}),
					lbDAO.read(new Integer[]{rs.getInt("branchId")}),
							brDAO.read(new Integer[]{rs.getInt("cardNo")}));
			bl.setDateOut(rs.getDate("dateOut"));
			bl.setDueDate(rs.getDate("dueDate"));
			list.add(bl);
		}
		return list;
	}

	public List<BookLoans> getListOfBookLoans(int cardNo) throws SQLException{
		return baseReadAll("select * from tbl_book_loans where cardNo=? and dateIn is null", new Object[]{cardNo});
		
	}

	public List<BookLoans> getListOfBookLoans(int branchId, int cardNo) throws SQLException {
		return baseReadAll("select * from tbl_book_loans where branchId=? and cardNo=? and dateIn is null", new Object[]{branchId, cardNo});
		
	}

	public List<BookLoans> getRowsByCardNo(int cardNo) throws SQLException{
		return baseReadAll("select * from tbl_book_loans where cardNo=? and dateIn is null", new Object[] {cardNo});
	}

}
