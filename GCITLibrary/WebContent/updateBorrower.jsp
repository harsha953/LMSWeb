
<%@page import="com.library.entity.Borrower"%>
<%@page import="com.library.entity.Publisher"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.library.entity.Author"%>
<%@page import="java.util.List"%>
<%@include file="includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<%
	List<Borrower> borrowers=(List<Borrower>)request.getAttribute("borrowerList");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Borrower</title>
</head>
<body>
	<div class="form-group">
	<form action="updateBorrower" method="post" >
    	<label for="BorrowerName">Select Borrower Name to Update:<span style="color:red;">*</span></label>
    	<select name="cardNo" class="form-control" style="width: 400px;">
    	<option disabled selected> -- select an option -- </option>
    	<% for(Borrower b:borrowers){%>
    		<option value="<%=b.getCardNo()%>"><%=b.getName() %></option>
    	<% }%>
    	</select><br>
    	<label>TO Update, Enter the following details:</label>
    	<br>
    		<label for="name">Borrower name<span style="color:red;">*</span></label>
    <input type="text" class="form-control" name="name" style="width: 400px;" placeholder="Enter Borrower Name">
    <br>
    <label for="address">Borrower Address</label>
    <input type="text" class="form-control" name="address" style="width: 400px;" placeholder="Enter Borrower Address">
    <br>
    <label for="phone">Borrower Phone</label>
    <input type="text" class="form-control" name="phone" style="width: 400px;" placeholder="Enter Borrower Phone">
    <br>
		<input type="submit" class="btn btn-primary" value="Update Borrower"/> 
	</form>
	
</div>

</body>
</html>