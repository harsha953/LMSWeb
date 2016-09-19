
<%@page import="com.library.entity.Publisher"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.library.entity.Author"%>
<%@page import="java.util.List"%>
<%@include file="includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<%
	List<Publisher> publishers=(List<Publisher>)request.getAttribute("publisherList");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Publisher</title>
</head>
<body>
	<div class="form-group">
	<form action="updatePublisher" method="post" >
    	<label for="PublisherName">Select Publisher Name to Update:<span style="color:red;">*</span></label>
    	<select name="publisherId" class="form-control" style="width: 400px;">
    	<option disabled selected> -- select an option -- </option>
    	<% for(Publisher p:publishers){%>
    		<option value="<%=p.getPublisherId()%>"><%=p.getPublisherName() %></option>
    	<% }%>
    	</select><br>
    	<label>TO Update, Enter the following details:</label>
    	<br>
    	<label for="publisherName">Publisher Name<span style="color:red;">*</span></label>
    <input type="text" class="form-control" name="publisherName" style="width: 400px;" placeholder="Enter publisher Name">
    <br>
     <label for="publisherAddress">Publisher Address</label>
    <input type="text" class="form-control" name="publisherAddress" style="width: 400px;" placeholder="Enter Publisher Address">
    <br>
    <label for="publisherPhone">Publisher Phone</label>
    <input type="text" class="form-control" name="publisherPhone" style="width: 400px;" placeholder="Enter Publisher Phone">
    <br>
		<input type="submit" class="btn btn-primary" value="Update Publisher"/> 
	</form>
	
</div>

</body>
</html>