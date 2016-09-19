
<%@page import="com.library.entity.LibraryBranch"%>
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
	List<LibraryBranch> branch=(List<LibraryBranch>)request.getAttribute("branchList");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Branch</title>
</head>
<body>
	<div class="form-group">
	<form action="deleteBranch" method="post" >
    	<label for="BranchName">select Branch Name to Delete<span style="color:red;">*</span></label>
    	<select name="branchId" class="form-control" style="width: 400px;">
    	<option disabled selected> -- select an option -- </option>
    	<% for(LibraryBranch b:branch){%>
    			<option value="<%=b.getBranchId()%>"><%=b.getBranchName() %></option>
    	<% }%>
    	</select><br>
    
		<input type="submit" class="btn btn-primary" value="Delete Branch"/> 
	</form>
	
</div>

</body>
</html>