<html>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">

   
    
    <link href="css/bootstrap.min.css" rel="stylesheet">


    <link href="css/starter-template.css" rel="stylesheet">
       <link href="css/bootstrap-tagsinput.css" rel="stylesheet">
     <link href="css/bootstrap-tokenfield.min.css" rel="stylesheet">
      <link href="css/jquery-ui.css" rel="stylesheet">
      <link href="css/docs.css" rel="stylesheet">
      <link href="css/pygments-manni.css" rel="stylesheet">
    


<style type="text/css">
body{
	margin:30px;
}
.dropdown-submenu {
	position: relative;
}

.dropdown-submenu>.dropdown-menu {
	top: 0;
	left: 100%;
	margin-top: -6px;
	margin-left: -1px;
	-webkit-border-radius: 0 6px 6px 6px;
	-moz-border-radius: 0 6px 6px 6px;
	border-radius: 0 6px 6px 6px;
}
/*.dropdown-submenu:hover>.dropdown-menu{display:block;}*/
.dropdown-submenu>a:after {
	display: block;
	content: " ";
	float: right;
	width: 0;
	height: 0;
	border-color: transparent;
	border-style: solid;
	border-width: 5px 0 5px 5px;
	border-left-color: #cccccc;
	margin-top: 5px;
	margin-right: -10px;
}

.dropdown-submenu:hover>a:after {
	border-left-color: #ffffff;
}

.dropdown-submenu.pull-left {
	float: none;
}

.dropdown-submenu.pull-left>.dropdown-menu {
	left: -100%;
	margin-left: 10px;
	-webkit-border-radius: 6px 0 6px 6px;
	-moz-border-radius: 6px 0 6px 6px;
	border-radius: 6px 0 6px 6px;
}
</style>
<body role="document">

	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				 <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
				<a class="navbar-brand" href="index.jsp">GCIT Library</a>
			</div>

			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">
					<!-- Administrator Menu -->
					<li class="menu-item dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown">Admin<b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li class="menu-item dropdown dropdown-submenu"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">Author
									Services</a>
								<ul class="dropdown-menu">
									<li class="menu-item "><a href="addAuthor.jsp">Add Author</a></li>
									<li role="separator" class="divider"></li>
									<li class="menu-item "><a href="displayAuthors.jsp">Update Author</a></li>
									<li role="separator" class="divider"></li>
									<li class="menu-item "><a href="displayAuthors.jsp">Delete Author</a></li>
									<li role="separator" class="divider"></li>
									<li class="menu-item "><a href="displayAuthors.jsp">Display Authors</a></li>
								</ul></li>
							 <li role="separator" class="divider"></li>
							<li class="menu-item dropdown dropdown-submenu"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">Book
									Services</a>
								<ul class="dropdown-menu">
									<li class="menu-item "><a href="showAddBook">Add Book</a></li>
									<li role="separator" class="divider"></li>
									<li class="menu-item "><a href="displayBooks.jsp">Update Book</a></li>
									<li role="separator" class="divider"></li>
									<li class="menu-item "><a href="displayBooks.jsp">Delete Book</a></li>
									<li role="separator" class="divider"></li>
									<li class="menu-item "><a href="displayBooks.jsp">Display Book</a></li>
								</ul></li>
							<li role="separator" class="divider"></li>
							<li class="menu-item dropdown dropdown-submenu"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">Publisher
									Services</a>
								<ul class="dropdown-menu">
									<li class="menu-item "><a href="addPublisher.jsp">Add Publisher</a></li>
									<li role="separator" class="divider"></li>
									<li class="menu-item "><a href="showUpdatePublisher">Update Publisher</a></li>
									<li role="separator" class="divider"></li>
									<li class="menu-item "><a href="showDeletePublisher">Delete Publisher</a></li>
									<li role="separator" class="divider"></li>
									<li class="menu-item "><a href="showAllPublisher">Display Publisher</a></li>
								</ul></li>
								<li role="separator" class="divider"></li>
							<li class="menu-item dropdown dropdown-submenu"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">Library branch
									Services</a>
								<ul class="dropdown-menu">
									<li class="menu-item "><a href="addBranch.jsp">Add Branch</a></li>
									<li role="separator" class="divider"></li>
									<li class="menu-item "><a href="showUpdateBranch">Update Branch</a></li>
									<li role="separator" class="divider"></li>
									<li class="menu-item "><a href="showDeleteBranch">Delete Branch</a></li>
									<li role="separator" class="divider"></li>
									<li class="menu-item "><a href="showAllBranch">Display Branch</a></li>
								</ul></li>
									<li role="separator" class="divider"></li>
							<li class="menu-item dropdown dropdown-submenu"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">Borrower
									Services</a>
								<ul class="dropdown-menu">
									<li class="menu-item "><a href="addBorrower.jsp">Add Borrower</a></li>
									<li role="separator" class="divider"></li>
									<li class="menu-item "><a href="showUpdateBorrower">Update Borrower</a></li>
									<li role="separator" class="divider"></li>
									<li class="menu-item "><a href="showDeleteBorrower">Delete Borrower</a></li>
									<li role="separator" class="divider"></li>
									<li class="menu-item "><a href="showAllBorrower">Display Borrower</a></li>
								</ul></li>
									<li role="separator" class="divider"></li>
								<li class="menu-item dropdown dropdown-submenu"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown">Book Loan Services</a>
								<ul class="dropdown-menu">
									<li class="menu-item "><a href="dueDate.jsp">Override Due Date</a></li>
									
									
								</ul></li>
						</ul></li>

					<!-- Librarian Menu -->
					<li class="menu-item dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown">Librarian<b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li class="menu-item "><a href="showUpdateBranch">Update Branch</a></li>
							<li role="separator" class="divider"></li>
							<li class="menu-item "><a href="showAddBookCopies">Add Book Copies</a></li>
						</ul>
					</li>

					<!-- Borrower Menu -->
					<li class="menu-item dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown">Borrower<b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li class="menu-item "><a href="showCheckOutBook">Checkout Book</a></li>
							<li role="separator" class="divider"></li>
							<li class="menu-item "><a href="returnBook.jsp">Return Book</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>
  <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="js/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="js/jquery.min.js"><\/script>')</script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-tagsinput.js"></script>
  <script src="js/bootstrap-tokenfield.min.js"></script>
   <script src="js/jquery-ui.js"></script>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->


	<script type='text/javascript'>
		$(document).ready(
				function() {

					$('ul.dropdown-menu [data-toggle=dropdown]').on(
							'mouseenter',
							function(event) {
								
								// Avoid following the href location when clicking
								event.preventDefault();
								// Avoid having the menu to close when clicking
								event.stopPropagation();
								// If a menu is already open we close it
								$('ul.dropdown-menu [data-toggle=dropdown]').parent().removeClass('open');
								
								// opening the one you clicked on
								$(this).parent().addClass('open');

								var menu = $(this).parent().find("ul");
								var menupos = menu.offset();

								if ((menupos.left + menu.width()) + 30 > $(
										window).width()) {
									var newpos = -menu.width();
								} else {
									var newpos = $(this).parent().width();
								}
								menu.css({
									left : newpos
								});

							});

				});
	
	</script>
</html>