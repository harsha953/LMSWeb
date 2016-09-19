
<%@page import="com.library.entity.Publisher"%>
<%@page import="com.library.entity.Book"%>
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
<title>Delete Publisher</title>
</head>
<body>
	<div class="form-group">
	<form action="deletePublisher" method="post" >
    	<label for="PublisherName">select Publisher Name to Delete<span style="color:red;">*</span></label>
    	<select name="publisherId" class="form-control" style="width: 400px;">
    	<option disabled selected> -- select an option -- </option>
    	<% for(Publisher p:publishers){%>
    		<option value="<%=p.getPublisherId()%>"><%=p.getPublisherName() %></option>
    	<% }%>
    	</select><br>
    
		<input type="submit" class="btn btn-primary" value="Delete Publisher"/> 
	</form>
	
</div>

</body>
</html>