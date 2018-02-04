<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/rezervareBilete.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/rezervareLocuri.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <title>Rezervare Bilete - Locuri</title>
    
  </head>
<body>

<!-- REPREZENTARE LOCURI AVION -->
<div class="container">
	<hr /> 
	<div class="row">
		<h4>REPREZENTARE LOCURI AVION</h4>
		<h3></h3>
		<c:if test="${flight.packageChosen eq 2}">
			<div  class="close" data-dismiss="alert" aria-label="close">×</div>
			<div class="alert alert-info"><strong>Pachet Plus!</strong>Selectati locurile pasagerilor!</div>
		</c:if>
		<c:if test="${flight.packageChosen eq 3}">
			<div  class="close" data-dismiss="alert" aria-label="close">×</div>
			<div class="alert alert-info"><strong>Flexi Plus!</strong>Selectati locurile pasagerilor!</div>
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
					<h4>Alegeti locurile pentru ruta: <c:out value="${cursa}"/></h4>
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
										<c:if test="${(ind-1) eq mijloc}"><td><c:out value="${i+1}"/></td></c:if>
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
				<input type="button" id="reserveSpace" value="Continua rezervarea!"/>
			</form:form>
		</div>
		<div class="col-md-6">
			<h3>Seat Information</h3>
			<ul class="list-group">
			  	<li class="list-group-item">
			  		<label class="btn btn-success">
						<span class="glyphicon glyphicon-ok"></span>
					</label>
					Extra leg room seats - 
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
			  		Fron seats - 
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
					Standard -
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
					Reserved
				</li>
			  	<li class="list-group-item">
				  	
						<span class="glyphicon glyphicon-ok"></span>
					
			  		Selected seat
			  	</li>
			</ul>
		</div>
	</div>
</div>

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
					alert("Va rugam sa selectati locuri pentru fiecare pasager! ("+nrPasageri+")");
				} else {
					$('#rezervareForm').submit();
				}
			});
		});
	</script>
</body>
</html>