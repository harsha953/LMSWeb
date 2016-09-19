package com.library.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.library.data.BookCopiesDAO;
import com.library.data.BookLoansDAO;
import com.library.data.BorrowerDAO;
import com.library.data.ConnectionUtils;
import com.library.entity.Book;
import com.library.entity.BookCopies;
import com.library.entity.BookLoans;
import com.library.entity.Borrower;
import com.library.entity.LibraryBranch;
import com.library.exceptions.LibraryException;

public class BorrowerService {
	private static BorrowerService instance=null;
	private BorrowerService(){}
	public static BorrowerService getInstance(){
		if (instance==null) instance=new BorrowerService();
		return instance;
	}
	public boolean checkCardNo(String pk) throws Exception {
		if (pk==null || pk.trim().length()==0) {
			throw new LibraryException("Card No. cannot be empty");
		}
		Connection con=ConnectionUtils.getConnection();
		try{
			BorrowerDAO bDAO=new BorrowerDAO(con);
			int cardNo=Integer.parseInt(pk);
			Borrower temp=bDAO.read(new Integer[]{cardNo});
			if (temp==null) {
				return false;
			}else{
				return true;
			}
		}catch(Exception e){
			throw new Exception("Checking cardNumber failed. Try again.");
		}	
	}
	public List<Book> getAllBooksForBranch(int branchId) throws Exception{
		Connection con=ConnectionUtils.getConnection();
		List<Book> list=new ArrayList<Book>();
		try{
			BookCopiesDAO bcDAO=new BookCopiesDAO(con);
			List<BookCopies> listBC=bcDAO.getListOfBooks(branchId);
			for (BookCopies bookCopies : listBC) {
				list.add(bookCopies.getBook());
			}
			return list;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
	}
	public void addBookLoans(BookLoans bl) throws Exception {
		Connection con=ConnectionUtils.getConnection();
		try{
			BookLoansDAO blDAO=new BookLoansDAO(con);
			BookLoans temp=blDAO.read(new Integer[]{bl.getBook().getBookId(), bl.getLibraryBranch().getBranchId(),
					bl.getBorrower().getCardNo()});
			if (temp==null) {
				blDAO.create(bl);
			}else if(temp.getDateIn()==null){
				throw new LibraryException("\" "+bl.getBook().getTitle()+" \"already taken from \""+bl.getLibraryBranch().getBranchName()+"\"");
			}else{
				blDAO.delete(bl);
				blDAO.create(bl);
			}
			
			BookCopiesDAO bcDAO=new BookCopiesDAO(con);
			BookCopies bc=new BookCopies();
			bc.setBook(bl.getBook());
			bc.setLibraryBranch(bl.getLibraryBranch());
			bc=bcDAO.read(new Integer[]{bc.getBook().getBookId(), bc.getLibraryBranch().getBranchId()});
			bc.setNoOfCopies(bc.getNoOfCopies()-1);
			bcDAO.update(bc);
			con.commit();
		}catch(Exception e){
			con.rollback();
			throw new Exception("Add bookloans failed..."+e.getMessage());
		}

	}
	public List<LibraryBranch> getAllBranchForBorrower(int cardNo) throws Exception {
		Connection con=ConnectionUtils.getConnection();
		List<LibraryBranch> list=new ArrayList<LibraryBranch>();
		try{
			BookLoansDAO blDAO=new BookLoansDAO(con);
			List<BookLoans> listBL=blDAO.getListOfBookLoans(cardNo);
			if (listBL==null) {
				return null;
			}else{
				List<Integer> branchIdList=new ArrayList<Integer>();
				for (BookLoans bl : listBL) {
					
					
					if (!branchIdList.contains(bl.getLibraryBranch().getBranchId())) {
						list.add(bl.getLibraryBranch());
					}
					branchIdList.add(bl.getLibraryBranch().getBranchId());
					
				}
				return list;
			}
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
	}
	public List<Book> getAllBookForBorrower(int branchId, int cardNo ) throws Exception {
		Connection con=ConnectionUtils.getConnection();
		List<Book> list=new ArrayList<Book>();
		try{
			BookLoansDAO blDAO=new BookLoansDAO(con);
			List<BookLoans> listBL=blDAO.getListOfBookLoans(branchId, cardNo);

			for (BookLoans bl : listBL) {
				list.add(bl.getBook());
			}
			return list;
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
	}
	public void updateBookLoans(BookLoans bl) throws Exception{
		Connection con=ConnectionUtils.getConnection();
		try{
			BookLoansDAO blDAO=new BookLoansDAO(con);
			blDAO.update(bl);
			BookCopiesDAO bcDAO=new BookCopiesDAO(con);
			BookCopies bc=new BookCopies();
			bc.setBook(bl.getBook());
			bc.setLibraryBranch(bl.getLibraryBranch());
			bc=bcDAO.read(new Integer[]{bc.getBook().getBookId(), bc.getLibraryBranch().getBranchId()});
			bc.setNoOfCopies(bc.getNoOfCopies()+1);
			bcDAO.update(bc);
			con.commit();
		}catch(Exception e){
			con.rollback();
			throw new Exception("Add bookloans failed..."+e.getMessage());
		}
		
	}

}
