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
<title>Euro Travel - Seat</title>

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





<!-- REPREZENTARE LOCURI AVION -->

<section class="tour section-wrapper container">
	<div class="row">
		<c:if test="${flight.packageChosen eq 2}">
			<div  class="close" data-dismiss="alert" aria-label="close">x</div>
			<div class="alert alert-info"><strong>Plus Package</strong> - Please select your seats</div>
		</c:if>
		<c:if test="${flight.packageChosen eq 3}">
			<div  class="close" data-dismiss="alert" aria-label="close">×</div>
			<div class="alert alert-info"><<strong>Flexi Plus Package</strong> - Please select your seats</div>
		</c:if>
		<div class="col-md-6">
			<form:form action="reserve" modelAttribute="rezervare" id="rezervareForm">
				<c:forEach items="${mapAvioane}" var="avion">
					<c:set var="keyVar" value="${fn:split(avion.key,';')}" />
					<c:set var="idZbor" value="${keyVar[0]}" />
					<c:set var="randuri" value="${keyVar[1]}" />
					<c:set var="coloane" value="${keyVar[2]}" />
					<c:set var="mijloc" value="${ coloane / 2 }"/>
					<c:set var="cursa" value="${routMap[idZbor]}" />
					<h4><c:out value="${cursa}"/> route</h4>
					<table class="table locuri">
						<thead>
							<tr><!-- cap de coloana - A, B, C ... -->
								<c:forEach var = "i" begin = "1" end = "${coloane}">
									<c:if test="${(i-1) eq mijloc}"><th>&nbsp;</th></c:if>
							         <c:choose>
							         	<c:when test="${i eq 1}"><th>A</th></c:when>
							         	<c:when test="${i eq 2}"><th>B</th></c:when>
							         	<c:when test="${i eq 3}"><th>C</th></c:when>
							         	<c:when test="${i eq 4}"><th>D</th></c:when>
							         	<c:when test="${i eq 5}"><th>E</th></c:when>
							         	<c:when test="${i eq 6}"><th>F</th></c:when>
							         	<c:when test="${i eq 7}"><th>G</th></c:when>
							         	<c:when test="${i eq 8}"><th>H</th></c:when>
							         	<c:when test="${i eq 9}"><th>I</th></c:when>
							         	<c:when test="${i eq 10}"><th>J</th></c:when>
							         	<c:when test="${i eq 11}"><th>K</th></c:when>
							         	<c:when test="${i eq 12}"><th>L</th></c:when>
							         	<c:when test="${i eq 13}"><th>M</th></c:when>
							         	<c:when test="${i eq 14}"><th>N</th></c:when>
							         	<c:when test="${i eq 15}"><th>O</th></c:when>
							         	<c:when test="${i eq 16}"><th>P</th></c:when>
							         	<c:when test="${i eq 17}"><th>Q</th></c:when>
							         	<c:when test="${i eq 18}"><th>R</th></c:when>
							         	<c:when test="${i eq 19}"><th>S</th></c:when>
							         	<c:when test="${i eq 20}"><th>T</th></c:when>
							         </c:choose>
							    </c:forEach>
							</tr>
						</thead>
						<tbody>
							<c:forEach var = "i" begin = "0" end = "${randuri-1}"> 
							<c:set var="ind" value="0"/>
								<tr>
									<c:forEach var = "j" begin = "0" end = "${coloane-1}">
									<c:set var="ind" value="${ind + 1}"/>
										<c:if test="${(ind-1) eq mijloc}"><th><c:out value="${i+1}"/></th></c:if>
												<c:choose>
													<c:when test="${avion.value[i][j] eq 0}">
														<td></td>
													</c:when>
													<c:when test="${avion.value[i][j] eq 1}">
													<td>
														<div data-toggle="buttons">
															<label class="btn btn-success">
															<c:if test="${flight.packageChosen eq 2}">
																<c:set var="pretLocScump" value='170'/>
															</c:if>
															<c:if test="${flight.packageChosen eq 3}">
																<c:set var="pretLocScump" value='185'/>
															</c:if>
																<form:checkbox path="locuri" autocomplete="off" value="${idZbor}-${i+1}-${j}-${pretLocScump}" />
																<span class="glyphicon glyphicon-ok"></span>
															</label>
														</div>
													</td>
													</c:when>
													<c:when test="${avion.value[i][j] eq 2}">
														<td>
															<div data-toggle="buttons">
																<c:if test="${flight.packageChosen eq 2}">
																	<c:set var="pretLocOk" value='165'/>
																</c:if>
																<c:if test="${flight.packageChosen eq 3}">
																	<c:set var="pretLocOk" value='185'/>
																</c:if>
																<label class="btn btn-warning">
																	<form:checkbox path="locuri" autocomplete="off" value="${idZbor}-${i+1}-${j}-${pretLocOk}" />
																	<span class="glyphicon glyphicon-ok"></span>
																</label>
															</div>
														</td>
													</c:when>
													<c:when test="${avion.value[i][j] eq 3}">
														<td>
															<div data-toggle="buttons">
																<label class="btn btn-info">
																	<c:if test="${flight.packageChosen eq 2}">
																		<c:set var="pretLoc" value='160'/>
																	</c:if>
																	<c:if test="${flight.packageChosen eq 3}">
																		<c:set var="pretLoc" value='185'/>
																	</c:if>
																	<form:checkbox path="locuri" autocomplete="off" value="${idZbor}-${i+1}-${j}-${pretLoc}" />
																	<span class="glyphicon glyphicon-ok"></span>
																</label>
															</div>
														</td>
													</c:when>
													<c:when test="${avion.value[i][j] eq 4}">
														<td>
															<div data-toggle="buttons">
																<label class="btn btn-default">
																	<span class="glyphicon glyphicon-remove"></span>
																</label>
															</div>
														</td>
													</c:when>
												</c:choose>
									</c:forEach> 
								</tr>    
							</c:forEach>
						</tbody>
					</table>
				</c:forEach>
				<hr />
				<div class="col-md-4 col-md-offset-4">
					<input type="button" class="btn btn-primary border-radius custom-button" id="reserveSpace" value="Continue"/>
				</div>
			</form:form>
		</div>
		<div class="col-md-6">
			<h3>Seat Information</h3>
			<ul class="list-group">
			  	<li class="list-group-item">
			  		<label class="btn btn-success">
						<span class="glyphicon glyphicon-ok"></span>
						</label>
						&nbsp;&nbsp;&nbsp; Extra legroom seat - 
					<c:if test="${flight.packageChosen eq 2}">
						170&euro;
					</c:if>
					<c:if test="${flight.packageChosen eq 3}">
						185&euro;
					</c:if>
				</li>
			  	<li class="list-group-item">
			  		<label class="btn btn-warning">
						<span class="glyphicon glyphicon-ok"></span>
					</label>
			  		&nbsp;&nbsp;&nbsp; Front seat - 
			  		<c:if test="${flight.packageChosen eq 2}">
						165&euro;
					</c:if>
					<c:if test="${flight.packageChosen eq 3}">
						185&euro;
					</c:if>
			  	</li>
			  	<li class="list-group-item">
			  		<label class="btn btn-info">
						<span class="glyphicon glyphicon-ok"></span>
					</label>
					&nbsp;&nbsp;&nbsp; Standard seat - 
					<c:if test="${flight.packageChosen eq 2}">
						160&euro;
					</c:if>
					<c:if test="${flight.packageChosen eq 3}">
						185&euro;
					</c:if>
				</li>
			  	<li class="list-group-item">
			  		<label class="btn btn-default">
						<span class="glyphicon glyphicon-remove"></span>
					</label>
					&nbsp;&nbsp;&nbsp; Reserved
				</li>
			  	<li class="list-group-item">
						&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-ok"></span>
			  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Selected seat
			  	</li>
			</ul>
		</div>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#reserveSpace').click(function(){
				var numberOfChecked = $('input:checkbox:checked').length;
				var nrPasageri= ${flight.passengers};
				if(numberOfChecked != nrPasageri ) {
					alert("Please select "+nrPasageri+" seat(s)!");
				} else {
					$('#rezervareForm').submit();
				}
			});
		});
	</script>
</body>
</html>