<%@include file="includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Branch</title>
</head>
<body>
	<div class="form-group">
	<form action="addBranch" method="post" >
		<label for="branchName">Branch Name<span style="color:red;">*</span></label>
    <input type="text" class="form-control" name="branchName" style="width: 400px;" placeholder="Enter Branch Name">
    <br>
    <label for="branchAddress">Branch Address<span style="color:red;">*</span></label>
    <input type="text" class="form-control" name="branchAddress" style="width: 400px;" placeholder="Enter Branch Address">
    <br>
  
		<input type="submit" class="btn btn-primary" value="Add Branch"/> 
	</form>
	
</div>

</body>
</html>