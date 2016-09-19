<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.library.entity.BookLoans"%>
<%@page import="com.library.service.Adminstrator"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.library.entity.Book"%>
<%@page import="com.library.entity.LibraryBranch"%>
<%@page import="java.util.List"%>
<%@include file="includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<%
	Adminstrator admin = Adminstrator.getInstance();
	List<BookLoans> list = new ArrayList<BookLoans>();
	if (request.getAttribute("cardNo") != null) {
		Integer cardNo = (Integer) request.getAttribute("cardNo");
		list=admin.getAllBookLoans(cardNo);
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Override Due Date</title>
<script>
	function chg(){
		var val1=document.getElementById('searchString').value;
		var xmlHttp=new XMLHttpRequest();
		xmlHttp.onreadystatechange=function (){
			if(xmlHttp.readyState==4 && xmlHttp.status==200){
				var result=xmlHttp.responseText;
				if(result=="valid"){
					
			    	
					document.getElementById('validity').innerHTML="<br><span class='alert alert-success' role='alert'>Card No. is "+result+"</span>";
					$.ajax({
						url : "searchBookLoans",
						data : {
							searchString : $("#searchString").val()
							}
						}).done(function(data) {
								$("#cardTable").html(data);
						})
			
				}else{
					
					document.getElementById('validity').innerHTML="<br><span class='alert alert-danger' role='alert'>Card No. is "+result+"</span>";
					
				}
				
			}
		};
		xmlHttp.open("GET", "checkValidity?val1="+val1, true);
		xmlHttp.send();
		
	

	}

	$(function(){
		$("#searchString").keydown(function(){
		    if(event.keyCode == 13){
		        $("#search").click();
		    }
		});
		$("#searchString").keyup(function() {
		    if (!this.value) {
		    	document.getElementById('validity').innerHTML="";
		    	$('#cardTable').html("");
		    	$('#rsuc').hide();
		    	$('#rdan').hide();
		    }
		});
		});	
</script>
<style type="text/css">
#search{
	font-size : 20px;
}
</style>
</head>
<body>
	<div class="form-group">
		
			<div class="row">
			<div class="col-md-4">
				<div class="input-group">
				<%if(request.getAttribute("cardNo")!=null){ %>
					<input type="text" class="form-control" placeholder="Card No." value="<%=(Integer)request.getAttribute("cardNo")%>"
					name="searchString" id="searchString" autocomplete="off">
				<% }else{%>
				<input type="text" class="form-control" placeholder="Card No."
					name="searchString" id="searchString" autocomplete="off">
				<%} %>
						
						 <span class="input-group-btn">
                <button class="btn btn-default" id="search" type="button" onclick="chg()"><i class="glyphicon glyphicon-search"></i></button>
            </span>
				</div>
				</div>	
		</div>
		
		<div id="validity"></div>
		<br>
		<%
			String result = (String) request.getAttribute("result");
			String flag = (String) request.getAttribute("flag");

			if (result != null && flag.equals("false")) {
		%>
		<div id="rsuc" class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Success!</strong>
			<%=result%></div>
		<%
			} else if (flag != null && flag.equals("true")) {
		%>
		<div id="rdan" class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Error :</strong>
			<%=result%></div>

		<%
			}
		%>
		
		<br>
		<br>
		<table class="table table-hover" id="cardTable">
			<%if(!(list.isEmpty())){ %>
			<thead><tr><th>cardNo.</th><th>Branch Name</th><th>Book Name</th><th>Date Out</th><th>Due Date</th></tr></thead>
				<%for(BookLoans b: list) {%>
					
				<tbody>
					
					<tr>
					<tbody><tr><th scope='row'><%=b.getBorrower().getCardNo()%></th>
					<td><%=b.getLibraryBranch().getBranchName()%></td>
					<td><%=b.getBook().getTitle()%></td>
					<td><%=b.getDateOut()%></td>
					<td><%=b.getDueDate()%></td>
					<%String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date()); %>
					<td><button type='button' class='btn btn-sm btn-success' href='dueDateModal.jsp?cardNo=<%=b.getBorrower().getCardNo()%>&branchId=<%=b.getLibraryBranch().getBranchId()%>&bookId=<%=b.getBook().getBookId()%>&dueDate=<%=b.getDueDate()%>&dateOut=<%=date%>' data-target='#editModal' data-toggle='modal'>
						<span class='glyphicon glyphicon-edit' aria-hidden='true'></span> Override Due Date</button></td>
		
					<tr>
				</tbody>
				<%} %>
				<%} %>
				
			</table>
			<div id="editModal" class="modal fade bd-example-modal-sm"
				tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
				aria-hidden="true">
				<div class="modal-dialog modal-sm">
					<div class="modal-content"></div>
				</div>
			</div>
	


	</div>

</body>
</html>