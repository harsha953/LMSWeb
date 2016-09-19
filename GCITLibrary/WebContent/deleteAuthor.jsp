<%int authorId=Integer.parseInt(request.getParameter("authorId"));
%>
<div class="modal-content">

	
	<form action="deleteAuthor" method="post">
	<div class="modal-body">
		
			<label for="sure">Are You sure?</label> 
			<input type="hidden"
				value="<%=authorId %>" name="authorId">
		
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
		<input type="submit" class="btn btn-primary" value="yes" />
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