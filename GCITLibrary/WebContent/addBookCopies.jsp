<%@page import="com.library.entity.Book"%>
<%@page import="com.library.entity.LibraryBranch"%>
<%@page import="java.util.List"%>
<%@include file="includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<%
	List<LibraryBranch> branch=(List<LibraryBranch>)request.getAttribute("branchList");
	List<Book> books=(List<Book>)request.getAttribute("bookList");
	String result=(String)request.getAttribute("result");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Book copies to Branch</title>
<script>
	function chg(){
		var val1=document.getElementById('one').value;
		var val2=document.getElementById('two').value;
		if(val1==0 || val2==0){
			document.getElementById('copies').innerHTML="Existing Number of Copies :n/a";
		}else{
		var xmlHttp=new XMLHttpRequest();
		xmlHttp.onreadystatechange=function (){
			if(xmlHttp.readyState==4 && xmlHttp.status==200){
				document.getElementById('copies').innerHTML=  xmlHttp.responseText;
			}
		};
		xmlHttp.open("GET", "twoDropdown?val1="+val1+"&val2="+val2, true);
		xmlHttp.send();
		}
	}
		
</script>
</head>
<body>
	<div class="form-group">
	<form action="addBookCopies" method="post" >
		<label for="branchName">Select Branch Name<span style="color:red;">*</span></label>
    	<select id="one" name="branchId" class="form-control" style="width: 400px;" onchange="chg()">
    	<option disabled selected value="0"> -- select an option -- </option>
    	<% for(LibraryBranch b:branch){%>
    		<option value="<%=b.getBranchId()%>"><%=b.getBranchName()+","+b.getBranchAddress()%></option>
    	<% }%>
    	</select><br>
    	<label for="bookTitle">select Book Title<span style="color:red;">*</span></label>
    	<select id="two" name="bookId" class="form-control" style="width: 500px;" onchange="chg()">
    	<option disabled selected value="0"> -- select an option -- </option>
    	<% for(Book b:books){%>
    		<option value="<%=b.getBookId()%>"><%=b.getTitle() %></option>
    	<% }%>
    	</select><br>
    	
  		<p id="copies" name="old"></p>
  		<br>
  		<label for="bookCopies">Enter New number of Copies to Add<span style="color:red;">*</span></label>
    <input type="text" class="form-control" name="noOfCopies" style="width: 400px;" placeholder="Enter noOfCopies to Add">
    <br>
  		
		<input type="submit" class="btn btn-primary" value="Add Copies to Branch"/> 
	</form>
	
</div>

</body>
</html>