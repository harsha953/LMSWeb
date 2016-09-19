<%@page import="java.util.List"%>
<%@page import="com.library.entity.Publisher"%>
<%@page import="com.library.entity.Book"%>
<%@page import="com.library.entity.Author"%>
<%@page import="com.library.service.Adminstrator"%>
<%
	Adminstrator admin=Adminstrator.getInstance();
	Integer bookId=Integer.parseInt(request.getParameter("bookId"));
	Book b=admin.getBook(bookId);
	Publisher pObj=b.getPublisher();
	
	List<Author> aList=admin.getAllAuthors();
	List<Publisher> pList=admin.getAllPublisher();
%>

<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<h4 class="modal-title">Edit Book</h4>
	</div>
	<form action="updateBook" method="post" id="myform">
	<div class="modal-body">
			<label for="BookTitle">Book Title :</label>
			<input type="text" class="form-control" name="title" placeholder="Edit &quot; <%=b.getTitle() %> &quot;" style="width: 400px;" >
			
			 <label for="Authors">Authors :</label>
			 <input name="authors" type="text" class="form-control" placeholder="Type AuthorName and hit enter" id="tokenfield" value="<%
			if(b.getAuthorsList()!=null){
				String prefix="";
			for(Author a: b.getAuthorsList()){%>
			<%=prefix %>
			<%prefix=","; %>
			<%=a.getAuthorName()%>
			<%}}%>" />
			 <br>
			<br>
			<label for="publisherName">Publisher Name :</label>
			<select id="pub" name="pubId" class="form-control" style="width: 600px;">
    	<option  value="0"> -- select an option -- </option> 
    	  	
    	<% for(Publisher p:pList){ %> 
    		<option value="<%=p.getPublisherId()%>"><%=p.getPublisherName()%></option>
    	<%} %>
    		<option  value="-1"> None </option> 
    	</select><br>
				 <input type="hidden" value="<%=b.getBookId()%>" name="bookId">
				 <%if(pObj!=null){ %>
				  <input type="hidden" value="<%=pObj.getPublisherId() %>" id="publisherId">
					<%}else{ %>
					 <input type="hidden" value="0" id="publisherId">
					 <%} %>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		<input id="save" type="button" class="btn btn-primary" value="Save changes" />
	</div>
	</form>
</div>
	
<script>



	$(document).ready(function() {
		
		$('.modal').on('hidden.bs.modal', function(e) {
			$(this).removeData();
		});
		
		
		$('.modal').on('shown.bs.modal', function(e) {
		//	$('#mycontrolId').val(myvalue).attr("selected", "selected");
			var x=document.getElementById("publisherId").value;
			
			 $("#pub").val(x);
			 $('#save').click(function(){
				
				$('#myform').submit();
			});
		
			var autocompleteSource = [];
			var i=0;
			<%for(Author a:aList){%>
			autocompleteSource[i]={value:'<%=a.getAuthorName()%>', label:'<%=admin.decodeString(a.getAuthorName())%>'};
			  i=i+1;
			  <%}%>
		
			$('#tokenfield').tokenfield({
				  autocomplete: {
					  source:autocompleteSource
				  },
				  showAutocompleteOnFocus: true
				});
			$(".token-input").css("min-width","150px");
			$(".ui-autocomplete").css("z-index","9999");	
			$("#tokenfield").on("tokenfield:createtoken", function(event) {
			    var selectedToken = event.attrs.value;

			    var existingTokens = $('#tokenfield').tokenfield('getTokens');
			    $.each(existingTokens, function(index, token) {
			        if (token.value === event.attrs.value)
			            event.preventDefault();
			    });
			  var flag=0;
			    $.each(autocompleteSource, function(index, token) {
			        if (token.value === event.attrs.value)
			            flag=1;
			    });
			    if(flag==0){
			    	 event.preventDefault();
			    }
			   
			});
		});
		
		
	});
</script>