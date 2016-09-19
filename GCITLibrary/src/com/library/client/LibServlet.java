package com.library.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.HtmlUtils;

import com.library.entity.Book;
import com.library.entity.BookCopies;
import com.library.entity.LibraryBranch;
import com.library.service.Adminstrator;
import com.library.service.Librarian;

@WebServlet(name="/LibServlet",
urlPatterns={"/showAddBookCopies", "/addBookCopies", "/twoDropdown"})
public class LibServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LibServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String function=request.getServletPath();
		Librarian lib=Librarian.getInstance();

		if (function.equals("/twoDropdown")) {
			try {
				String val1=encodeString(request.getParameter("val1"));
				String val2=encodeString(request.getParameter("val2"));
				BookCopies bc=new BookCopies();
				Book b=new Book();
				LibraryBranch lb=new LibraryBranch();
				lb.setBranchId(Integer.parseInt(val1));
				b.setBookId(Integer.parseInt(val2));
				bc.setBook(b);
				bc.setLibraryBranch(lb);
				int noOfCopies;
				noOfCopies = lib.getNoOfCopiesForBranch(bc);
				response.getWriter().write("Existing Number of Copies :"+noOfCopies);
			}catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write("operation failed");
			}
		}else{
			doPost(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String function=request.getServletPath();
		RequestDispatcher rd=request.getServletContext().getRequestDispatcher("/index.jsp");
		try {
			switch (function) {
			case "/showAddBookCopies":
				showAddBookCopies(request);
				rd=request.getServletContext().getRequestDispatcher("/addBookCopies.jsp");
				break;
			case "/addBookCopies":
				addBookCopies(request);
				request.setAttribute("result", "Add copies to branch is successful!!");
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", e.getMessage());
		}
		rd.forward(request, response);
	}

	private void addBookCopies(HttpServletRequest request) throws Exception{
		try{
		int branchId=Integer.parseInt(encodeString(request.getParameter("branchId")));
		int bookId=Integer.parseInt(encodeString(request.getParameter("bookId")));
		
		int noOfCopies=Integer.parseInt(encodeString(request.getParameter("noOfCopies")));
		Librarian lib=Librarian.getInstance();	
		BookCopies bc=new BookCopies();
			Book b=new Book();
			LibraryBranch lb=new LibraryBranch();
			lb.setBranchId(branchId);
			b.setBookId(bookId);
			bc.setBook(b);
			bc.setLibraryBranch(lb);
			int old=lib.getNoOfCopiesForBranch(bc);
			bc.setNoOfCopies(noOfCopies+old);
			lib.addBookCopiesToBranch(bc);
		}catch(Exception e){
			throw new Exception("Fields cannot be empty");
		}
	}

	private void showAddBookCopies(HttpServletRequest request) throws Exception {
		Adminstrator admin=Adminstrator.getInstance();
		List<LibraryBranch> branchList=admin.getAllBranches();
		request.setAttribute("branchList", branchList);
		List<Book> bookList=admin.getAllBooks();
		request.setAttribute("bookList", bookList);
	}
	private String encodeString(String s){
		return HtmlUtils.htmlEscape(s);
	}
}
