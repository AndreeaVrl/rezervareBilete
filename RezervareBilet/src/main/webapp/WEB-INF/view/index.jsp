<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <!-- Datepicker -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <title>Rezervare Bilete</title>
  </head>
  <body>
    
    <a class="btn btn-link" href="goToAdminPage">admin</a><br />
<!-- Selectare aeroport plecare sosire -->
<section>
	<div class="row">
		<c:if test="${not empty succes}">
				<div class="close" data-dismiss="alert" aria-label="close">×</div>
				<div class="alert alert-success"><strong>Succes!</strong> ${succes}</div>
			<br>
		</c:if>
		<c:if test="${not empty exceptie}">
				<div  class="close" data-dismiss="alert" aria-label="close">×</div>
				<div class="alert alert-danger"><strong>Eroare!</strong>${exceptie}</div>
			<br>
		</c:if>
		<div class="col-md-6">
    	<form:form class="form-inline" id="getRouteForm" method="POST" modelAttribute="cursa">
      	<div class="form-group">
        	From
        </div>
        <br />
        <div class="form-group">
          <label for="country">Country</label>
          <form:select class="form-control" path="contryFrom">
            <form:option value="">United States</form:option>
            <form:option value="">United Kingdom</form:option>
            <form:option value="">France</form:option>
            <form:option value="">Germany</form:option>
            <form:option value="">Romania</form:option>
          </form:select>
        </div>
        <div class="form-group">
          <label for="country">Airport</label>
          <form:select class="form-control" path="airportFrom">
            <form:option value="1">Linz</form:option>
            <form:option value="">Miami</form:option>
            <form:option value="">London</form:option>
            <form:option value="">Bremen</form:option>
            <form:option value="">Romania</form:option>
          </form:select>
        </div>
      	<br />
        <div class="form-group">
        	To
        </div>
        <br />
        <div class="form-group">
          <label for="country">Country</label>
          <form:select class="form-control" path="countryTo">
            <form:option value="">United States</form:option>
            <form:option value="">United Kingdom</form:option>
            <form:option value="">France</form:option>
            <form:option value="">Germany</form:option>
            <form:option value="">Romania</form:option>
          </form:select>
        </div>
        <div class="form-group">
          <label for="country">Airport</label>
          <form:select class="form-control" path="airportTo">
            <form:option value="23" >Lille</form:option>
            <form:option value="">Miami</form:option>
            <form:option value="">London</form:option>
            <form:option value="">Bremen</form:option>
            <form:option value="">Romania</form:option>
          </form:select>
         </div>
         <div class="form-group">
			<label for="date">Fly out date</label>
			<form:input class="form-control" path="departureDate" placeholder="DD/MM/YYYY" type="text"/>
		 </div>
         <br />
         <div class="form-group" id="flyBackDate">
			<label for="date">Fly back date</label>
			<form:input class="form-control" path="flyBack"  placeholder="DD/MM/YYYY" type="text"/>
		 </div>
         <div class="form-group" >
         	<label for="retur"> </label>
			<form:checkbox path="retur"/>Retur <br>
		 </div>
        	<input class="btn btn-primary" name="submit" id="submit" type="submit" value="Afiseaza zborul optim"/>
			<input class="btn btn-primary" name="optim" id="optim" type="submit" value="Afiseaza toate zborurile"/>
        </form:form>
		</div>
	</div>
</section>
<c:if test="${(not empty zboruriCautare or not empty zboruriCautareRetur) and flag eq 0}">
<h4>Zboruri recomandate</h4>
</c:if>

<c:if test="${(not empty zboruriCautare or not empty zboruriCautareRetur) and flag eq 1}">
<h4>Zboruri</h4>
</c:if>

<c:choose>
	<c:when test="${not empty zboruriCautare}">
		<hr />
	<h4>Zboruri tur</h4>	
		<!-- afisare zboruri  -->
		<section>
			<div id="cautare">
				<table>
					<c:forEach items="${zboruriCautare}" var="zbor">
						<c:set var="first" value="1"/>
						<tr>
						<td>
							<input type="radio" id="departureFlight" name="departureFlight" value="${zbor.key}" />
						</td>
						<c:forEach items="${zbor.value}" var="zboruriCautare" >
							<td>
								<c:choose>
									<c:when test="${first eq 1}">
										${zboruriCautare[0].cursa.aeroport_1.denumire}-${zboruriCautare[0].cursa.aeroport_2.denumire}
										<select id="${zboruriCautare[0].cursa.id}-${zbor.key}">
											<c:forEach items="${zboruriCautare}" var="zborCautare">
												<option value="${zborCautare.id}"/>
												${zborCautare.dataPlecare} - ${zborCautare.dataSosire},
												${zborCautare.companie.denumire}, ${zborCautare.pret}€ 
											</c:forEach>
										</select>
									</c:when>
									<c:otherwise>
										-${zboruriCautare[0].cursa.aeroport_2.denumire}
										<select id="${zboruriCautare[0].cursa.id}-${zbor.key}">
											<c:forEach items="${zboruriCautare}" var="zborCautare">
												<option value="${zborCautare.id}"/>
												${zborCautare.dataPlecare} - ${zborCautare.dataSosire}
												${zborCautare.companie.denumire}, ${zborCautare.pret}€ 
											</c:forEach>
										</select>
									</c:otherwise>
								</c:choose>
							</td>
						</c:forEach>
						</tr>
					</c:forEach>
				</table>
			</div>
		</section>
	</c:when>
	<c:when test="${empty zboruriCautare and flag eq 0 }">
		<div  class="close" data-dismiss="alert" aria-label="close">×</div><c:out value="${flag}"/>
		<div class="alert alert-info"><strong>Atentie</strong>Ne pare rau, nu a fost gasit nicun zbor pentru datele specificate!</div>
	</c:when>
	<c:when test="${empty zboruriCautare and flag eq 1 }">
		<div  class="close" data-dismiss="alert" aria-label="close">×</div>
		<div class="alert alert-info"><strong>Atentie!</strong>Ne pare rau, nu a fost gasit nicun zbor pentru datele specificate!</div>
	</c:when>
</c:choose>

<c:if test="${not empty cursaRequestView && cursaRequestView.retur}">
	<hr />
	<c:choose>
		<c:when test="${empty zboruriCautareRetur}">
			<div  class="close" data-dismiss="alert" aria-label="close">×</div>
			<div class="alert alert-info"><strong>Atentie!</strong>Nu au fost gasite zboruri de retur!</div>
		<br>
		</c:when>
		<c:when test="${not empty zboruriCautareRetur}">
			<!-- afisare zboruri  -->
			<section>
			<h4>Zboruri retur</h4>
				<div id="cautare">
					<table>
						<c:forEach items="${zboruriCautareRetur}" var="zbor">
							<c:set var="first" value="1"/>
							<tr>
							<td>
								<input type="radio" id="returnFlight" name="returnFlight" value="${zbor.key}" />
							</td>
							<c:forEach items="${zbor.value}" var="zboruriCautare" >
								<td>
									<c:choose>
										<c:when test="${first eq 1}">
											${zboruriCautare[0].cursa.aeroport_1.denumire}-${zboruriCautare[0].cursa.aeroport_2.denumire}
											<select id="-${zboruriCautare[0].cursa.id}-${zbor.key}">
												<c:forEach items="${zboruriCautare}" var="zborCautare">
													<option value="${zborCautare.id}"/>
													 ${zborCautare.dataPlecare} - ${zborCautare.dataSosire},
													 ${zborCautare.companie.denumire},${zborCautare.pret}€ <br>
												</c:forEach>
											</select>
										</c:when>
										<c:otherwise>
											-${zboruriCautare[0].cursa.aeroport_2.denumire}
											<select id="-${zboruriCautare[0].cursa.id}-${zbor.key}">
												<c:forEach items="${zboruriCautare}" var="zborCautare">
													<option value="${zborCautare.id}"/>
													 ${zborCautare.dataPlecare} - ${zborCautare.dataSosire} <br><br>
													${zborCautare.companie.denumire}, ${zborCautare.pret}€ <br>
												</c:forEach>
											</select>
										</c:otherwise>
									</c:choose>
								</td>
							</c:forEach>
							</tr>
						</c:forEach>
					</table>
				</div>
			</section>
		</c:when>
	</c:choose>
</c:if>
<div id="divPasageri">
<hr />
<!-- Selectare nr pasageri -->
	<section>
		<div class="row">
	    	<form:form class="form-inline" id="getPackageForm" action="" method="post" modelAttribute="flightChosen">
	        <div class="form-group">
	        	<div class="col-md-1">
	         		 <label for="passengers">Passengers</label>
	         	</div>
	         	<div class="col-md-2">
	          		<form:input path="passengers" class="form-control"/>
		      	</div>
		      </div>
		        <br />
		        <form:hidden path="departureCompannies"/>
				<form:hidden path="returnCompannies"/>
		        <form:hidden path="departurFlight"/>
				<form:hidden path="returFlight"/>
				<div class="row">
					<div class="col-md-6">
			    	<div class="col-md-4">
			        <div class="panel panel-info">
			          <div class="panel-heading">
			            <h3 class="panel-title text-center">Standard</h3>
			          </div>
			          <div class="panel-body">
			            <ul class="pachet-descriere">
			            	<li>Lowest Fare</li>
			              <li class="disabled">60 day check-in</li>
			              <li class="disabled">20kg check-in bag</li>
							<li class="disabled">Priority Boarding</li>
							<li class="disabled">Reserved standard seat</li>
							<li class="disabled">Flexible tickets</li>
							<li class="disabled">Optional airport check-in</li>
							<li class="disabled">Fast Track</li>
			            </ul>
			          </div>
			          <div class="panel-footer text-center">
			            <form:radiobutton path="packageChosen" value="1" /> &euro; 120 
			          </div>
			        </div>
			      </div>
			      <div class="col-md-4">
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
			            <form:radiobutton path="packageChosen" value="2" /> &euro; 160 
			          </div>
			        </div>
			      </div>
			      <div class="col-md-4">
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
			            <form:radiobutton path="packageChosen"  value="3" /> &euro; 185 
			          </div>
			        </div>
			      </div>
			    </div>
			</div>
	        <button class="btn btn-primary" name="getPackage" id="getPackage" type="submit">Continua rezervarea!</button>
	      </form:form>
		</div>
	</section>
<!-- END Selectare nr pasageri -->
<hr />
</div>

<section>
	<div>
		<a class="btn btn-link" href="goToLoginPage"><input type="button" value="LogIn"/></a>
		<a class="btn btn-link" href="goToCreateNewAccount"><input type="button" value="CreateNewAccount"/></a>
		
	</div>
</section>
<!-- END Selectare nr pasageri -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

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
		});
		$('#flyBack').datepicker({
			format: 'dd/mm/yyyy',
			container: container,
			todayHighlight: true,
			autoclose: true,
		});
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
			console.log("AICIII");
			var zborAles = $('input[name=departureFlight]:checked').val();
			$('#departurFlight').val(zborAles);
			console.log("departurFlight"+$('#departurFlight').val());
			<c:forEach items="${zboruriCautare}" var="zbor">
				var mapKey = ${zbor.key};
				if( zborAles == mapKey) {
					<c:set var="flag" value="1"/>
					<c:forEach items="${zbor.value}" var="zboruriCautare"  >
					var idCursaSelect = ${zboruriCautare[0].cursa.id}+"-"+mapKey;
						<c:choose>
							<c:when test="${flag eq 1}">
								var value = $("#"+idCursaSelect).val();
								console.log("value="+value);
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
				console.log("departureCompannies=["+$('#departureCompannies').val()+"]");
			</c:forEach>
			var zborA = $('input[name=returnFlight]:checked').val();
			$('#returFlight').val(zborA);
			console.log("zborA"+$('#returFlight').val());
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
						console.log("returnCompannies"+$('#returnCompannies').val());
					}
				</c:forEach>
			}
			$('#getPackageForm').attr('action', 'getChosenFlight');
		});	
		
	})
</script> 
    
        
  </body>
</html>
