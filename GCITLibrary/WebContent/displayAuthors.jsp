<%@page import="java.util.ArrayList"%>
<%@page import="com.library.entity.Author"%>
<%@page import="java.util.List"%>
<%@page import="com.library.service.Adminstrator"%>
<%@include file="includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	Adminstrator admin = Adminstrator.getInstance();
	int totalCount=admin.getAuthorsCount();
	int pageSize=10;
	int pageCount=0;
	if(totalCount%pageSize>0)
		pageCount=(totalCount/pageSize)+1;
	else
		pageCount=totalCount/pageSize;
	List<Author> list=new ArrayList<Author>();
		//if(request.getAttribute("authors")!=null){
	// list=(List<Author>)request.getAttribute("authors");
		//}else{
	list = admin.getAllAuthors(1, null);
		//}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Authors</title>
<script>
function search(pageNo) {
	$.ajax({
			url : "pageAuthors",
			data : {
				searchString : $("#searchString").val(),
			// pageNo : pageNo
			}
		}).done(function(data) {

			$("#pageAuthors").html(data);

		})
		$.ajax({
			url : "searchAuthors",
			data : {
				searchString : $("#searchString").val(),
				pageNo : pageNo
				}
			}).done(function(data) {
					if (data == 0) {
						$('#panel').hide();
						$("#none").show();
						$("#none").html("<br><span class='alert alert-info' role='alert'>No items match your search</span>");
							} else {
								$("#none").hide();
								$('#panel').show();

								$("#authorTable").html(data);
							}
						})

	}
	$(function() {
		$("#searchString").keydown(function() {
			if (event.keyCode == 13) {
				$("#search").click();
			}
		});
	});
</script>
<style type="text/css">
#search {
	font-size: 20px;
}
</style>
</head>
<body>


	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Author Name"
						name="searchString" id="searchString" autocomplete="off">
					<span class="input-group-btn">
						<button class="btn btn-default" id="search" type="button"
							onclick="search(1)">
							<i class="glyphicon glyphicon-search"></i>
						</button>
					</span>
				</div>
			</div>
		</div>
		<br>

		<%
			String result = (String) request.getAttribute("result");
			String flag = (String) request.getAttribute("flag");

			if (result != null && flag.equals("false")) {
		%>
		<div class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Success!</strong>
			<%=result%></div>
		<%
			} else if (flag != null && flag.equals("true")) {
		%>
		<div class="alert alert-danger">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong>Error :</strong>
			<%=result%></div>

		<%
			}
		%>


		<nav aria-label="Page navigation" id="pageAuthors">
			<ul class="pagination">
				<li><a href="#" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
				<%
					for (int i = 1; i <= pageCount; i++) {
				%>
				<li class="page-item"><a href='javascript:search(<%=i%>)'><%=i%></a></li>
				<%
					}
				%>
				<li><a href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
		<span id="none"></span>
		<div class="panel panel-primary" id="panel">
			<!-- Default panel contents -->
			<div class="panel-heading">List of Authors</div>
			<table class="table table-hover" id="authorTable">
				<thead>
					<tr>
						<th>#</th>
						<th>Author Name</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (Author a : list) {
					%>
					<tr>
						<th scope="row"><%=list.indexOf(a) + 1%></th>
						<td><%=a.getAuthorName()%></td>
						<td><button type="button" class="btn btn-sm btn-success"
								href="updateAuthor.jsp?authorId=<%=a.getAuthorId()%>"
								data-target="#editModal" data-toggle="modal">
								<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
								Edit
							</button></td>
						<td><button type="button" class="btn btn-sm btn-danger"
						href="deleteAuthor.jsp?authorId=<%=a.getAuthorId()%>"
								data-target="#deleteModal" data-toggle="modal">
								<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
								Delete
							</button></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<div id="editModal" class="modal fade bd-example-modal-lg"
				tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
				aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content"></div>
				</div>
			</div>
			<div id="deleteModal" class="modal fade bd-example-modal-sm"
				tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
				aria-hidden="true">
				<div class="modal-dialog modal-sm">
					<div class="modal-content"></div>
				</div>
			</div>
			
		</div>
	</div>
	
</body>
</html>