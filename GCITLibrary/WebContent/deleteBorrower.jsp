
<%@page import="com.library.entity.Borrower"%>
<%@page import="java.util.ArrayList"%>
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
<title>Delete Borrower</title>
</head>
<body>
	<div class="form-group">
	<form action="deleteBorrower" method="post" >
    	<label for="BorrowerName">select Borrower Name to Delete<span style="color:red;">*</span></label>
    	<select name="cardNo" class="form-control" style="width: 400px;">
    	<option disabled selected> -- select an option -- </option>
    	<% for(Borrower b:borrowers){%>
    		<option value="<%=b.getCardNo()%>"><%=b.getName() %></option>
    	<% }%>
    	</select><br>
    
		<input type="submit" class="btn btn-primary" value="Delete Borrower"/> 
	</form>
	
</div>

</body>
</html>