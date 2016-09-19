<%@page import="java.util.Date"%>
<%@page import="com.library.entity.Author"%>
<%@page import="com.library.service.Adminstrator"%>


<%  //Date d=(Date)
System.out.println(request.getParameter("dueDate"));
%>
<div class="modal-content">

	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Override Due date</h4>
	</div>
	<form action="overrideDueDate" method="post">
	<div class="modal-body">
		
			<input type="date" class="form-control" name="dueDate" value="<%=request.getParameter("dueDate")%>" min="<%=request.getParameter("dateOut")%>" >
			<input type="hidden" value="<%=request.getParameter("cardNo")%>" name="cardNo">
			<input type="hidden" value="<%=request.getParameter("branchId")%>" name="branchId">
			<input type="hidden" value="<%=request.getParameter("bookId")%>" name="bookId">
		
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		<input type="submit" class="btn btn-primary" value="Save changes" />
	</div>
	</form>
</div>

<script>
$(document).ready(function()
		{
		    $('.modal').on('hidden.bs.modal', function(e)
		    { 
		        $(this).removeData();
		    }) ;
		});
</script>