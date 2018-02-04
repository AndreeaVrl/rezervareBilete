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
		alert(${succes});
	</c:if>
	<c:if test="${not empty exceptie}">
		alert(${exceptie});
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
						<li class="active"><a href="/">Home</a></li>
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

<section>	
	
<section>
	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped">
			    <tbody>
			      <tr>
			        <th>Numar bilet</th>
			        <td>${bilet.id}</td>
			      </tr>
			      <tr>
			        <th>Zbor</th>
			        <td>${bilet.zbor.cursa.aeroport_1.denumire}-${bilet.zbor.cursa.aeroport_2.denumire}</td>
			      </tr>
			      <tr>
			        <th>Nume pasager</th>
			        <td>${bilet.clientBilet.nume} ${bilet.clientBilet.prenume}</td>
			      </tr>
			      <tr>
			        <th>Data nasterii</th>
			        <td>${bilet.clientBilet.dataNasterii}</td>
			      </tr>
			      <tr>
			        <th>Rezervarea realizata de</th>
			        <td>${bilet.clientRezervare.nume} ${bilet.clientRezervare.prenume}</td>
			      </tr>
			      <tr>
			        <th>Pachet</th>
			        <td>${bilet.pachet.denumire}</td>
			      </tr>
			      <tr>
			        <th>Facilitati</th>
			        <td>${bilet.pachet.caracteristici}</td>
			      </tr>
			      <tr>
			        <th>Loc</th>
			        <td>${bilet.loc.rand}${bilet.loc.coloana}</td>
			      </tr>
			      <tr>
			        <th>Pret loc</th>
			        <td>${bilet.pachet.taxaPachet}</td>
			      </tr>
			      <tr>
			        <th>Pret zbor</th>
			        <td>${bilet.zbor.pret}</td>
			      </tr>
			      <tr>
			        <th>Pret total</th>
			        <td>${suma}</td>
			      </tr>
			    </tbody>
		  </table>
	  </div>
	</div>
</section>

<!-- Cautare zbor -->
	<section class="tour section-wrapper container">
		<h2 class="section-title" id="book">Make a rezervation</h2>
		<p class="section-subtitle">Where would you like to go?</p>
		<div class="row">
			
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
