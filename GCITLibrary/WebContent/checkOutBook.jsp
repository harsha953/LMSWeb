<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
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
<title>Check Out Book</title>
<script>
	function chg(){
		var val1=document.getElementById('one').value;
		var xmlHttp=new XMLHttpRequest();
		xmlHttp.onreadystatechange=function (){
			if(xmlHttp.readyState==4 && xmlHttp.status==200){
				var result=xmlHttp.responseText;
				if(result=="valid"){
					document.getElementById('validity').innerHTML="<span style=\"color:green;\">Card No. is "+result+"</span>";
				document.getElementById("two").disabled=false;
				document.getElementById("three").disabled=false;
				document.getElementById("four").disabled=false;
				}else{
					document.getElementById('validity').innerHTML="<span style=\"color:red;\">Card No. is "+result+"</span>";
					document.getElementById("two").disabled=true;
					document.getElementById("three").disabled=true;
					document.getElementById("four").disabled=true;
				}
				
			}
		};
		xmlHttp.open("GET", "checkValidity?val1="+val1, true);
		xmlHttp.send();
	}
	function bookChg(){
		var val1=document.getElementById('two').value;
		var xmlHttp=new XMLHttpRequest();
		xmlHttp.onreadystatechange=function (){
			if(xmlHttp.readyState==4 && xmlHttp.status==200){
				var result=xmlHttp.responseText;
					document.getElementById('three').innerHTML=result;	
			}
		};
		xmlHttp.open("GET", "booksDropdown?val1="+val1, true);
		xmlHttp.send();
	}
		
</script>
</head>
<body>
	<div class="form-group">
		<form action="checkOutBook" method="post">
			<label for="cardNo">Enter CardNo:<span style="color: red;">*</span></label>
			<input id="one" type="text" class="form-control" name="cardNo"
				onchange="chg()" style="width: 400px;" placeholder="Enter CardNo">
			<br>
			<p id="validity"></p>

			<label for="branchName">Select Branch Name to Check out<span
				style="color: red;">*</span></label> <select id="two" disabled="true"
				name="branchId" class="form-control" style="width: 400px;"
				onchange="bookChg()">
				<option disabled selected value="0">-- select an option --
				</option>
				<% for(LibraryBranch b:branch){%>
				<option value="<%=b.getBranchId()%>"><%=b.getBranchName()+","+b.getBranchAddress()%></option>
				<% }%>
			</select><br> <label for="bookTitle">List of Books in the above
				choosen branch<span style="color: red;">*</span>
			</label> <select id="three" disabled="true" name="bookId"
				class="form-control" style="width: 500px;">
				<option disabled selected value="0">-- select an option --
				</option>

			</select> <br> 
			<input id="four" type="submit" disabled="true"
				class="btn btn-primary" value="CheckOut Book" />
		</form>


	</div>

</body>
</html>