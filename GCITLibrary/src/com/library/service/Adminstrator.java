package com.library.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.util.HtmlUtils;

import com.library.data.AuthorDAO;
import com.library.data.BookDAO;
import com.library.data.BookLoansDAO;
import com.library.data.BorrowerDAO;
import com.library.data.ConnectionUtils;
import com.library.data.LibraryBranchDAO;
import com.library.data.PublisherDAO;
import com.library.entity.Author;
import com.library.entity.Book;
import com.library.entity.BookLoans;
import com.library.entity.Borrower;
import com.library.entity.LibraryBranch;
import com.library.entity.Publisher;
import com.library.exceptions.LibraryException;

public class Adminstrator {
	private static Adminstrator instance=null;
	private Adminstrator(){}
	public static Adminstrator getInstance(){
		if (instance==null) instance=new Adminstrator();
		return instance;
	}
	public void addAuthor(Author a) throws Exception{
		checkExceptionsInAuthor(a);
		Connection con=ConnectionUtils.getConnection();
		try {
			AuthorDAO aDAO =new AuthorDAO(con);
			aDAO.create(a);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new Exception("Add author failed");
		}finally{
			con.close();
		}
	}
	public void addBook(Book b) throws Exception{
		checkExceptionsInBook(b);
		Connection con=ConnectionUtils.getConnection();
		try {
			BookDAO bDAO =new BookDAO(con);
			int key=bDAO.createAndGetKey(b);
			b.setBookId(key);
			if (b.getAuthorsList()!=null) {
				bDAO.createInBookAuthorsTbl(b);
			}
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new Exception("Add Book failed");
		}finally{
			con.close();
		}
	}
	
	public void addPublisher(Publisher p) throws Exception{
		checkExceptionsInPublisher(p);
		Connection con=ConnectionUtils.getConnection();
		try {
			PublisherDAO pDAO =new PublisherDAO(con);
			pDAO.create(p);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new Exception("Add publisher failed");
		}finally{
			con.close();
		}
	}
	public void addBranch(LibraryBranch lb) throws Exception{
		checkExceptionsInBranch(lb);
		Connection con=ConnectionUtils.getConnection();
		try {
			LibraryBranchDAO lbDAO =new LibraryBranchDAO(con);
			lbDAO.create(lb);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new Exception("Add library branch failed");
		}finally{
			con.close();
		}
	}
	public void addBorrower(Borrower b) throws Exception{
		checkExceptionsInBorrower(b);
		Connection con=ConnectionUtils.getConnection();
		try {
			BorrowerDAO bDAO =new BorrowerDAO(con);
			bDAO.create(b);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new Exception("Add Borrower failed");
		}finally{
			con.close();
		}
	}
	private void checkExceptionsInBorrower(Borrower b) throws LibraryException {
		if (b==null ||b.getName()==null ||	b.getName().trim().length()==0 ) {
			throw new LibraryException("Borrower name cannot be null or blank");
		}else if (b.getName().trim().length()>45) {
			throw new LibraryException("Branch name and branch address cannot be more than 45 charcters");
		}
	}
	public void updateAuthor(Author a) throws Exception{
		checkExceptionsInAuthor(a);
		Connection con=ConnectionUtils.getConnection();
		try {
			AuthorDAO aDAO =new AuthorDAO(con);
			aDAO.update(a);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new Exception("update author failed");
		}finally{
			con.close();
		}
	}
	private void checkExceptionsInAuthor(Author a) throws LibraryException {
		if (a==null ||a.getAuthorName()==null || a.getAuthorName().trim().length()==0 ) {
			throw new LibraryException("Author Name cannot be null or blank");
		}else if (a.getAuthorName().trim().length()>45) {
			throw new LibraryException("Author name cannot be more than 45 charcters");
		}
	}
	public void updateBook(Book b) throws Exception{
		checkExceptionsInBook(b);
		Connection con=ConnectionUtils.getConnection();
		try {
			BookDAO bDAO =new BookDAO(con);
			bDAO.update(b);
			
			bDAO.deleteBookAuthors(b);
			bDAO.createInBookAuthorsTbl(b);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new Exception("Add Book failed");
		}finally{
			con.close();
		}
	}
	private void checkExceptionsInBook(Book b) throws LibraryException {
		if (b==null ||b.getTitle()==null || b.getTitle().trim().length()==0 ) {
			throw new LibraryException("Book title cannot be null or blank");
		}else if (b.getTitle().trim().length()>45) {
			throw new LibraryException("Book title cannot be more than 45 charcters");
		}
	}
	public void updatePublisher(Publisher p) throws Exception{
		checkExceptionsInPublisher(p);
		Connection con=ConnectionUtils.getConnection();
		try {
			PublisherDAO pDAO =new PublisherDAO(con);
			pDAO.update(p);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new Exception("Update publisher failed");
		}finally{
			con.close();
		}
	}
	private void checkExceptionsInPublisher(Publisher p)
			throws LibraryException {
		if (p==null ||p.getPublisherName()==null ||	p.getPublisherName().trim().length()==0 ) {
			throw new LibraryException("publisher name cannot be null or blank");
		}else if (p.getPublisherName().trim().length()>45) {
			throw new LibraryException("publisher name cannot be more than 45 charcters");
		}
	}
	public void updateBranch(LibraryBranch lb) throws Exception{
		checkExceptionsInBranch(lb);
		Connection con=ConnectionUtils.getConnection();
		try {
			LibraryBranchDAO lbDAO =new LibraryBranchDAO(con);
			lbDAO.update(lb);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new Exception("Add library branch failed");
		}finally{
			con.close();
		}
	}
	private void checkExceptionsInBranch(LibraryBranch lb)
			throws LibraryException {
		if (lb==null ||lb.getBranchName()==null ||	lb.getBranchName().trim().length()==0 ) {
			throw new LibraryException("branch name cannot be null or blank");
		}else if(lb.getBranchAddress()==null ||	lb.getBranchAddress().trim().length()==0){
			throw new LibraryException("branch address cannot be null or blank");
		}else if (lb.getBranchName().trim().length()>45 || lb.getBranchAddress().trim().length()>45) {
			throw new LibraryException("Branch name and branch address cannot be more than 45 charcters");
		}
	}
	public void updateBorrower(Borrower b) throws Exception{
		checkExceptionsInBorrower(b);
		Connection con=ConnectionUtils.getConnection();
		try {
			BorrowerDAO bDAO =new BorrowerDAO(con);
			bDAO.update(b);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new Exception("Add Borrower failed");
		}finally{
			con.close();
		}
	}
	public void deleteAuthor(Author a) throws Exception{
		Connection con=ConnectionUtils.getConnection();
		try {
			AuthorDAO aDAO =new AuthorDAO(con);
			aDAO.delete(a);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new Exception("delete author failed");
		}finally{
			con.close();
		}
	}
	public void deleteBook(Book b) throws Exception{
		Connection con=ConnectionUtils.getConnection();
		try {
			BookDAO bDAO =new BookDAO(con);
			bDAO.delete(b);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new Exception("delete Book failed");
		}finally{
			con.close();
		}
	}
	public void deletePublisher(Publisher p) throws Exception{
		Connection con=ConnectionUtils.getConnection();
		try {
			PublisherDAO pDAO =new PublisherDAO(con);
			pDAO.delete(p);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new Exception("Delete publisher failed");
		}finally{
			con.close();
		}
	}
	public void deleteBranch(LibraryBranch lb) throws Exception{
		Connection con=ConnectionUtils.getConnection();
		try {
			LibraryBranchDAO lbDAO =new LibraryBranchDAO(con);
			lbDAO.delete(lb);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new Exception("Delete library branch failed");
		}finally{
			con.close();
		}
	}
	public void deleteBorrower(Borrower b) throws Exception{
		Connection con=ConnectionUtils.getConnection();
		try {
			BorrowerDAO bDAO =new BorrowerDAO(con);
			bDAO.delete(b);
			con.commit();
		} catch (Exception e) {
			con.rollback();
			throw new Exception("Add Borrower failed");
		}finally{
			con.close();
		}
	}
	public List<Author> getAllAuthors() throws Exception {
		Connection con=ConnectionUtils.getConnection();
		AuthorDAO aDAO=new AuthorDAO(con);
		return aDAO.readAll();
	}
	public List<Author> getAllAuthors(int pageNo, String searchString) throws Exception {
		Connection con=ConnectionUtils.getConnection();
		AuthorDAO aDAO=new AuthorDAO(con);
		return aDAO.readAll(pageNo, 10, searchString);
	}
	public List<Book> getAllBook(int pageNo, String searchString) throws Exception {
		Connection con=ConnectionUtils.getConnection();
		BookDAO bDAO=new BookDAO(con);
		return bDAO.readAll(pageNo, 10, searchString);
	}
	public int getAuthorsCount() throws Exception{
		Connection con=ConnectionUtils.getConnection();
		AuthorDAO aDAO=new AuthorDAO(con);
		return aDAO.getCount();	
	}
	public int getAuthorsCountByName(String searchString) throws Exception {
		Connection con=ConnectionUtils.getConnection();
		AuthorDAO aDAO=new AuthorDAO(con);
		return aDAO.getCountByName(searchString);	
	}
	public int getBooksCount() throws Exception{
		Connection con=ConnectionUtils.getConnection();
		BookDAO bDAO=new BookDAO(con);
		return bDAO.getCount();	
	}
	public int getBooksCountByName(String searchString) throws Exception {
		Connection con=ConnectionUtils.getConnection();
		BookDAO bDAO=new BookDAO(con);
		return bDAO.getCountByName(searchString);	
	}
	public Author getAuthor(int pk) throws Exception{
		Connection con=ConnectionUtils.getConnection();
		AuthorDAO aDAO=new AuthorDAO(con);
		return aDAO.read(new Integer[]{pk});
	}
	public LibraryBranch getBranch(int pk) throws Exception{
		Connection con=ConnectionUtils.getConnection();
		LibraryBranchDAO bDAO=new LibraryBranchDAO(con);
		return bDAO.read(new Integer[]{pk});
	}
	public Book getBook(int pk) throws Exception{
		Connection con=ConnectionUtils.getConnection();
		BookDAO bDAO=new BookDAO(con);
		return bDAO.read(new Integer[]{pk});
	}
	public Borrower getBorrower(int pk) throws Exception{
		Connection con=ConnectionUtils.getConnection();
		BorrowerDAO bDAO=new BorrowerDAO(con);
		return bDAO.read(new Integer[]{pk});
	}
	public List<Publisher> getAllPublisher() throws Exception{
		Connection con=ConnectionUtils.getConnection();
		PublisherDAO pDAO=new PublisherDAO(con);
		return pDAO.readAll();
	}
	public Publisher getPublisher(int pk) throws Exception{
		Connection con=ConnectionUtils.getConnection();
		PublisherDAO pDAO=new PublisherDAO(con);
		return pDAO.read(new Integer[]{pk});
	}
	public List<Book> getAllBooks() throws Exception {
		Connection con=ConnectionUtils.getConnection();
		BookDAO bDAO=new BookDAO(con);
		return bDAO.readAll();
	}
	public List<LibraryBranch> getAllBranches() throws Exception{
		Connection con=ConnectionUtils.getConnection();
		LibraryBranchDAO lbDAO=new LibraryBranchDAO(con);
		return lbDAO.readAll();
	}
	public List<Borrower> getAllBorrowers() throws Exception{
		Connection con=ConnectionUtils.getConnection();
		BorrowerDAO bDAO=new BorrowerDAO(con);
		return bDAO.readAll();
	}
	public String decodeString(String s){
		return HtmlUtils.htmlUnescape(s);
	}
	public int getId(String trim) throws Exception{
		Connection con=ConnectionUtils.getConnection();
		AuthorDAO aDAO=new AuthorDAO(con);
		return aDAO.getId(trim);	
	}
	public List<BookLoans> getAllBookLoans(int cardNo) throws Exception{
		if(cardNo!=0){
			Connection con=ConnectionUtils.getConnection();
			BookLoansDAO blDAO=new BookLoansDAO(con);
			return blDAO.getRowsByCardNo(cardNo);
		}
		return null;
	}
	public void updateDueDate(BookLoans bl) throws Exception {
		Connection con=ConnectionUtils.getConnection();
		try{
		BookLoansDAO blDAO=new BookLoansDAO(con);
		blDAO.updateByDueDate(bl);
		con.commit();
		}catch(Exception e){
			con.rollback();
			throw new Exception("Override Due date failed. Try again.");
		}
		
	}
}
