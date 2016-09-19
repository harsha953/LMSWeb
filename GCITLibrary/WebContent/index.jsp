<%@include file="includes.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GCIT Library</title>
</head>
<body>
  <div class="container">
	<%String result=(String)request.getAttribute("result");
	if(result!=null){
	%>
	<span style="color:green;"><%=result %></span>
	<%}else{ %>
	<span style="color:green;"></span>
	<%} %>
      <div class="starter-template">
        <h1>Welcome to GCIT Library</h1>
       </div>

    </div>
</body>
</html>