package com.library.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.HtmlUtils;

import com.library.entity.Author;
import com.library.entity.Book;
import com.library.entity.BookLoans;
import com.library.entity.Borrower;
import com.library.entity.LibraryBranch;
import com.library.entity.Publisher;
import com.library.service.Adminstrator;


@WebServlet(name="/AdminServlet", 
urlPatterns={"/addAuthor", "/showAddBook", "/addBook", "/addPublisher", "/addBranch", "/addBorrower",
		 "/updateAuthor",  "/updateBook",  "/updatePublisher", "/updateBranch", "/updateBorrower", "/showUpdateBranch",
		"/showDeleteAuthor", "/deleteAuthor", "/showDeleteBook", "/deleteBook", 
		"/showDeletePublisher", "/deletePublisher", "/showDeleteBranch", "/deleteBranch",
		"/showDeleteBorrower", "/deleteBorrower", "/showAllAuthor", 
		"/pageAuthors", "/searchAuthors", "/pageBooks", "/searchBooks", "/searchBookLoans", "/overrideDueDate"})
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String function=request.getServletPath();
		boolean ajax=false;
		String forwardPage="";
		try{
			switch (function) {
			case "/pageAuthors":{
				Adminstrator admin=Adminstrator.getInstance();
				//int pageNo=Integer.parseInt(request.getParameter("pageNo"));
				String searchString = request.getParameter("searchString");
				int totalCount=admin.getAuthorsCountByName(searchString);
				int pageCount = getPageCount(totalCount);
				StringBuffer str = new StringBuffer();
				if(totalCount!=0){
				str.append("<ul class='pagination'>");
				str.append("<li><a href='#' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span></a></li>");
				for (int i = 1; i <= pageCount; i++) {
					str.append("<li class='page-item'><a href='javascript:search("+i+")'>"+i+"</a></li>");
				}
				str.append("<li><a href='#' aria-label='Next'> <span aria-hidden='true'>&raquo;</span></a></li>");
				str.append("</ul>");
				
				}
				response.getWriter().append(str);
				ajax = true;
				
				forwardPage="/displayAuthors.jsp"; break;
				}
			case "/searchAuthors":{
				searchAuthors(request, response);
				ajax=true;
				
				forwardPage="/displayAuthors.jsp";	break;
			}
			case "/pageBooks":{
				
				Adminstrator admin=Adminstrator.getInstance();
				// int pageNo=Integer.parseInt(request.getParameter("pageNo"));
				String searchString = request.getParameter("searchString");
				int totalCount=admin.getBooksCountByName(searchString);
				int pageCount = getPageCount(totalCount);
				StringBuffer str = new StringBuffer();
				if(totalCount!=0){
				str.append("<ul class='pagination'>");
				str.append("<li><a href='#' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span></a></li>");
				for (int i = 1; i <= pageCount; i++) {
					str.append("<li class='page-item'><a href='javascript:search("+i+")'>"+i+"</a></li>");
				}
				str.append("<li><a href='#' aria-label='Next'> <span aria-hidden='true'>&raquo;</span></a></li>");
				str.append("</ul>");
				
				}
				response.getWriter().append(str);
				ajax = true;
				
				forwardPage="/displayBooks.jsp";break;
			}
			case "/searchBooks":{
				
				searchBooks(request, response);
				ajax=true;
				
				forwardPage="/displayBooks.jsp";	break;
			}
			case "/searchBookLoans":{
				
				searchBooksLoans(request, response);
				ajax=true;
				
				forwardPage="/dueDate.jsp";	break;
			}
			default:
				break;
			}
		}catch(Exception e){
			e.printStackTrace();
			//request.setAttribute("result", e.getMessage());
		}
		if(!ajax && !"".equals(forwardPage)){
		RequestDispatcher rd=request.getServletContext().getRequestDispatcher(forwardPage);
		rd.forward(request, response);
		}else if("".equals(forwardPage)){
		doPost(request, response);
		}
	}

	private void searchBooksLoans(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int cardNo=Integer.parseInt(request.getParameter("searchString").trim());
		
		Adminstrator admin=Adminstrator.getInstance();
		List<BookLoans> list=new ArrayList<BookLoans>();
		list=admin.getAllBookLoans(cardNo);
		
		StringBuilder sb=new StringBuilder();
		if(list!=null){
		sb.append("<thead><tr><th>cardNo.</th><th>Branch Name</th><th>Book Name</th><th>Date Out</th><th>Due Date</th></tr></thead>");
		
		for (BookLoans b : list) {
			sb.append("<tbody><tr><th scope='row'>"+b.getBorrower().getCardNo()+"</th>");
			sb.append("<td>"+b.getLibraryBranch().getBranchName()+"</td>");
			sb.append("<td>"+b.getBook().getTitle()+"</td>");

			sb.append("<td>"+b.getDateOut()+"</td>");
			sb.append("<td>"+b.getDueDate()+"</td>");
			String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());

			sb.append("<td><button type='button' class='btn btn-sm btn-success' href='dueDateModal.jsp?cardNo="+b.getBorrower().getCardNo()+"&branchId="+b.getLibraryBranch().getBranchId()+"&bookId="+b.getBook().getBookId()+"&dueDate="+b.getDueDate()+"&dateOut="+date+" 'data-target='#editModal' data-toggle='modal'>");
			sb.append("<span class='glyphicon glyphicon-edit' aria-hidden='true'></span> Override Due Date</button></td>");
		}
		}else{
			sb.append("<div class='alert alert-info' role='alert'><strong>Info: </strong>No Books Taken</div>");
		}
		response.getWriter().append(sb);
		
		
	}

	private void searchBooks(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String searchString=encodeString(request.getParameter("searchString"));
		int pageNo=Integer.parseInt(request.getParameter("pageNo"));
		Adminstrator admin=Adminstrator.getInstance();
		List<Book> list=new ArrayList<Book>();
		list=admin.getAllBook(pageNo, searchString);
		//request.setAttribute("authors", list);
		StringBuilder sb=new StringBuilder();
		if(list!=null){
		sb.append("<thead><tr><th>#</th><th>Title</th><th>Publisher Name</th><th>Authors</th><th>Edit</th><th>Delete</th></tr></thead>");
		
		for (Book b : list) {
			sb.append("<tbody><tr><th scope='row'>"+((list.indexOf(b))+ 1)+"</th>");
			sb.append("<td>"+b.getTitle()+"</td>");
			if (b.getPublisher()!=null) {
				sb.append("<td>"+b.getPublisher().getPublisherName()+"</td>");
			}else{
				sb.append("<td></td>");
			}
			if (b.getAuthorsList()!=null) {
				sb.append("<td>");
				String prefix="";
				for (Author a : b.getAuthorsList()) {
				sb.append(prefix);
				prefix=",";
				sb.append(" "+a.getAuthorName());
				}
				sb.append("</td>");
				
			}else{
				sb.append("<td></td>");
			}
			sb.append("<td><button type='button' class='btn btn-sm btn-success' href='updateBook.jsp?bookId="+b.getBookId()+"' data-target='#editModal' data-toggle='modal'>");
			sb.append("<span class='glyphicon glyphicon-edit' aria-hidden='true'></span> Edit</button></td>");
			sb.append("<td><button type='button' class='btn btn-sm btn-danger' href='deleteBook.jsp?bookId="+b.getBookId()+"' data-target='#deleteModal' data-toggle='modal'>");
			sb.append("<span class='glyphicon glyphicon-trash' aria-hidden='true'></span>Delete</button></td></tr></tbody>");}
		}else{
			sb.append("0");
		}
		response.getWriter().append(sb);
		
	}

	private int getPageCount(int totalCount) {
		int pageSize=10;
		int pageCount=0;
		if(totalCount%pageSize>0)
			pageCount=(totalCount/pageSize)+1;
		else
			pageCount=totalCount/pageSize;
		return pageCount;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String function=request.getServletPath();
		String forwardPage="/index.jsp";
		request.setAttribute("flag", "false");
		try{
			switch (function) {
			case "/addAuthor":
				addAuthor(request);
				request.setAttribute("result", "Adding Author \""+encodeString(request.getParameter("authorName")+"\" successful!!"));
				break;
			case "/addBook":
				addBook(request);
				request.setAttribute("result", "Adding Book \""+encodeString(request.getParameter("title")+"\" successful!!"));
				break;
			case "/showAddBook":
				showAddBook(request);
				forwardPage="/addBook.jsp";
				break;
			case "/addPublisher":
				addPublisher(request);
				request.setAttribute("result", "Adding Publisher \""+encodeString(request.getParameter("publisherName")+"\" successful!!"));
				break;
			case "/addBranch":
				addBranch(request);
				request.setAttribute("result", "Adding Branch \""+encodeString(request.getParameter("branchName")+"\" successful!!"));
				break;
			case "/addBorrower":
				addBorrower(request);
				request.setAttribute("result", "Adding Borrower \""+encodeString(request.getParameter("borrowerName")+"\" successful!!"));
				break;
			case "/updateAuthor":
				forwardPage="/displayAuthors.jsp";
				updateAuthor(request);
				request.setAttribute("result", "Updating Author to \""+encodeString(request.getParameter("authorName")+"\" is successful!!"));
				break;
			case "/updateBook":
				forwardPage="/displayBooks.jsp";
				updateBook(request);
				request.setAttribute("result", "Updating Book is successful!!");
				break;
			case "/showUpdatePublisher":
				showUpdatePublisher(request);
				forwardPage="/updatePublisher.jsp";
				break;
			case "/updatePublisher":
				updatePublisher(request);
				request.setAttribute("result", "Updating Publisher to \""+encodeString(request.getParameter("publisherName")+"\" is successful!!"));
				break;
			case "/showUpdateBranch":
				showUpdateBranch(request);
				forwardPage="/updateBranch.jsp";
				break;
			case "/updateBranch":
				updateBranch(request);
				request.setAttribute("result", "Updating Branch to \""+encodeString(request.getParameter("branchName")+"\" is successful!!"));
				break;
			case "/showUpdateBorrower":
				showUpdateBorrower(request);
				forwardPage="/updateBorrower.jsp";
				break;
			case "/updateBorrower":
				updateBorrower(request);
				request.setAttribute("result", "Updating Borrower to \""+encodeString(request.getParameter("name")+"\" is successful!!"));
				break;
			
			case "/deleteAuthor":
				deleteAuthor(request);
				forwardPage="/displayAuthors.jsp";
				
				request.setAttribute("result", "Delete Author is successful!!");
				break;

			case "/deleteBook":
				deleteBook(request);
				forwardPage="/displayBooks.jsp";
				request.setAttribute("result", "Delete Book is successful!!");
				break;
			case "/showDeletePublisher":
				showUpdatePublisher(request);
				forwardPage="/deletePublisher.jsp";
				break;
			case "/deletePublisher":
				deletePublisher(request);
				request.setAttribute("result", "Delete Publisher is successful!!");
				break;
			case "/showDeleteBranch":
				showUpdateBranch(request);
				forwardPage="/deleteBranch.jsp";
				break;
			case "/deleteBranch":
				deleteBranch(request);
				request.setAttribute("result", "Delete Branch is successful!!");
				break;
			case "/showDeleteBorrower":
				showUpdateBorrower(request);
				forwardPage="/deleteBorrower.jsp";
				break;
			case "/deleteBorrower":
				deleteBorrower(request);
				request.setAttribute("result", "Delete Borrower is successful!!");
				break;
				
			case "/overrideDueDate":
				forwardPage="/dueDate.jsp";
				overrideDueDate(request);
				
				request.setAttribute("result", " Override Due Date is successful!!");
				break;
			default:
				break;
			}			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", e.getMessage());
			request.setAttribute("flag", "true");
		}
		RequestDispatcher rd=request.getServletContext().getRequestDispatcher(forwardPage);
		rd.forward(request, response);
	}

	private void overrideDueDate(HttpServletRequest request) throws Exception {
		try{
		int cardNo=Integer.parseInt(request.getParameter("cardNo"));
		int branchId=Integer.parseInt(request.getParameter("branchId"));
		int bookId=Integer.parseInt(request.getParameter("bookId"));
		String date=request.getParameter("dueDate");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date utilDate=sdf.parse(date);
		java.sql.Date dueDate=new java.sql.Date(utilDate.getTime());
		Borrower br=new Borrower();
		br.setCardNo(cardNo);
		LibraryBranch lb=new LibraryBranch();
		lb.setBranchId(branchId);
		Book b=new Book();
		b.setBookId(bookId);
		BookLoans bl=new BookLoans(b, lb, br);
		bl.setDueDate(dueDate);
		Adminstrator admin=Adminstrator.getInstance();
		admin.updateDueDate(bl);
		request.setAttribute("cardNo", cardNo);
		}catch(Exception e){
			throw new Exception("Card No. is null");
		}
	}

	private void searchAuthors(HttpServletRequest request, HttpServletResponse response ) throws Exception {
		String searchString=encodeString(request.getParameter("searchString"));
		int pageNo=Integer.parseInt(request.getParameter("pageNo"));
		Adminstrator admin=Adminstrator.getInstance();
		List<Author> list=new ArrayList<Author>();
		list=admin.getAllAuthors(pageNo, searchString);
		//request.setAttribute("authors", list);
		StringBuilder sb=new StringBuilder();
		if(list!=null){
		sb.append("<thead><tr><th>#</th><th>Author Name</th><th>Edit</th><th>Delete</th></tr></thead>");
		
		for (Author author : list) {
			sb.append("<tbody><tr><th scope='row'>"+((list.indexOf(author))+ 1)+"</th>");
			sb.append("<td>"+author.getAuthorName()+"</td>");
			sb.append("<td><button type='button' class='btn btn-sm btn-success' href='updateAuthor.jsp?authorId="+author.getAuthorId()+"' data-target='#editModal' data-toggle='modal'>");
			sb.append("<span class='glyphicon glyphicon-edit' aria-hidden='true'></span> Edit</button></td>");
			sb.append("<td><button type='button' class='btn btn-sm btn-danger' href='deleteAuthor.jsp?authorId="+author.getAuthorId()+"' data-target='#deleteModal' data-toggle='modal'>");
			sb.append("<span class='glyphicon glyphicon-trash' aria-hidden='true'></span>Delete</button></td></tr></tbody>");
		}
		}else{
			sb.append("0");
		}
		response.getWriter().append(sb);
	}

	private void deleteBorrower(HttpServletRequest request) throws Exception{
		int cardNo=Integer.parseInt(encodeString(request.getParameter("cardNo")));
		Borrower b=new Borrower();
		b.setCardNo(cardNo);
		Adminstrator admin=Adminstrator.getInstance();
		admin.deleteBorrower(b);

	}

	private void deleteBranch(HttpServletRequest request) throws Exception{
		int branchId=Integer.parseInt(encodeString(request.getParameter("branchId")));
		LibraryBranch lb=new LibraryBranch();
		lb.setBranchId(branchId);
		Adminstrator admin=Adminstrator.getInstance();
		admin.deleteBranch(lb);

	}

	private void deletePublisher(HttpServletRequest request) throws Exception {
		int publisherId=Integer.parseInt(encodeString(request.getParameter("publisherId")));
		Publisher p=new Publisher();
		p.setPublisherId(publisherId);
		Adminstrator admin=Adminstrator.getInstance();
		admin.deletePublisher(p);

	}

	private void deleteBook(HttpServletRequest request) throws Exception {
		int bookId=Integer.parseInt(encodeString(request.getParameter("bookId")));
		Book b=new Book();
		b.setBookId(bookId);
		Adminstrator admin=Adminstrator.getInstance();
		admin.deleteBook(b);

	}

	private void deleteAuthor(HttpServletRequest request) throws Exception {
		int authorId=Integer.parseInt(encodeString(request.getParameter("authorId")));
		Author a=new Author();
		a.setAuthorId(authorId);
		Adminstrator admin=Adminstrator.getInstance();
		admin.deleteAuthor(a);

	}

	private void updateBorrower(HttpServletRequest request) throws Exception {
		int cardNo=Integer.parseInt(encodeString(request.getParameter("cardNo")));
		String name=encodeString(request.getParameter("name"));
		String address=encodeString(request.getParameter("address"));
		String phone=encodeString(request.getParameter("phone"));
		Borrower b=new Borrower(name, address, phone);
		b.setCardNo(cardNo);
		Adminstrator admin=Adminstrator.getInstance();
		admin.updateBorrower(b);

	}

	private void showUpdateBorrower(HttpServletRequest request) throws Exception {
		Adminstrator admin=Adminstrator.getInstance();
		List<Borrower> borrowerList=admin.getAllBorrowers();
		request.setAttribute("borrowerList", borrowerList);

	}

	private void showUpdateBranch(HttpServletRequest request) throws Exception{
		Adminstrator admin=Adminstrator.getInstance();
		List<LibraryBranch> branchList=admin.getAllBranches();
		request.setAttribute("branchList", branchList);

	}

	private void updateBranch(HttpServletRequest request) throws Exception{
		int branchId=Integer.parseInt(encodeString(request.getParameter("branchId")));
		String branchName=encodeString(request.getParameter("branchName"));
		String branchAddress=encodeString(request.getParameter("branchAddress"));
		LibraryBranch lb=new LibraryBranch(branchName, branchAddress);
		lb.setBranchId(branchId);
		Adminstrator admin=Adminstrator.getInstance();
		admin.updateBranch(lb);

	}

	private void updatePublisher(HttpServletRequest request) throws Exception{
		int publisherId=Integer.parseInt(encodeString(request.getParameter("publisherId")));
		String publisherName=encodeString(request.getParameter("publisherName"));
		String publisherAddress=encodeString(request.getParameter("publisherAddress"));
		String publisherPhone=encodeString(request.getParameter("publisherPhone"));
		Publisher p=new Publisher(publisherName, publisherAddress, publisherPhone);
		p.setPublisherId(publisherId);
		Adminstrator admin=Adminstrator.getInstance();
		admin.updatePublisher(p);
	}

	private void showUpdatePublisher(HttpServletRequest request) throws Exception {
		Adminstrator admin=Adminstrator.getInstance();
		List<Publisher> publisherList=admin.getAllPublisher();
		request.setAttribute("publisherList", publisherList);

	}

	private void updateBook(HttpServletRequest request) throws Exception{
		String title=encodeString(request.getParameter("title"));
		String authors=request.getParameter("authors");
		int pubId=Integer.parseInt(request.getParameter("pubId"));
		int bookId=Integer.parseInt(request.getParameter("bookId"));
		Adminstrator admin=Adminstrator.getInstance();
		Book old=admin.getBook(bookId);
		Book b=new Book();
		b.setBookId(bookId);
		if (title==null || title.trim().length()==0) {
			b.setTitle(old.getTitle());
		}else{
			b.setTitle(title);
		}
		if ("".equals(authors)) {
			b.setAuthorsList(null);
		}else{
			
			List<Author> aList=new ArrayList<Author>();
			String[] authorsArray=authors.split(",");
			for (String s : authorsArray) {
				Author a=new Author();
				//System.out.println(admin.getId(s.trim()));
				System.out.println(s);
				a.setAuthorId(admin.getId(s.trim()));
				a.setAuthorName(s);
				aList.add(a);
			}
			b.setAuthorsList(aList);
		}
		if(pubId==-1 || pubId==0){
			b.setPublisher(null);
		}else{
			System.out.println("pubId"+pubId);
			b.setPublisher(admin.getPublisher(pubId));
		}
		
		
		admin.updateBook(b);
	}

	

	private void addBorrower(HttpServletRequest request) throws Exception {
		String borrowerName=encodeString(request.getParameter("borrowerName"));
		String borrowerAddress=encodeString(request.getParameter("borrowerAddress"));
		String borrowerPhone=encodeString(request.getParameter("borrowerPhone"));
		Borrower b=new Borrower(borrowerName, borrowerAddress, borrowerPhone);
		Adminstrator admin=Adminstrator.getInstance();
		admin.addBorrower(b);

	}

	private void addBranch(HttpServletRequest request)throws Exception {
		String branchName=encodeString(request.getParameter("branchName"));
		String branchAddress=encodeString(request.getParameter("branchAddress"));
		LibraryBranch branch=new LibraryBranch(branchName, branchAddress);
		Adminstrator admin=Adminstrator.getInstance();
		admin.addBranch(branch);

	}

	private void addPublisher(HttpServletRequest request) throws Exception {
		String publisherName=encodeString(request.getParameter("publisherName"));
		String publisherAddress=encodeString(request.getParameter("publisherAddress"));
		String publisherPhone=encodeString(request.getParameter("publisherPhone"));
		Publisher p=new Publisher(publisherName, publisherAddress, publisherPhone);
		Adminstrator admin=Adminstrator.getInstance();
		admin.addPublisher(p);

	}

	private void showAddBook(HttpServletRequest request) throws Exception {
		Adminstrator admin=Adminstrator.getInstance();
		List<Author> authorList=admin.getAllAuthors();
		List<Publisher> publisherList=admin.getAllPublisher();
		request.setAttribute("authorList", authorList);
		request.setAttribute("publisherList", publisherList);

	}


	private void addBook(HttpServletRequest request) throws Exception {
		String title=encodeString(request.getParameter("title"));
		String[] authorId=null;
		authorId=request.getParameterValues("authorId");
		String pubId=encodeString(request.getParameter("pubId"));
		Adminstrator admin=Adminstrator.getInstance();
		Book b=new Book();
		b.setTitle(title);
		if (pubId==null) {
			b.setPublisher(null);
		}else{
			b.setPublisher(admin.getPublisher(Integer.parseInt(pubId)));
		}
		List<Author> list=new ArrayList<Author>();
		if (authorId==null) {
			list=null;
		}else{
			for (String string : authorId) {
				if (Integer.parseInt(string)==0) {
					list=null;
					break;
				}
				list.add(admin.getAuthor(Integer.parseInt(string)));
			}
		}
		b.setAuthorsList(list);
		admin.addBook(b);

	}

	private void updateAuthor(HttpServletRequest request) throws Exception{
		int authorId=Integer.parseInt(encodeString(request.getParameter("authorId")));
		String authorName=encodeString(request.getParameter("authorName"));
		Adminstrator admin=Adminstrator.getInstance();
		Author a=new Author();
		a.setAuthorId(authorId);
		a.setAuthorName(authorName);
		admin.updateAuthor(a);
	}
	private void addAuthor(HttpServletRequest request) throws Exception {
		String authorName=encodeString(request.getParameter("authorName"));
		Author a=new Author(authorName);
		Adminstrator admin=Adminstrator.getInstance();
		admin.addAuthor(a);

	}

	private String encodeString(String s){
		return HtmlUtils.htmlEscape(s);
	}
	

}
