<%@include file="includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Author</title>
</head>
<body>
	<div class="container">
		<div class="form-group">
			<form action="addAuthor" method="post">
				<label for="authorName">Author's name<span style="color: red;">*</span></label> 
				<input type="text" class="form-control" name="authorName" style="width: 400px;" placeholder="Enter Author's name"> 
				<br>
				 <input type="submit" class="btn btn-primary" value="Add Author" />

			</form>
		</div>
	</div>

</body>
</html>