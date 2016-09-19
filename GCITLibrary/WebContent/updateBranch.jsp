
<%@page import="com.library.entity.LibraryBranch"%>
<%@page import="com.library.entity.Publisher"%>
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
<title>Update Branch</title>
</head>
<body>
	<div class="form-group">
	<form action="updateBranch" method="post" >
    	<label for="branchName">Select Branch Name to Update:<span style="color:red;">*</span></label>
    	<select name="branchId" class="form-control" style="width: 400px;">
    	<option disabled selected value> -- select an option -- </option>
    	<% for(LibraryBranch b:branch){%>
    		<option value="<%=b.getBranchId()%>"><%=b.getBranchName()+","+b.getBranchAddress()%></option>
    	<% }%>
    	</select><br>
    	
    	<label>TO Update, Enter the following details:</label>
    	<br>
  		<label for="branchName">Branch Name<span style="color:red;">*</span></label>
    <input type="text" class="form-control" name="branchName" style="width: 400px;" placeholder="Enter Branch Name">
    <br>
    <label for="branchAddress">Branch Address<span style="color:red;">*</span></label>
    <input type="text" class="form-control" name="branchAddress" style="width: 400px;" placeholder="Enter Branch Address">
    <br>
		<input type="submit" class="btn btn-primary" value="Update Branch"/> 
	</form>
	
</div>

</body>
</html>