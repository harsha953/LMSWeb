<%@include file="includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Borrower</title>
</head>
<body>
	<div class="form-group">
	<form action="addBorrower" method="post" >
		<label for="borrowerName">Borrower name<span style="color:red;">*</span></label>
    <input type="text" class="form-control" name="borrowerName" style="width: 400px;" placeholder="Enter Borrower Name">
    <br>
    <label for="borrowerAddress">Borrower Address</label>
    <input type="text" class="form-control" name="borrowerAddress" style="width: 400px;" placeholder="Enter Borrower Address">
    <br>
    <label for="borrowerPhone">Borrower Phone</label>
    <input type="text" class="form-control" name="borrowerPhone" style="width: 400px;" placeholder="Enter Borrower Phone">
    <br>
		<input type="submit" class="btn btn-primary" value="Add Borrower"/> 
	</form>
	
</div>

</body>
</html>