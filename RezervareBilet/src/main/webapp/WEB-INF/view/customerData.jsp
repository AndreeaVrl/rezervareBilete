<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<!-- Bootstrap -->
<link rel="stylesheet"	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet"	href="${pageContext.request.contextPath}/resources/css/ionicons.min.css">
<link rel="stylesheet"	href="${pageContext.request.contextPath}/resources/css/owl.carousel.css">
<link rel="stylesheet"	href="${pageContext.request.contextPath}/resources/css/owl.theme.css">
<link rel="stylesheet"	href="${pageContext.request.contextPath}/resources/css/carousel.css">
<link rel="stylesheet"	href="${pageContext.request.contextPath}/resources/css/main.css">
<script src="${pageContext.request.contextPath}/resources/js/validation.js"></script>
<!-- Datepicker -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />

<!-- customizare finala css -->
<link rel="stylesheet"	href="${pageContext.request.contextPath}/resources/css/rezervareBilete.css">
<link href="${pageContext.request.contextPath}/resources/css/rezervareLocuri.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<title>Euro Travel - Passengers Information</title>
	
    </head>
    <body>
    
    	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index" title="HOME"><i
					class="ion-paper-airplane"></i> euro <span>travel</span></a>
			</div>
			<!-- /.navbar-header -->

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
				<c:set var="user" value='<%=request.getSession().getAttribute("client")%>'/>
					<c:if test="${not empty user}">
						<li class="active"><a href="index">Home</a></li>
						<li><a href="goToChangeAccount">Actualizare date</a></li>
						<li><a href="checkin">Check-in</a></li>
						<li><a href="rezervari">Rezervari</a></li>
					</c:if>
					<li><a href="goToCreateNewAccount">Sign-up</a></li>
					<li><a href="goToLoginPage">Log-in</a></li>
					<c:if test="${not empty user}">
						<li><a href="logout">Log-out</a></li>
					</c:if>
				</ul>
				<!-- /.nav -->
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>
	<!-- Home -->
	
	<section class="tour section-wrapper container">
  	<h2 class="section-title">Passengers Information</h2> 
  	<p class="section-subtitle">Please fill passengers data</p>    
  		<div class="col-md-6 col-md-offset-3">
  	 		<form:form action="invoicing" modelAttribute="pasageri">
	        <c:forEach var = "i" begin = "0" end = "${flight.passengers-1}"> 
		      <div class="form-group">
		      	<label for="nume">Name</label>
		        <div class="input-group">
		         	<form:input class="form-control nume border-radius" path="listaClienti[${i}].nume" placeholder="Name"/>
		        </div>
		      </div>
		      <div class="form-group">
		      	<label for="prenume">Surname</label>
		       	<div class="input-group">
		       		<form:input class="form-control prenume border-radius" path="listaClienti[${i}].prenume" placeholder="Surname"/>
		      	</div>
		      </div>
		      <div class="form-group">
		      	<label for="dataNasterii">Birthday</label>
		        <div class="input-group">
		        	<form:input class="datepicker form-control dataNasterii border-radius" path="listaClienti[${i}].dataNasterii" placeholder="dd/mm/yyyy" />
		        </div>
		      </div>
		      <hr />
	        </c:forEach>
	        <div class="col-md-6 col-md-offset-3">
	        	<input type="submit" class="btn btn-primary border-radius custom-button" value="Continue"/>
	       	</div>
        </form:form>
      </div>
  </section>
        
   <footer>
		<div class="container">
			<div class="row">
				<div class="col-xs-4">
					<div class="text-left">
						&copy; Copyright Euro Travels - 2018 MTAPO project
					</div>
				</div>
				<div class="col-xs-4">
					
				</div>
				<div class="col-xs-4">
					<div class="top">
						<a href="#header">
							<i class="ion-arrow-up-b"></i>
						</a>
					</div>
				</div>
			</div>
		</div>		
	</footer>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
		
  <script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/contact.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/script.js"></script>

	<!-- Include Date Range Picker -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
			
        <script>
        $(document).ready(function(){
			$(function() {
				$( ".datepicker" ).datepicker({ 
					format: 'dd/mm/yyyy',
					todayHighlight: true,
					autoclose: true,
				});
			}); 
			 $('form').submit(function(){
	        	  var nume = document.getElementsByClassName('nume');
	        	  var prenume = document.getElementsByClassName('prenume');
	        	  var dataNasterii = document.getElementsByClassName('dataNasterii');
	    		  return !completDatePassengers(nume,prenume,dataNasterii, 'Please fill all passengers data!');
	  		});
        });
        </script>
</body>
</html>