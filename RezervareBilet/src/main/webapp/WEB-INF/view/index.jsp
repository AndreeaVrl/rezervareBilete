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

<section>	
	<div id="header">
<!-- Carousel
    ================================================== -->
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
      </ol>
      <div class="carousel-inner" role="listbox">
        <div class="item active">
          <img class="first-slide" src="${pageContext.request.contextPath}/resources/images/slide1.jpg" alt="First slide">
          <div class="container">
            <div class="carousel-caption">
              <h1>TO <b>TRAVEL</b> IS TO <b>LIVE</b></h1>
              <p>it leaves you speecless, then turns your into a storyteller.</p>
              <p><a class="btn btn-primary border-radius custom-button" style="width:150px" href="#book" role="button">Book now</a></p>
            </div>
          </div>
        </div>
        <div class="item">
          <img class="second-slide" src="${pageContext.request.contextPath}/resources/images/slide2.jpg" alt="Second slide">
          <div class="container">
            <div class="carousel-caption">
              <h1>TRAVELING ALWAYS <b>"GOOD IDEA"</b></h1>
              <p>it leaves you speecless, then turns your into a storyteller.</p>
              <p><a class="btn btn-primary border-radius custom-button" style="width:150px" href="#book" role="button">Book now</a></p>
            </div>
          </div>
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div><!-- /.carousel -->
</section>
<!--  END Home -->
	
<section>
	<div class="row">
		<c:if test="${not empty exceptie}">
			<div  class="close" data-dismiss="alert" aria-label="close">×</div>
			<div class="alert alert-danger"><strong>Eroare! </strong> ${exceptie}</div>
		</c:if>
	</div>
</section>

<!-- Cautare zbor -->
	<section class="tour section-wrapper container">
		<h2 class="section-title" id="book">Make a rezervation</h2>
		<p class="section-subtitle">Where would you like to go?</p>
		<div class="row">
			<form:form role="form" class="form-dropdown" id="getRouteForm" method="POST" modelAttribute="cursa">
				<div class="col-md-3">
					<div class="col-md-12">
						<h4>From:</h4>
					</div>
					<div class="form-group col-md-12">
						<form:select class="form-control border-radius" path="contryFrom">
							<c:forEach items="${tari}" var="tara">
								<form:option value="${tara.id}">${tara.denumire}</form:option>
							</c:forEach>
						  </form:select>
					</div>
					<div class="form-group col-md-12">
						<form:select class="form-control border-radius" path="airportFrom">
						  <form:option value="">---Selecteaza---</form:option>
						  <c:if test="${not empty aeroportFrom}">
							<form:option value="${aeroportFrom.id}">${aeroportFrom.denumire}</form:option>
						  </c:if>
						</form:select>
					</div>
				</div>
				<div class="col-md-3">
					<div class="col-md-12">
						<h4>To:</h4>
					</div>
					<div class="form-group col-md-12">
						<form:select class="form-control border-radius" path="countryTo">
							<c:forEach items="${tari}" var="tara">
								<form:option value="${tara.id}">${tara.denumire}</form:option>
							</c:forEach>
						  </form:select>
					</div>
					<div class="form-group col-md-12">
						<form:select class="form-control border-radius" path="airportTo">
						  <form:option value="">---Selecteaza---</form:option>
						  <c:if test="${not empty aeroportTo}">
							<form:option value="${aeroportTo.id}">${aeroportTo.denumire}</form:option>
						  </c:if>
						</form:select>
					</div>
				</div>
				<div class="col-md-3">
						<div class="row">
						<div class="col-md-12">
							<div class="col-md-12">
								<h4>Date:</h4>
							</div>
							<div class="form-group col-md-12">
								<div class="input-group" style="margin-bottom: 0px">
									<form:input type="text"	class="form-control border-radius border-right"	placeholder="Departure date" path="departureDate" /> 
									<span	class="input-group-addon border-radius custom-addon"> 
										<i class="ion-ios-calendar"></i>
									</span>
								</div>
							</div>
							</div>
					</div>
					<div class="col-md-3">
						<div class="row">
							<div class="col-md-12">
								<h5 style="margin-top: 0px">Return?</h5>
							</div>
							<div class="form-group col-md-12">
								<form:checkbox path="retur"/>
							</div>
						</div>
					</div>
					<div class="col-md-9" id="flyBackDate">
						<div class="row">
							<div class="form-group col-md-12">
								<div class="input-group" style="margin-bottom: 0px">
									<form:input type="text"	class="form-control border-radius border-right"	placeholder="Fly back" path="flyBack" /> 
									<span	class="input-group-addon border-radius custom-addon"> 
										<i class="ion-ios-calendar"></i>
									</span>
								</div>
							</div>
						</div>	
					</div>
				</div>
				<div class="col-md-3">
						<div class="row">
						<div class="col-md-12">
							<div class="col-md-12">
								<h4>&nbsp;</h4>
							</div>
							<div class="col-md-12" style="padding-bottom:5px">
								<input class="btn btn-primary border-radius custom-button" name="submit" id="submit" type="submit" value="Optimal flight"/>
							</div>
							<div class="col-md-12" style="padding-top:5px">
								<input class="btn btn-primary border-radius custom-button" name="optim" id="optim" type="submit" value="All flights"/>
							</div>
						</div>
						</div>
				</div>
			</form:form>
		</div>
	</section>


<!-- Selectare zbor -->
	<section class="tour section-wrapper container">
		<h2 class="section-title">Choose your flight</h2>
		<p class="section-subtitle">
			<c:if test="${(not empty zboruriCautare or not empty zboruriCautareRetur) and flag eq 0}">
			Optimal flights
			</c:if>	
			<c:if test="${(not empty zboruriCautare or not empty zboruriCautareRetur) and flag eq 1}">
			All flights
			</c:if>
		</p>
		<div class="col-md-12">
		<c:choose>
			<c:when test="${not empty zboruriCautare}">
				<h4>Flights Out</h4>	
				<!-- afisare zboruri  -->
				<div id="cautare">
				<c:forEach items="${zboruriCautare}" var="zbor">
					<c:set var="first" value="1"/>
					<div class="col-md-12">
						<hr />
						<div class="col-md-1">
							<input type="radio" id="departureFlight" name="departureFlight" value="${zbor.key}" />
						</div>
						<div class="col-md-11">
							<c:forEach items="${zbor.value}" var="zboruriCautare" >
							<div class="col-md-12">
								<c:choose>
									<c:when test="${first eq 1}">
										<h4>${zboruriCautare[0].cursa.aeroport_1.denumire} - ${zboruriCautare[0].cursa.aeroport_2.denumire}</h4>
										<select class="form-control border-radius" id="${zboruriCautare[0].cursa.id}-${zbor.key}">
											<c:forEach items="${zboruriCautare}" var="zborCautare">
												<option value="${zborCautare.id}"/>
												${zborCautare.dataPlecare} - ${zborCautare.dataSosire},	${zborCautare.companie.denumire}, from ${zborCautare.pret}&euro; 
											</c:forEach>
										</select>
									</c:when>
									<c:otherwise>
										<h4>- ${zboruriCautare[0].cursa.aeroport_2.denumire}</h4>
										<select class="form-control border-radius" id="${zboruriCautare[0].cursa.id}-${zbor.key}">
											<c:forEach items="${zboruriCautare}" var="zborCautare">
												<option value="${zborCautare.id}"/>
												${zborCautare.dataPlecare} - ${zborCautare.dataSosire}, ${zborCautare.companie.denumire}, from ${zborCautare.pret}&euro;
											</c:forEach>
										</select>
									</c:otherwise>
								</c:choose>
							</div>
							</c:forEach>
						</div>
					</div>
				</c:forEach>
			</div>
			</c:when>	
			<c:when test="${empty zboruriCautare and flag eq 0 }">
				<div  class="close" data-dismiss="alert" aria-label="close">×</div><c:out value="${flag}"/>
				<div class="alert alert-info"><strong>Atentie</strong>Ne pare rau, nu a fost gasit nicun zbor tur pentru datele specificate!</div>
			</c:when>
			<c:when test="${empty zboruriCautare and flag eq 1 }">
				<div  class="close" data-dismiss="alert" aria-label="close">×</div>
				<div class="alert alert-info"><strong>Atentie!</strong>Ne pare rau, nu a fost gasit nicun zbor pentru datele specificate!</div>
			</c:when>
		</c:choose>
		</div>


		<c:if test="${not empty cursaRequestView && cursaRequestView.retur}">
		<div class="col-md-12">
		<hr />
			<c:choose>
				<c:when test="${empty zboruriCautareRetur}">
					<div  class="close" data-dismiss="alert" aria-label="close">×</div>
					<div class="alert alert-info"><strong>Atentie!</strong>Ne pare rau, nu a fost gasit nicun zbor retur pentru datele specificate!</div>
				</c:when>
				<c:when test="${not empty zboruriCautareRetur}">
					<h4>Flights In</h4>
					<!-- afisare zboruri  -->
					<div id="cautare">
						<c:forEach items="${zboruriCautareRetur}" var="zbor">
							<c:set var="first" value="1"/>
							<div class="col-md-12">
								<hr />
								<div class="col-md-1">
									<input type="radio" id="returnFlight" name="returnFlight" value="${zbor.key}" />
								</div>
								<div class="col-md-11">
								<c:forEach items="${zbor.value}" var="zboruriCautare" >
									<div class="col-md-12">
									<c:choose>
										<c:when test="${first eq 1}">
											<h4>${zboruriCautare[0].cursa.aeroport_1.denumire} - ${zboruriCautare[0].cursa.aeroport_2.denumire}</h4>
											<select class="form-control border-radius" id="-${zboruriCautare[0].cursa.id}-${zbor.key}">
												<c:forEach items="${zboruriCautare}" var="zborCautare">
													<option value="${zborCautare.id}"/>
													 ${zborCautare.dataPlecare} - ${zborCautare.dataSosire}, ${zborCautare.companie.denumire}, from ${zborCautare.pret}&euro;
												</c:forEach>
											</select>
										</c:when>
										<c:otherwise>
											<h4>- ${zboruriCautare[0].cursa.aeroport_2.denumire}</h4>
											<select class="form-control border-radius" id="-${zboruriCautare[0].cursa.id}-${zbor.key}">
												<c:forEach items="${zboruriCautare}" var="zborCautare">
													<option value="${zborCautare.id}"/>
													 ${zborCautare.dataPlecare} - ${zborCautare.dataSosire},	${zborCautare.companie.denumire}, from ${zborCautare.pret}&euro;
												</c:forEach>
											</select>
										</c:otherwise>
									</c:choose>
									</div>
								</c:forEach>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:when>
			</c:choose>
		</div>
		</c:if>
	</section>					

			
		
	<section id="divPasageri" class="offer section-wrapper">
		<h2 class="section-title">Choose your flying plan</h2>
		<p class="section-subtitle">Best deal for your trip</p>
		<div class="row">
	    <form:form class="form-inline" id="getPackageForm" action="" method="post" modelAttribute="flightChosen">
		    <form:hidden path="departureCompannies"/>
				<form:hidden path="returnCompannies"/>
		    <form:hidden path="departurFlight"/>
				<form:hidden path="returFlight"/>
				<div class="col-md-12">
					<div class="col-md-4 col-md-offset-4">					
			      <div class="col-md-6">
			        <div class="panel panel-info">
			          <div class="panel-heading">
			            <h3 class="panel-title text-center">Plus</h3>
			          </div>
			          <div class="panel-body">
			            <ul class="pachet-descriere">
			            	<li>Lowest Fare</li>
			              <li>60 day check-in</li>
			              <li>20kg check-in bag</li>
										<li>Priority Boarding</li>
										<li>Reserved standard seat</li>
										<li class="disabled">Flexible tickets</li>
										<li class="disabled">Optional airport check-in</li>
										<li class="disabled">Fast Track</li>
			            </ul>
			          </div>
			          <div class="panel-footer text-center">
			            <form:radiobutton path="packageChosen" value="2" checked="checked"/> &euro; 160 
			          </div>
			        </div>
			      </div>
			      <div class="col-md-6">
			        <div class="panel panel-info">
			          <div class="panel-heading">
			            <h3 class="panel-title text-center">Flexi Plus</h3>
			          </div>
			          <div class="panel-body">
			            <ul class="pachet-descriere">
			            	<li>Lowest Fare</li>
			             	<li>60 day check-in</li>
			              <li>20kg check-in bag</li>
										<li>Priority Boarding</li>
										<li>Reserved standard seat</li>
										<li>Flexible tickets</li>
										<li>Optional airport check-in</li>
										<li>Fast Track</li>
			            </ul>
			          </div>
			          <div class="panel-footer text-center">
			            <form:radiobutton path="packageChosen" value="3" /> &euro; 185 
			          </div>
			        </div>
			      </div>
			      <div class="col-md-6">
								<label for="passengers">Passengers </label>
								<form:input path="passengers" class="form-control border-radius"/>
						</div>
			      <div class="col-md-6 text-center">
			      	<button class="btn btn-primary border-radius custom-button" name="getPackage" id="getPackage" type="submit">Continue</button>
			      </div>
			    </div>
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
		var date_input=$('input[name="departureDate"]'); //our date input has the name "date"
		var container=$('.data-plecare form').length>0 ? $('.data-plecare form').parent() : "body";
		date_input.datepicker({
			format: 'dd/mm/yyyy',
			container: container,
			todayHighlight: true,
			autoclose: true,
			minDate: new Date()
		});
		$('#flyBack').datepicker({
			format: 'dd/mm/yyyy',
			container: container,
			todayHighlight: true,
			autoclose: true,
			minDate: new Date()
		});
		if($('#retur1').is(':checked')){
			$('#flyBackDate').show();
		} else {
			$('#flyBackDate').hide();
		}
		$('#flyBackDate').hide();
		$('#retur1').change( function(){
			if($(this).is(':checked')) {
				$('#flyBackDate').show();
			} else {
				$('#flyBackDate').hide();
			}
		});
		$("#optim").on("click", function(){
			$('#getRouteForm').attr('action', "getAllRoutes");
		});
		$('#submit').click(function(){
		   $('#getRouteForm').attr('action', 'getRoute');
		});
		$('#divPasageri').hide();
		$('input[type=radio][name=departureFlight]').change(function() {
			$('#divPasageri').show();
	    });
		$('input[type=radio][name=zboruriCautareRetur]').change(function() {
			$('#divPasageri').show();
	    });
		$('#getPackage').click( function(){
			var zborAles = $('input[name=departureFlight]:checked').val();
			$('#departurFlight').val(zborAles);
			<c:forEach items="${zboruriCautare}" var="zbor">
				var mapKey = ${zbor.key};
				if( zborAles == mapKey) {
					<c:set var="flag" value="1"/>
					<c:forEach items="${zbor.value}" var="zboruriCautare"  >
					var idCursaSelect = ${zboruriCautare[0].cursa.id}+"-"+mapKey;
						<c:choose>
							<c:when test="${flag eq 1}">
								var value = $("#"+idCursaSelect).val();
								$('#departureCompannies').val(value);
								<c:set var="flag" value="0"/>
							</c:when>
							<c:otherwise>
								var value = $('#departureCompannies').val() +";"+ $("#"+idCursaSelect).val();
								$('#departureCompannies').val(value);
							</c:otherwise>
						</c:choose>
					</c:forEach>
				}
			</c:forEach>
			var zborA = $('input[name=returnFlight]:checked').val();
			$('#returFlight').val(zborA);
			if(zborA){
				<c:forEach items="${zboruriCautareRetur}" var="zbor">
					var mapKey = ${zbor.key};
					if( zborA == mapKey) {
						<c:set var="flag" value="1"/>
						<c:forEach items="${zbor.value}" var="zboruriCautare"  >
						var idCursaSelect = "-"+${zboruriCautare[0].cursa.id}+"-"+mapKey;
							<c:choose>
								<c:when test="${flag eq 1}">
									var value = $("#"+idCursaSelect).val();
									$('#returnCompannies').val(value);
									<c:set var="flag" value="0"/>
								</c:when>
								<c:otherwise>
									var value = $('#returnCompannies').val() +";"+ $("#"+idCursaSelect).val();
									$('#returnCompannies').val(value);
								</c:otherwise>
							</c:choose>
						</c:forEach>
					}
				</c:forEach>
			}
			$('#getPackageForm').attr('action', 'getChosenFlight');
		});	
		function sort(id){
			$(id).append($(id+" option").remove().sort(function(a, b) {
			    var at = $(a).text(), bt = $(b).text();
			    return (at > bt)?1:((at < bt)?-1:0);
			}));
		}
		$('#contryFrom').click(function() {
			var idTara = $('#contryFrom').val();
			$.ajax({
				type: 'POST',
				url: '${pageContext.request.contextPath}/getAirportForContry',
				data: {id: idTara},
				dataType : 'json',
				success: function(data) {
					airportsList=data;
					<c:forEach items="${airportsList}" var="succes">
					</c:forEach>
					$('#airportFrom').find('option').remove();
					$.each(airportsList, function (i, item) {
					    $('#airportFrom').append($('<option>', { 
					        value: item.id,
					        text : item.denumire 
					    }));
					});
					sort('#airportFrom');
				}
			})
		});
		
		$('#countryTo').click(function() {
			var idTara = $('#countryTo').val();
			$.ajax({
				type: 'POST',
				url: '${pageContext.request.contextPath}/getAirportForContry',
				data: {id: idTara},
				dataType : 'json',
				success: function(data) {
					airportsList=data;
					$('#airportTo').find('option').remove();
					$.each(airportsList, function (i, item) {
					    $('#airportTo').append($('<option>', { 
					        value: item.id,
					        text : item.denumire 
					    }));
					});
					sort('#airportTo');
				}
			})
		});
		
		$('#getRouteForm').submit(function () {
    		if ($('input[name="retur"]:checked').length > 0) {
    			var d1 = new Date(document.getElementById("departureDate").value);
    			var d2 = new Date(document.getElementById("flyBack").value);
    			return (!isEmpty('contryFrom','Va rog selectati tara din care doriti sa plecati!') &&
   		    		   !isEmpty('airportFrom','Va rog selectati aeroportul de plecare!') &&
   		    		   !isEmpty('countryTo','Va rog selectati tara din care doriti sa ajungeti!') &&
   		    		   !isEmpty('airportTo','Va rog selectati aeroportul de sosire!') &&
   		    		   !isEmpty('departureDate','Va rog alegeti data de plecare!') && 
		    		   !isEmpty('flyBack','Va rog alegeti data de intoarcere!') &&
		    		   compareDate(d1,d2,"Va rog verificati perioada deplasarii!Data de inceput trebuie sa fie predecesoare celei de retur!"))
 		} else {
    			return (!isEmpty('contryFrom','Va rog selectati tara din care doriti sa plecati!') &&
   		    		   !isEmpty('airportFrom','Va rog selectati aeroportul de plecare!') &&
   		    		   !isEmpty('countryTo','Va rog selectati tara din care doriti sa ajungeti!') &&
   		    		   !isEmpty('airportTo','Va rog selectati aeroportul de sosire!') &&
   		    		   !isEmpty('departureDate','Va rog alegeti data de plecare!'))
    		}
    	});
		
		$('#getPackageForm').submit(function () {
			var passengers = $.trim($('#passengers').val());
    		var packageChosen = $('input[name=packageChosen]:checked').val();
    		var departureFlight = $('input[name=departureFlight]:checked').val();
    		var returnFlight = $('input[name=returnFlight]:checked').val();
    		var retur1 = $.trim($('#retur1').val());
    		if ($('input[name="retur"]:checked').length < 0) {
	    		return (!isEmpty('passengers','Va rog completati numarul de paageri!') &&
	    				isEmptyByValue(packageChosen,'Va rog alegeti unul din cele 3 pachete!') && 
	    				isEmptyByValue(departureFlight, 'Selectati zborul tur!' ))
    		} else{
    			return (!isEmpty('passengers','Va rog completati numarul de paageri!') &&
	    				isEmptyByValue(packageChosen,'Va rog alegeti unul din cele 3 pachete!') && 
	    				isEmptyByValue(departureFlight, 'Selectati zborul tur!' ) &&
	    				isEmptyByValue(returnFlight, 'Selectati zborul retur!' ))
    		}
		});
		
	})
</script> 


</body>
</html>
