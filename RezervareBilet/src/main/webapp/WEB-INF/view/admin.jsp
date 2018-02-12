<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" />
    

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <title>Rezervare Bilete - Admin</title>
  </head>
  <body>
    
	<!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="goToAdminPage">AdminPage</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav"> 
            <li class="active"><a href="#">Airports</a></li>
            <li><a href="#">Companies</a></li>
            <li><a href="#">Airliners</a></li>
            <li><a href="#">Flights</a></li>
            <li><a>|</a></li>        
            <li><a href="#">Clients</a></li>
            <li><a href="#">Tickets</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
          	<li><a href="#">Users</a></li>
            <li><a href="logout">Logout<span class="sr-only">(current)</span></a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

<script>


function editFlight(flightId) {
	<c:forEach items="${flightsList}" var="flight">
		var id = ${flight.id};
		if(flightId == id){
			document.getElementById("editFlightId").value = id;
			$("#editFlightRoutes").val(${flight.cursa.id});
			$("#editFlightCompanies").val(${flight.companie.id});
			$("#editFlightAirplanes").val(${flight.avion.id});
			
			
			$('#editDatetimepicker4').datetimepicker({});
			$('#editDatetimepicker4').data("DateTimePicker").date(moment(new Date ).format('DD/MM/YYYY HH:mm'));
			$('#editDatetimepicker5').datetimepicker({});
			$('#editDatetimepicker5').data("DateTimePicker").date(moment(new Date ).format('DD/MM/YYYY HH:mm'));
			
			var pret = ${flight.pret};
			document.getElementById("editStandardPrice").value = pret;
		}
	</c:forEach>
	
	$('#myEditFlightModal').modal('show');
}
</script>
<!-- MESAJE 
<div class="container">
	<div class="row">
		<div class="alert alert-warning alert-dismissible" role="alert">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  <strong>Warning!</strong> Better check yourself, you're not looking too good.
		</div>
	</div>
</div>
-->
<!-- MENIU AIRPORTS -->
<div class="container">
	<div class="row">
		<h4>MENIU AIRPORTS</h4>
		<div class="col-md-3">
			<div class="panel panel-info">
        <div class="panel-heading">
          <h3 class="panel-title text-center">Countries</h3>
        </div>
        <form:form class="form-inline" action="" id="countriesForm" modelAttribute="tara" method="post">
	        <div class="panel-body">
	        	<div class="form-group">
			        <form:select class="form-control" id="countries-sel" size="20" path="denumire"> <!-- trage date din tabela tari -->
			        	<c:forEach items="${countriesList}" var="country">
			        		<form:option value="${country.id}">${country.denumire}</form:option>
			        	</c:forEach>
			        </form:select>
			      </div>
	        </div>
	        <div class="panel-footer text-center">
	        	<button class="btn btn-primary disabled" name="submit_edit" type="button" data-toggle="modal" data-target="#myEditCountryModal" id="editCountryModal"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_add" type="button" data-toggle="modal" data-target="#myAddCountryModal" id="addCountryModal"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_remove" type="button" id="deleteCountry"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
	        </div>
        </form:form>
      </div>		
		</div>
		
		<div class="col-md-3">
			<div class="panel panel-info">
        <div class="panel-heading">
          <h3 class="panel-title text-center">Airports</h3>
        </div>
        <form:form class="form-inline" action="" model="aeroport" id="airportsForm" method="post" commandName="aeroport">
	        <div class="panel-body">
	        	<div class="form-group">
			        <form:select class="form-control" id="airports-sel" size="20" path="denumire">
			        	<c:forEach items="${airportsList}" var="airport">
			        		<form:option data-group="${airport.tara.id}" value="${airport.id}">${airport.denumire}</form:option>
			        	</c:forEach>
			        </form:select>
			      </div>
	        </div>
	        <div class="panel-footer text-center">
	        	<button class="btn btn-primary" name="submit_edit" type="button" data-toggle="modal" data-target="#myEditAirportModal" id="editAirportModal"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_add" type="button" data-toggle="modal" data-target="#myAddAirportModal" id="addAirportModal"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_remove" type="button" id="deleteAirportModal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
	        </div>
         </form:form>
      </div>		
		</div>
		
		<div class="col-md-6">
			<div class="panel panel-info">
        <div class="panel-heading">
          <h3 class="panel-title text-center">Route To</h3>
        </div>
        <form:form class="form-inline" action="" model="aeroport" id="routeForm" method="post" commandName="aeroport">
	        <div class="panel-body">
	        	<div class="col-md-6">
		        	<div class="form-group">
				        <form:select class="form-control" id="routes-sel" size="20" path="denumire">
				        	<c:forEach items="${airportsList}" var="airport">
				        		<c:forEach items="${airport.curseAeroport_1}" var="curse">
				        			<form:option data-group="${airport.id}" value="${curse.id}">${curse.aeroport_2.denumire}</form:option>
				        		</c:forEach>
				        	</c:forEach>
				        </form:select>
				      </div>
				    </div>
				    <div class="col-md-6" id="route_info"> <!-- se actualizeaza functie de ruta aleasa -->
				    	<h4>Informations</h4>
						<p id="routeDistanceInformations"></p>
				    </div>
	        </div>
	        <div class="panel-footer text-center">
	        	<button class="btn btn-primary" name="submit_edit" type="button" data-toggle="modal" data-target="#myEditRouteModal" id="editRouteModal"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_add" type="button" data-toggle="modal" data-target="#myAddRouteModal" id="addRouteModal"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_remove" type="button" id="deleteRouteModal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
	        </div>
        </form:form>
      </div>		
		</div>
		
  </div>
</div>

<!-- MENIU COMPANIES -->
<%--
<div class="container">
	<hr /> 
	<div class="row">
		<h4>MENIU COMPANIES</h4>	
		<div class="col-md-3">
			<div class="panel panel-info">
        <div class="panel-heading">
          <h3 class="panel-title text-center">Companies</h3>
        </div>
        <form:form class="form-inline" action="" model="aeroport" id="airportsForm" method="post" commandName="aeroport">
	        <div class="panel-body">
	        	<div class="form-group">
		        	<form:select class="form-control" id="companies-sel" size="20" path="denumire">
				        <c:forEach items="${companiesList}" var="company">
					        <form:option value="${company.id}">${company.denumire}</form:option>
					    </c:forEach>
					</form:select>
			     </div>
	        </div>
	        <div class="panel-footer text-center">
	        	<button class="btn btn-primary" name="submit_edit" type="submit"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_add" type="submit"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_remove" type="submit"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
	        </div>
        </form:form>
      </div>		
		</div>
		
		<div class="col-md-6">
			<div class="panel panel-info">
        <div class="panel-heading">
          <h3 class="panel-title text-center">Packages</h3>
        </div>
         <form:form class="form-inline" action="" model="aeroport" id="airportsForm" method="post" commandName="aeroport">
	        <div class="panel-body">
	        	<div class="col-md-6">
		        	<div class="form-group">
				    <form:select class="form-control" id="packages-sel" size="20" path="denumire">
				        <c:forEach items="${companiesList}" var="company">
				        	<c:forEach items="${company.pachete}" var="packages">	
					        	<form:option data-group="${company.id}" value="${packages.id}">${packages.denumire}</form:option>
					        </c:forEach>
					    </c:forEach>
					</form:select>
				      </div>
				    </div>
				    <div class="col-md-6" id="route_info"> <!-- se actualizeaza functie de pachetul ales -->
				    	<h4>Informations</h4>
						<p id="infoPackageTax"></p>
						<p id="infoPackageDescription"></p>
				    </div>
	        </div>
	        <div class="panel-footer text-center">
	        	<button class="btn btn-primary" name="submit_edit" type="submit"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_add" type="submit"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_remove" type="submit"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
	        </div>
        </form:form>
      </div>		
		</div>

	</div>
</div>

  	
<!-- MENIU AIRLINERS -->
<div class="container">
	<hr /> 
	<div class="row">
		<h4>MENIU AIRLINERS</h4>	
		<div class="col-md-3">
			<div class="panel panel-info">
        <div class="panel-heading">
          <h3 class="panel-title text-center">Airliner Types</h3>
        </div>
        <form class="form-inline" action="" method="post">
	        <div class="panel-body">
	        	<div class="form-group">
			        <select class="form-control" id="airlinertype-sel" size="20"> <!-- trage date din tabela tipuri_avioane -->
			          <option>Airbus 380</option>
			          <option>Airbus 340</option>
			          <option>Boeing 747</option>
			          <option>Boeing 777</option>
			        </select>
			      </div>
	        </div>
	        <div class="panel-footer text-center">
	        	<button class="btn btn-primary" name="submit_edit" type="submit"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_add" type="submit"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_remove" type="submit"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
	        </div>
        </form>
      </div>		
		</div>
		
		<div class="col-md-6">
			<div class="panel panel-info">
        <div class="panel-heading">
          <h3 class="panel-title text-center">Airliners</h3>
        </div>
        <form class="form-inline" action="" method="post">
	        <div class="panel-body">
	        	<div class="col-md-6">
		        	<div class="form-group">
				        <select class="form-control" id="airliner-sel" size="20"> <!-- trage date din tabela avioane functie de tip avion -->
			          <option>N930NN</option>
			          <option>N988NA</option>
			          <option>XA-NAK</option>
			          <option>XA-LUS</option>
			          <option>XB-NOR</option>
			        </select>
				      </div>
				    </div>
				    <div class="col-md-6" id="airliner_info"> <!-- se actualizeaza functie de avionul ales -->
				    	<h4>Informations</h4>
				    	Airliner type:<br />
				    	Number:<br />
				    	Crew:<br />
				    	Bussiness class seats:<br />
				    	Economy class seats:<br />
				    	Facilities:<br />
				    	...
				    </div>
	        </div>
	        <div class="panel-footer text-center">
	        	<button class="btn btn-primary" name="submit_edit" type="submit"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_add" type="submit"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_remove" type="submit"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
	        </div>
        </form>
      </div>		
		</div>
		
  </div>
</div>   
--%>
<!-- MENIU FLIGHTS -->
<div class="container">
	<hr /> 
	<div class="row">
		<h4>MENIU FLIGHTS</h4>	
		<div class="col-md-12">
			<div class="panel panel-info">
        <div class="panel-heading">
          <h3 class="panel-title text-center">Flights</h3>
        </div>
        <form:form class="form-inline" action="" model="aeroport" id="flightsForm" method="post" commandName="aeroport">
	        <div class="panel-body">
	        	<div class="table-responsive">
						  <table class="table table-striped table-hover">
						  	<thead>
									<tr>
										<th>#</th>
										<th>Departure</th>
										<th>Arrival</th>
										<th>Departure Time</th>
										<th>Arrival Time</th>
										<th>Company</th>
										<th>Airliner</th>
										<th>Standard Price</th>
										<th></th>
									</tr>
								</thead>
						    <tbody>
						    	<c:forEach items="${flightsList}" var="flight">
						    		<tr>
						    			<td>${flight.id}</td>
						    			<td>${flight.cursa.aeroport_1.tara.denumire} - ${flight.cursa.aeroport_1.denumire}</td>
						    			<td>${flight.cursa.aeroport_2.tara.denumire} - ${flight.cursa.aeroport_2.denumire}</td>
						    			<td><fmt:formatDate pattern = "dd/MM/yyyy - HH:mm" value = "${flight.dataPlecare}"/></td>
						    			<td><fmt:formatDate pattern = "dd/MM/yyyy - HH:mm" value = "${flight.dataSosire}"/></td>
						    			<td>${flight.companie.denumire}</td>
						    			<td>${flight.avion.tipAvion.denumire} - ${flight.avion.id}</td>
						    			<td>${flight.pret}</td>
						    			<td><button class="btn btn-primary" name="submit_edit" data-toggle="modal" data-target="myEditFlightModal" type="button" onClick="editFlight(${flight.id})"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button></td>
						    		</tr>
						    	</c:forEach>
						    </tbody>
						  </table>
						</div>

	        </div>
	        <div class="panel-footer text-center">
	        	<%--<button class="btn btn-primary" name="submit_edit" type="submit"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>--%>
	        	<button class="btn btn-primary" name="submit_add" data-toggle="modal" data-target="#myAddFlightModal" type="button"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
	        	<%-- <button class="btn btn-primary" name="submit_remove" type="submit"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>--%>
	        </div>
        </form:form>
      </div>		
		</div>		
  </div>
</div>    
  
   

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>  
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
<script>
$("#editCountryModal").attr("disabled", true);
$("#deleteCountry").attr("disabled", true);
$("#editAirportModal").attr("disabled", true);
$("#addAirportModal").attr("disabled", true);
$("#deleteAirportModal").attr("disabled", true);
$("#addRouteModal").attr("disabled", true);
$("#editRouteModal").attr("disabled", true);
$("#deleteRouteModal").attr("disabled", true);



function sortAirports(){
	$("#airports-sel").append($("#airports-sel option").remove().sort(function(a, b) {
	    var at = $(a).text(), bt = $(b).text();
	    return (at > bt)?1:((at < bt)?-1:0);
	}));
}

function sortRoutes(){
	$("#routes-sel").append($("#routes-sel option").remove().sort(function(a, b) {
	    var at = $(a).text(), bt = $(b).text();
	    return (at > bt)?1:((at < bt)?-1:0);
	}));
}

function sortCountries(){
	$("#countries-sel").append($("#countries-sel option").remove().sort(function(a, b) {
	    var at = $(a).text(), bt = $(b).text();
	    return (at > bt)?1:((at < bt)?-1:0);
	}));
}

function sortCompanies(){
	$("#companies-sel").append($("#companies-sel option").remove().sort(function(a, b) {
	    var at = $(a).text(), bt = $(b).text();
	    return (at > bt)?1:((at < bt)?-1:0);
	}));
}

function sortPackages(){
	$("#packages-sel").append($("#packages-sel option").remove().sort(function(a, b) {
	    var at = $(a).text(), bt = $(b).text();
	    return (at > bt)?1:((at < bt)?-1:0);
	}));
}


$(function(){
    $('#countries-sel').on('change', function(){
        var val = $(this).val();
        var sub = $('#airports-sel');
        $('option', sub).filter(function(){
            if (
                 $(this).attr('data-group') === val 
              || $(this).attr('data-group') === 'SHOW'
            ) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    });
    $('#countries-sel').trigger('change');
});

$(function(){
    $('#airports-sel').on('change', function(){
        var val = $(this).val();
        var sub = $('#routes-sel');
        $('option', sub).filter(function(){
            if (
                 $(this).attr('data-group') === val 
              || $(this).attr('data-group') === 'SHOW'
            ) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    });
    $('#airports-sel').trigger('change');
});

$(function(){
    $('#airports-sel').on('change', function(){
        var val = $(this).val();
        var sub = $('#routes-sel');
        $('option', sub).filter(function(){
            if (
                 $(this).attr('data-group') === val 
              || $(this).attr('data-group') === 'SHOW'
            ) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    });
    $('#airports-sel').trigger('change');
});

$(function(){
    $('#companies-sel').on('change', function(){
        var val = $(this).val();
        var sub = $('#packages-sel');
        $('option', sub).filter(function(){
            if (
                 $(this).attr('data-group') === val 
              || $(this).attr('data-group') === 'SHOW'
            ) {
                $(this).show();
            } else {
                $(this).hide();
            }
        });
    });
    $('#companies-sel').trigger('change');
});

function showAddCountryInput(){
	document.getElementById("editCountryInputId").style.display = "inline";
}

function submitNewCountry(){
	document.getElementById("countriesForm").action("addCountry");
	document.getElementById("countriesForm").submit();
	
}

$(document).ready(function(){
	$("#toAirport").append($("#toAirport option").remove().sort(function(a, b) {
	    var at = $(a).text(), bt = $(b).text();
	    return (at > bt)?1:((at < bt)?-1:0);
	}));
	$("#toAirport").val($("#toAirport option:first").val());
	
	$('#saveAddCountryChanges').click(function() {
		var newCountryName = $('#addCountryInputDenumire').val();
		var newCountryId;
		$.ajax({
			type: 'POST',
			url: '${pageContext.request.contextPath}/addCountry',
			data: {denumire: newCountryName},
			dataType : 'json',
			success: function(data) {
				console.log("SUCCESS id: " + data.id);
				newCountryId=data.id;
				console.log("tara:" + newCountryId + "name:" + newCountryName);
				$('#countries-sel').append($('<option>', {
				    value: newCountryId,
				    text: newCountryName
				}));
				console.log("added country to select");
				
				sortCountries();
			}
		})

		
		$('#myAddCountryModal').modal('hide');
	})
	
	$('#deleteCountry').click(function() {
		var selectedCountryId = $( "#countries-sel option:selected" ).val();
		console.log("addCountryInputDenumire: " + selectedCountryId);
		$.ajax({
			type: 'POST',
			url: '${pageContext.request.contextPath}/deleteCountry',
			data: {id: selectedCountryId},
			dataType : 'json',
			success: function(data) {
				console.log("SUCCESS id: " + data.id);
				$( "#countries-sel option:selected" ).remove();
				$("#editCountryModal").attr("disabled", true);
				$("#deleteCountry").attr("disabled", true);
			}
		})
	})
	
	$('#editCountryModal').click(function(){
		console.log("in modal jq");
		var selectedCountryId = $( "#countries-sel option:selected" ).val();
		var selectedCountry = $( "#countries-sel option:selected" ).text();
		console.log(selectedCountryId);
		console.log(selectedCountry);
		$("#editCountryInputId").val(selectedCountryId);
		$("#editCountryInputDenumire").val(selectedCountry);
		sortCountries();
	})
	
	$("#countries-sel").change(function() {
		$("#editCountryModal").removeClass("disabled");
		$("#editCountryModal").attr("disabled", false);
		$("#deleteCountry").attr("disabled", true);
		$("#addAirportModal").attr("disabled", false);
		$("#editAirportModal").attr("disabled", true);
		$("#deleteAirportModal").attr("disabled", true);
		$("#addRouteModal").attr("disabled", true);
		$("#editRouteModal").attr("disabled", true);
		$("#deleteRouteModal").attr("disabled", true);
		$("#routeDistanceInformations").text("");
		document.getElementById("addAirportInputIdTara").value = $( "#countries-sel option:selected" ).val();
		document.getElementById("addAirportInputDenumireTara").value = $( "#countries-sel option:selected" ).text();
		document.getElementById("editAirportInputIdTara").value = $( "#countries-sel option:selected" ).val();
		document.getElementById("editAirportInputDenumireTara").value = $( "#countries-sel option:selected" ).text();
		

		$("#routes-sel > option").each(function() {
			 $(this).hide();
		});
		
		var found = 0;
		<c:forEach items="${countriesList}" var="country">
			var selectedCountryId = $("#countries-sel option:selected").val();
			var countryId = ${country.id};
			
			if (countryId == selectedCountryId) {
				var found = 1;
			      <c:choose>
			         <c:when test="${empty country.clienti && empty country.aeroporturi}">
			         	$("#deleteCountry").attr("disabled", false);
			         	console.log("both empty");
			         </c:when>
			         <c:otherwise>
			         	console.log("not empty");
			         </c:otherwise>
			      </c:choose>
			}
		</c:forEach>
		if(found != 1){
			$("#deleteCountry").attr("disabled", false);
		}
	})
	
	$("#routes-sel").change(function() {
		<%--$("#editRouteModal").attr("disabled", false);--%>
		$("#deleteRouteModal").attr("disabled", true);
		
		console.log("routes select change");
		var found = 0;
		
		<c:forEach items="${routesList}" var="route">
			var selectedRouteId = $("#routes-sel option:selected").val();
			var routeId = ${route.id};
			if (selectedRouteId == routeId) {
				$("#routeDistanceInformations").text("Distance: " + ${route.distanta});
				var found = 1;
				console.log("found route :" + routeId);
				<c:if test="${empty route.zboruri}">
					$("#deleteRouteModal").attr("disabled", false);
				</c:if>
			}
		</c:forEach>
		
		if(found != 1){
			$("#deleteAirportModal").attr("disabled", false);
		}
	})
	
	$("#airports-sel").change(function() {
		$("#editAirportModal").removeClass("disabled");
		$("#editAirportModal").attr("disabled", false);
		$("#deleteAirportModal").attr("disabled", true);
		$("#editRouteModal").attr("disabled", true);
		$("#deleteRouteModal").attr("disabled", true);
		$("#addRouteModal").attr("disabled", false);
		$("#routeDistanceInformations").text("");
		
		$("#fromAirport").val($("#airports-sel option:selected").text());
		console.log($("#airports-sel option:selected").val());
		$("#fromAirportId").val($("#airports-sel option:selected").val());
		
		var found = 0;
		<c:forEach items="${airportsList}" var="airport">
			var selectedAirportId = $("#airports-sel option:selected").val();
			var airportId = ${airport.id};
			
			if (selectedAirportId == airportId) {
				var found = 1;
				<c:if test="${empty airport.curseAeroport_1 && empty airport.curseAeroport_2}">
					$("#deleteAirportModal").attr("disabled", false);
				</c:if>
			}
		</c:forEach>
		if(found != 1){
			$("#deleteAirportModal").attr("disabled", false);
		}
		
	})
	
	$('#deleteAirportModal').click(function() {
		var selectedAirportId = $( "#airports-sel option:selected" ).val();
		console.log("selectedAirportId: " + selectedAirportId);
		$.ajax({
			type: 'POST',
			url: '${pageContext.request.contextPath}/deleteAirport',
			data: {id: selectedAirportId},
			dataType : 'json',
			success: function(data) {
				console.log("SUCCESS id: " + data.id);
				$("#airports-sel option:selected").remove();
				$("#editAirportModal").attr("disabled", true);
				$("#deleteAirportModal").attr("disabled", true);
			}
		})
	})
	
	$('#saveEditCountryChanges').click(function() {
		var countryIdChange = $('#editCountryInputId').val();
		var countryNameChange = $('#editCountryInputDenumire').val();
		
		$.ajax({
			type: 'POST',
			url: '${pageContext.request.contextPath}/editCountry',
			data: { id: countryIdChange,
				denumire: countryNameChange},
			dataType : 'json',
			success: function(data) {
				console.log("SUCCES");
				console.log(data);
			}
		})
		$( "#countries-sel option:selected" ).text(countryNameChange);
		$('#myEditCountryModal').modal('hide');
	})
	
	$('#saveAddAirportChanges').click(function() {
		var newAirportName = $('#addAirportInputDenumire').val();
		var newAirportId;
		$.ajax({
			type: 'POST',
			url: '${pageContext.request.contextPath}/addAirport',
			data: {denumire: newAirportName,
				idTara: $('#addAirportInputIdTara').val()},
			dataType : 'json',
			success: function(data) {
				newAirportId=data.id;
				$('#airports-sel').append($('<option>', {
				    value: newAirportId,
				    text: newAirportName,
				    "data-group": $('#addAirportInputIdTara').val()
				}));
				
				sortAirports();
			}
		})
		
		$('#myAddAirportModal').modal('hide');
	})
	
	$('#editAirportModal').click(function(){
		var selectedAirportId = $( "#airports-sel option:selected" ).val();
		var selectedAirport = $( "#airports-sel option:selected" ).text();
		$("#editedAirportId").val(selectedAirportId);
		$("#editAirportInputDenumire").val(selectedAirport);
	})
	
	$('#saveAirportChanges').click(function() {
		console.log("start - saveAirportChanges");
		var airportIdChange = $('#editedAirportId').val();
		var airportNameChange = $('#editAirportInputDenumire').val();
		var countryIdForEditedAirport = $('#editAirportInputIdTara').val();
		
		$.ajax({
			type: 'POST',
			url: '${pageContext.request.contextPath}/editAirport',
			data: { id: airportIdChange,
				denumire: airportNameChange,
				idTara: countryIdForEditedAirport},
			dataType : 'json',
			success: function(data) {
				console.log("SUCCES");
				console.log(data);
				$("#airports-sel option:selected").text(airportNameChange);
				$('#myEditAirportModal').modal('hide');
			}
		})
	})
	
	$('#saveNewRoute').click(function() {
		console.log("start - saveNewRoute");
		var fromAirport = $('#fromAirport').val();
		var toAirport = $('#toAirport').val();
		var distance = $('#distance').val();
		
		console.log(fromAirport);
		console.log(toAirport);
		console.log(distance);
		
		$.ajax({
			type: 'POST',
			url: '${pageContext.request.contextPath}/addRoute',
			data: $('form[name=routeForm]').serialize(),
			dataType : 'json',
			success: function(data) {
				console.log("SUCCES");
				console.log(data);
				
				$('#routes-sel').append($('<option>', {
				    value: data.idTara,
				    text: data.denumireTara,
				    "data-group": data.id
				}));
				
				$('#myAddRouteModal').modal('hide');
				sortRoutes();
			}
		})
	})
	
	
	$('#deleteRouteModal').click(function() {
		var selectedRouteId = $("#routes-sel option:selected").val();
		$.ajax({
			type: 'POST',
			url: '${pageContext.request.contextPath}/deleteRoute',
			data: {id: selectedRouteId},
			dataType : 'json',
			success: function(data) {
				console.log("SUCCESS id: " + data.id);
				$("#routes-sel option:selected").remove();
				$("#editRouteModal").attr("disabled", true);
				$("#deleteRouteModal").attr("disabled", true);
			}
		})
	})
	
	$("#routes-sel").change(function() {
		<%--$("#editRouteModal").attr("disabled", false);--%>
		$("#deleteRouteModal").attr("disabled", true);
		
		console.log("routes select change");
		var found = 0;
		
		<c:forEach items="${routesList}" var="route">
			var selectedRouteId = $("#routes-sel option:selected").val();
			var routeId = ${route.id};
			if (selectedRouteId == routeId) {
				var routeDistance = ${route.distanta};
				$("#routeDistanceInformations").text("Distance: " + routeDistance);
				var found = 1;
				console.log("found route :" + routeId);
				<c:if test="${empty route.zboruri}">
					$("#deleteRouteModal").attr("disabled", false);
				</c:if>
			}
		</c:forEach>
		
		if(found != 1){
			$("#deleteAirportModal").attr("disabled", false);
		}
	})
	
	$("#packages-sel").change(function() {
		console.log("packages select change");
		var found = 0;
		<c:forEach items="${packagesList}" var="p"> 
			var selectedPackageId = $("#packages-sel option:selected").val();
			var packageId = ${p.id}; 
			console.log("packageId" + packageId);
			if (selectedPackageId == packageId) {
				var taxaPachet = ${p.taxaPachet};
				var caracteristici = "${p.caracteristici}";
				
				$("#infoPackageTax").text("Tax: " + taxaPachet);
				$("#infoPackageDescription").text("Characteristics: " + caracteristici);
				var found = 1;
				<%--
				<c:if test="${empty route.zboruri}">
					$("#deleteRouteModal").attr("disabled", false);
				</c:if>--%>
			}
		</c:forEach>
		<%--
		if(found != 1){
			$("#deleteAirportModal").attr("disabled", false);
		}
		--%>
	})
	
	$("#companies-sel").change(function() {
		$("#infoPackageTax").text("");
		$("#infoPackageDescription").text("");
	})
	
	
	$( document ).ready(function() {
	    sortAirports();
	    sortCountries();
	    sortCompanies();
	    sortPackages();
	});
	
	$(function () {
        $('#datetimepicker4').datetimepicker();
        $('#datetimepicker5').datetimepicker();
        $('#editDatetimepicker4').datetimepicker();
        $('#editDatetimepicker5').datetimepicker();
    });
	
	$('#saveAddFlightChanges').click(function() {
		$.ajax({
			type: 'POST',
			url: '${pageContext.request.contextPath}/addFlight',
			data: $('form[name=addFlight]').serialize(),
			dataType : 'json',
			success: function(data) {
				console.log("SUCCES");
				console.log(data);
				$('#myAddFlightModal').modal('hide');
				location.reload(true);
			}
		})
	})
	
	$('#saveEditFlightChanges').click(function() {
		console.log("saveEditFlightChanges");
		$.ajax({
			type: 'POST',
			url: '${pageContext.request.contextPath}/editFlight',
			data: $('form[name=editFlightForm]').serialize(),
			dataType : 'json',
			success: function(data) {
				console.log("SUCCES");
				console.log(data);
				$('#myEditFlightModal').modal('hide');
				location.reload(true);
			}
		})
		
	})
	
})

</script>


<!-- Modal Edit Country -->
<div class="modal fade" id="myEditCountryModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Edit Country</h4>
      </div>
      <form:form class="form-inline" id="countriesForm" modelAttribute="tara" method="post">
      	<div class="modal-body">
			<form:input path="id" id="editCountryInputId" style="display: none;"/>
			<strong>Country Name:</strong><form:input path="denumire" id="editCountryInputDenumire"/>
      	</div>
      	<div class="modal-footer">
        	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        	<button type="button" class="btn btn-primary" id="saveEditCountryChanges">Save changes</button>
      	</div>
	  	</form:form>
    </div>
  </div>
</div>
<!-- END Modal -->

<!-- Modal Add Country -->
<div class="modal fade" id="myAddCountryModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Add Country</h4>
      </div>
      	<div class="modal-body">
			<form:form class="form-inline" id="countriesForm" modelAttribute="tara" method="post">
				<strong>Country Name:</strong><form:input path="denumire" id="addCountryInputDenumire"/>
			</form:form>
      	</div>
      	<div class="modal-footer">
        	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        	<button type="button" class="btn btn-primary" id="saveAddCountryChanges">Save changes</button>
      	</div>
    </div>
  </div>
</div>
<!-- END Modal -->

<!-- Modal Edit Airport -->
<div class="modal fade" id="myEditAirportModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Edit Airport</h4>
      </div>
      	<div class="modal-body">
			<form:form class="form-inline" id="editAirportForm" modelAttribute="aeroport" method="post">
				<table>
				<tr>
					<td><strong>Airport Name:</strong></td>
					<td><form:input path="denumire" id="editAirportInputDenumire"/></td>
				</tr>
				<tr>
					<td><strong>Country:</strong></td>
					<td><form:input path="denumireTara" id="editAirportInputDenumireTara" disabled="true"/></td>
				</tr>
				</table>
				<form:input path="id" id="editedAirportId" style="display: none;"/>
				<form:input path="idTara" id="editAirportInputIdTara" style="display: none;"/>
			</form:form>
      	</div>
      	<div class="modal-footer">
        	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        	<button type="button" class="btn btn-primary" id="saveAirportChanges">Save changes</button>
      	</div>
    </div>
  </div>
</div>
<!-- END Modal -->

<!-- Modal Add Airport -->
<div class="modal fade" id="myAddAirportModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Add Airport</h4>
      </div>
      	<div class="modal-body">
			<form:form class="form-inline" id="addAirportForm" modelAttribute="aeroport" method="post">
				<table>
				<tr>
					<td><strong>Airport Name:</strong></td>
					<td><form:input path="denumire" id="addAirportInputDenumire"/></td>
				</tr>
				<tr>
					<td><strong>Country:</strong></td>
					<td><form:input path="denumireTara" id="addAirportInputDenumireTara" disabled="true"/></td>
				</tr>
				</table>
				<form:input path="idTara" id="addAirportInputIdTara" style="display: none;"/>
			</form:form>
      	</div>
      	<div class="modal-footer">
        	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        	<button type="button" class="btn btn-primary" id="saveAddAirportChanges">Save changes</button>
      	</div>
    </div>
  </div>
</div>
<!-- END Modal -->
 
<!-- Modal Edit Route -->
<div class="modal fade" id="myEditRouteModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Edit Route</h4>
      </div>
      	<div class="modal-body">
			<%--<form:form class="form-inline" id="addRouteForm" name="routeForm" modelAttribute="aeroport" method="post">
				<table>
					<tr>
						<td><strong>From Airport:</strong></td>
						<td><form:input path="denumire" id="editRoutefromAirport" disabled="true"/></td>
					</tr>
					<tr>
						<td><strong>To Airport:</strong></td>
						<td>
							<form:select class="form-control" id="editRoutetoAirport" path="idTara">
					        	<c:forEach items="${airportsList}" var="routeTo">
					        		<form:option value="${routeTo.id}">${routeTo.denumire}</form:option>
					        	</c:forEach>
					        </form:select>
				        </td>
					</tr>
				    <tr>
				        <td><strong>Distance:</strong></td>
				        <td><form:input type="number" path="distanta" id="editRouteDistance"/></td>
			        </tr>
			        <form:input path="id" id="editRouteFromAirportId" style="visibility: hidden" type="text"/>
				</table>
			</form:form> --%>
			Edit route ?!
      	</div>
      	<div class="modal-footer">
        	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        	<button type="button" class="btn btn-primary" id="saveCountryChanges">Save changes</button>
      	</div>
    </div>
  </div>
</div>
<!-- END Modal -->

<!-- Modal Add Route -->
<div class="modal fade" id="myAddRouteModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Add Route</h4>
      </div>
      	<div class="modal-body">
			<form:form class="form-inline" id="addRouteForm" name="routeForm" modelAttribute="aeroport" method="post">
				<table>
					<tr>
						<td><strong>From Airport:</strong></td>
						<td><form:input path="denumire" id="fromAirport" disabled="true"/></td>
					</tr>
					<tr>
						<td><strong>To Airport:</strong></td>
						<td>
							<form:select class="form-control" id="toAirport" path="idTara">
					        	<c:forEach items="${airportsList}" var="routeTo">
					        		<form:option value="${routeTo.id}">${routeTo.denumire}</form:option>
					        	</c:forEach>
					        </form:select>
				        </td>
					</tr>
				    <tr>
				        <td><strong>Distance:</strong></td>
				        <td><form:input type="number" path="distanta" id="distance"/></td>
			        </tr>
			        <form:input path="id" id="fromAirportId" style="visibility: hidden" type="text"/>
				</table>
			</form:form>
      	</div>
      	<div class="modal-footer">
        	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        	<button type="button" class="btn btn-primary" id="saveNewRoute">Save changes</button>
      	</div>
    </div>
  </div>
</div>
<!-- END Modal -->

<!-- Modal Add Flight -->
<div class="modal fade" id="myAddFlightModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Add Flight</h4>
      </div>
      	<div class="modal-body">
			<form:form class="form-inline" id="flightsForm" name="addFlight" modelAttribute="aeroport" method="post">
				<table>
					<tr>
						<td>Route:</td>
						<td>
							<form:select class="form-control" id="addFlightRoutes" path="idRoute">
								<c:forEach items="${routesList}" var="route">
									<form:option value="${route.id}">${route.aeroport_1.denumire} - ${route.aeroport_2.denumire}</form:option>
								</c:forEach>
							</form:select>
						</td>
					</tr>
					<tr>
						<td>Departure Date:</td>
						<td>
							<div class='input-group date' id='datetimepicker4'>
						    	<form:input type='text' path="departureDate" class="form-control" />
						        <span class="input-group-addon">
						     		<span class="glyphicon glyphicon-calendar"></span>
						        </span>
						     </div>
    					</td>
					</tr>
					<tr>
						<td>Arrival Date:</td>
						<td>
							<div class='input-group date' id='datetimepicker5'>
						    	<form:input type='text' path="arrivalDate" class="form-control" />
						        <span class="input-group-addon">
						     		<span class="glyphicon glyphicon-calendar"></span>
						        </span>
						     </div>
    					</td>
					</tr>
					<tr>
						<td>Company:</td>
						<td>
							<form:select class="form-control" id="addFlightCompanies" path="companyId">
								<c:forEach items="${companiesList}" var="company">
									<form:option value="${company.id}">${company.denumire}</form:option>
								</c:forEach>
							</form:select>
						</td>
					</tr>
					<tr>
						<td>Airplane:</td>
						<td>
							<form:select class="form-control" id="addFlightCompanies" path="airlineId">
								<c:forEach items="${airplanesList}" var="airplane">
									<form:option value="${airplane.id}">${airplane.tipAvion.denumire} - ${airplane.id}</form:option>
								</c:forEach>
							</form:select>
						</td>
					</tr>
					<tr>
						<td>Standard Price:</td>
						<td><form:input path="standardPrice" type="number"/></td>
					</tr>
				</table>
			</form:form>
      	</div>
      	<div class="modal-footer">
        	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        	<button type="button" class="btn btn-primary" id="saveAddFlightChanges">Save changes</button>
      	</div>
    </div>
  </div>
</div>
<!-- END Modal -->   

<!-- Modal Edit Flight -->
<div class="modal fade" id="myEditFlightModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Edit Flight</h4>
      </div>
      	<div class="modal-body">
			<form:form class="form-inline" id="flightsForm" name="editFlightForm" modelAttribute="aeroport" method="post">
				<table>
					<tr>
						<td>Route:</td>
						<td>
							<form:select class="form-control" id="editFlightRoutes" path="idRoute">
								<c:forEach items="${routesList}" var="route">
									<form:option value="${route.id}">${route.aeroport_1.denumire} - ${route.aeroport_2.denumire}</form:option>
								</c:forEach>
							</form:select>
						</td>
					</tr>
					<tr>
						<td>Departure Date:</td>
						<td>
							<div class='input-group date' id='editDatetimepicker4'>
						    	<form:input type='text' path="departureDate" class="form-control" />
						        <span class="input-group-addon">
						     		<span class="glyphicon glyphicon-calendar"></span>
						        </span>
						     </div>
    					</td>
					</tr>
					<tr>
						<td>Arrival Date:</td>
						<td>
							<div class='input-group date' id='editDatetimepicker5'>
						    	<form:input type='text' path="arrivalDate" class="form-control" />
						        <span class="input-group-addon">
						     		<span class="glyphicon glyphicon-calendar"></span>
						        </span>
						     </div>
    					</td>
					</tr>
					<tr>
						<td>Company:</td>
						<td>
							<form:select class="form-control" id="editFlightCompanies" path="companyId">
								<c:forEach items="${companiesList}" var="company">
									<form:option value="${company.id}">${company.denumire}</form:option>
								</c:forEach>
							</form:select>
						</td>
					</tr>
					<tr>
						<td>Airplane:</td>
						<td>
							<form:select class="form-control" id="editFlightAirplanes" path="airlineId">
								<c:forEach items="${airplanesList}" var="airplane">
									<form:option value="${airplane.id}">${airplane.tipAvion.denumire} - ${airplane.id}</form:option>
								</c:forEach>
							</form:select>
						</td>
					</tr>
					<tr>
						<td>Standard Price:</td>
						<td><form:input path="standardPrice" type="number" id="editStandardPrice"/></td>
					</tr>
					<form:input path="id" id="editFlightId" style="visibility: hidden" type="text"/>
				</table>
			</form:form>
      	</div>
      	<div class="modal-footer">
        	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        	<button type="button" class="btn btn-primary" id="saveEditFlightChanges">Save changes</button>
      	</div>
    </div>
  </div>
</div>
<!-- END Modal -->           
        
        
  </body>
</html>
