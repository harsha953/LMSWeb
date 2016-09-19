<%@page import="com.library.entity.Publisher"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.library.entity.Author"%>
<%@page import="java.util.List"%>
<%@include file="includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<%
	List<Author> authors=(List<Author>)request.getAttribute("authorList");
	List<Publisher> publisher=(List<Publisher>)request.getAttribute("publisherList");

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Book</title>
</head>
<body>
	<div class="form-group">
	<form action="addBook" method="post" >
		<label for="bookTitle">Book title<span style="color:red;">*</span></label>
    <input type="text" class="form-control" name="title" style="width: 400px;" placeholder="Enter Book title">
    <br>
    	<label for="AuthorName">Author's Name (multiple select)</label>
    	<select multiple name="authorId" class="form-control" style="width: 600px;">
    	<option disabled selected value="0"> -- select options -- </option>
    	<% for(Author a:authors){%>
    		<option value="<%=a.getAuthorId()%>"><%=a.getAuthorName() %></option>
    	<% }%>
    	</select><br>
    	<label for="publisherName">Publisher Name</label>
    	<select name="pubId" class="form-control" style="width: 600px;">
    	<option disabled selected value="0"> -- select an option -- </option>
    	
    	<% for(Publisher p:publisher){%>
    		<option value="<%=p.getPublisherId()%>"><%=p.getPublisherName()%></option>
    	<% }%>
    	</select><br>
		<input type="submit" class="btn btn-primary" value="Add Book"/> 
	</form>
	
</div>

</body>
</html>