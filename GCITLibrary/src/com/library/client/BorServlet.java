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
import com.library.entity.BookLoans;
import com.library.entity.Borrower;
import com.library.entity.LibraryBranch;
import com.library.service.Adminstrator;
import com.library.service.BorrowerService;

@WebServlet(name="/BorServlet",
urlPatterns={"/showCheckOutBook", "/checkOutBook",
		"/checkValidity", "/booksDropdown", "/returnBook", "/branchDropdown", "/bookDropInReturn"})
public class BorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BorServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String function=request.getServletPath();
		BorrowerService bor=BorrowerService.getInstance();
		if (function.equals("/checkValidity")) {
			try {
				String val1=encodeString(request.getParameter("val1"));
				boolean check=bor.checkCardNo(val1.trim());
				if (check) {
					response.getWriter().write("valid");
				}else{
					response.getWriter().write("Invalid");
				}
			}catch (Exception e) {
				response.getWriter().write("Invalid");
			}
		}else if(function.equals("/booksDropdown")){
			try {
				String branchId=encodeString(request.getParameter("val1"));
				List<Book> list=bor.getAllBooksForBranch(Integer.parseInt(branchId));
				response.getWriter().write("<option disabled selected value=\"0\"> -- select an option -- </option>");
				if (list!=null) {
					for (Book b : list) {
						response.getWriter().write("<option value="+b.getBookId()+">"+b.getTitle()+"</option>");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write("operation failed "+e.getMessage());
			}

		}else if(function.equals("/branchDropdown")){
			String cardNo=encodeString(request.getParameter("val1"));
			try {
				List<LibraryBranch> list=bor.getAllBranchForBorrower(Integer.parseInt(cardNo));
				if (list!=null) {
					response.getWriter().write("<option disabled selected value=\"0\"> -- select an option -- </option>");
					for (LibraryBranch branch : list) {
						response.getWriter().write("<option value="+branch.getBranchId()+">"
								+branch.getBranchName()+","+branch.getBranchAddress()+"</option>");
					}
				}else{
					response.getWriter().write("<option disabled selected value=\"0\"> No Books To Return</option>");
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write("operation failed "+e.getMessage());
			}
		}else if(function.equals("/bookDropInReturn")){
			String val1=encodeString(request.getParameter("val1"));
			String val2=encodeString(request.getParameter("val2"));
			try {
				List<Book> list=bor.getAllBookForBorrower(Integer.parseInt(val2), Integer.parseInt(val1));
				response.getWriter().write("<option disabled selected value=\"0\"> -- select an option -- </option>");
				for (Book book : list) {
					response.getWriter().write("<option value="+book.getBookId()+">"
							+book.getTitle()+"</option>");
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write("operation failed "+e.getMessage());
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
			case "/showCheckOutBook":
				showCheckOutBook(request);
				rd=request.getServletContext().getRequestDispatcher("/checkOutBook.jsp");
				break;
			case "/checkOutBook":
				CheckOutBook(request);
				request.setAttribute("result", "CheckOut Book is successful!!");
				break;
			case "/returnBook":
				returnBook(request);
				request.setAttribute("result", "Return Book is successful!!");
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


	private void returnBook(HttpServletRequest request) throws Exception{
		int cardNo=Integer.parseInt(encodeString(request.getParameter("cardNo")));
		int branchId=Integer.parseInt(encodeString(request.getParameter("branchId")));
		int bookId=Integer.parseInt(encodeString(request.getParameter("bookId")));
		Borrower bor=new Borrower();
		bor.setCardNo(cardNo);
		LibraryBranch lb=new LibraryBranch();
		lb.setBranchId(branchId);
		Book b=new Book();
		b.setBookId(bookId);
		BookLoans bl=new BookLoans(b, lb, bor);
		BorrowerService bs=BorrowerService.getInstance();
		bs.updateBookLoans(bl);

	}

	private void CheckOutBook(HttpServletRequest request) throws Exception{
		int cardNo=Integer.parseInt(encodeString(request.getParameter("cardNo")));
		int branchId=Integer.parseInt(encodeString(request.getParameter("branchId")));
		int bookId=Integer.parseInt(encodeString(request.getParameter("bookId")));		
		Adminstrator admin=Adminstrator.getInstance();
		LibraryBranch lb=admin.getBranch(branchId);
		Borrower bor=admin.getBorrower(cardNo);
		Book b=admin.getBook(bookId);
		BookLoans bl=new BookLoans(b, lb, bor);
		BorrowerService bs=BorrowerService.getInstance();
		bs.addBookLoans(bl);
	}


	private void showCheckOutBook(HttpServletRequest request) throws Exception {
		Adminstrator admin=Adminstrator.getInstance();
		List<LibraryBranch> branchList=admin.getAllBranches();
		request.setAttribute("branchList", branchList);
	}
	private String encodeString(String s){
		return HtmlUtils.htmlEscape(s);
	}

}
