package com.library.service;

import java.sql.Connection;

import com.library.data.BookCopiesDAO;
import com.library.data.ConnectionUtils;
import com.library.entity.BookCopies;

public class Librarian {
	private static Librarian instance=null;
	private Librarian(){}
	public static Librarian getInstance(){
		if (instance==null) instance=new Librarian();
		return instance;
	}
	public int getNoOfCopiesForBranch(BookCopies bc) throws Exception{
		Connection con=ConnectionUtils.getConnection();
		int value=0;
		try {
			BookCopiesDAO bDAO=new BookCopiesDAO(con);
			BookCopies temp=bDAO.read(new Integer[]{bc.getBook().getBookId(), bc.getLibraryBranch().getBranchId()});
			if (temp==null) {
				bc.setNoOfCopies(0);
				value=0;
				bDAO.create(bc);
				con.commit();
			}else{
				value=temp.getNoOfCopies();
			}
			return value;
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
			throw new Exception("Get Number of copies operation failed...");
		}
	}
	public void addBookCopiesToBranch(BookCopies bc) throws Exception {
		Connection con=ConnectionUtils.getConnection();
		try {
			BookCopiesDAO bcDAO=new BookCopiesDAO(con);
			bcDAO.update(bc);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
			throw new Exception("Add book copies to branch failed...");
		}
	}
	

}
