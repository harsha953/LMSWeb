<%@include file="includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Publisher</title>
</head>
<body>
	<div class="form-group">
	<form action="addPublisher" method="post" >
		<label for="publisherName">Publisher name<span style="color:red;">*</span></label>
    <input type="text" class="form-control" name="publisherName" style="width: 400px;" placeholder="Enter Publisher name">
    <br>
    <label for="publisherAddress">Publisher Address</label>
    <input type="text" class="form-control" name="publisherAddress" style="width: 400px;" placeholder="Enter Publisher Address">
    <br>
    <label for="publisherPhone">Publisher Phone</label>
    <input type="text" class="form-control" name="publisherPhone" style="width: 400px;" placeholder="Enter Publisher Phone">
    <br>
		<input type="submit" class="btn btn-primary" value="Add Publisher"/> 
	</form>
	
</div>

</body>
</html>