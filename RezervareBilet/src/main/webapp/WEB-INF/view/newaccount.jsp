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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />

<!-- customizare finala css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/rezervareBilete.css">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<title>Euro Travel</title>
<script type="text/javascript">
$(document).ready(function(){
	<c:if test="${not empty succes}">
		var succes = ${succes};
		alert(succes);
	</c:if>
	<c:if test="${not empty exceptie}">
		var exceptie = ${exceptie};
		alert(exceptie);
	</c:if>
});
</script>

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

<section  style="min-height: 600px">
<h2 class="section-title">New Account</h2>
	<c:if test="${not empty succes}">
			<div class="close" data-dismiss="alert" aria-label="close">×</div>
			<div class="alert alert-success"><strong>Succes!</strong> ${succes}</div>
		<br>
	</c:if>
	
	<c:if test="${not empty exceptie}">
			<div class="close" data-dismiss="alert" aria-label="close">×</div>
			<div class="alert alert-danger"><strong>Eroare!</strong>${exceptie}</div>
		<br>
	</c:if>
	<div class="row">
      <form:form class="form-signin" method="POST" action="createNewAccount" modelAttribute="clientBean">
        <label for="firstName" class="sr-only">First Name</label>
        <form:input type="text" id="firstName" class="form-control border-radius" placeholder="First Name"  value="${clientBean.prenume}" path="nume" required="true"/>
        <label for="lastName" class="sr-only">Last Name</label>
        <form:input type="text" id="lastName" class="form-control border-radius" placeholder="Last Name"  value="${clientBean.nume}" path="prenume" required="true"/>
        <label for="userName" class="sr-only">Email address</label>
        <form:input type="text" id="userName" class="form-control border-radius" placeholder="Email Adress"  value="${clientBean.email}" path="email" required="true"/>
        <label for="password" class="sr-only">Password</label>
        <form:input type="password" id="password" class="form-control border-radius" placeholder="Password" value="${clientBean.parola}" path="parola" required="true"/>
        <button class="btn btn-primary border-radius custom-button" type="submit" id="login" value="Log In">Log in</button>
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


</body>
</html>
