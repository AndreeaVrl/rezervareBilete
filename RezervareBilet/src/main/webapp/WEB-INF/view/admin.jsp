<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <!-- Datepicker -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

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
            <li><a href="#">Logout<span class="sr-only">(current)</span></a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>


<!-- MESAJE -->
<div class="container">
	<div class="row">
		<div class="alert alert-warning alert-dismissible" role="alert">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  <strong>Warning!</strong> Better check yourself, you're not looking too good.
		</div>
	</div>
</div>

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
	        	<button class="btn btn-primary" name="submit_remove" type="button"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
	        </div>
        </form:form>
      </div>		
		</div>
		
		<div class="col-md-3">
			<div class="panel panel-info">
        <div class="panel-heading">
          <h3 class="panel-title text-center">Airports</h3>
        </div>
        <form class="form-inline" action="" method="post">
	        <div class="panel-body">
	        	<div class="form-group">
			        <select class="form-control" id="airports-sel" size="20"> <!-- trage date din tabela aeroporturi functie de tara -->
			          <option>Linz</option>
			          <option>Salzburg</option>
			        </select>
			      </div>
	        </div>
	        <div class="panel-footer text-center">
	        	<button class="btn btn-primary" name="submit_edit" type="button" data-toggle="modal" data-target="#myEditAirportModal" id="editAirportModal"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_add" type="button" data-toggle="modal" data-target="#myAddAirportModal" id="addAirportModal"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_remove" type="button"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
	        </div>
        </form>
      </div>		
		</div>
		
		<div class="col-md-6">
			<div class="panel panel-info">
        <div class="panel-heading">
          <h3 class="panel-title text-center">Route To</h3>
        </div>
        <form class="form-inline" action="" method="post">
	        <div class="panel-body">
	        	<div class="col-md-6">
		        	<div class="form-group">
				        <select class="form-control" id="routes-sel" size="20"> <!-- trage date din tabela curse functie de aeroport1 -->
				          <option>Paris</option>
				          <option>Bucharest</option>
				        </select>
				      </div>
				    </div>
				    <div class="col-md-6" id="route_info"> <!-- se actualizeaza functie de ruta aleasa -->
				    	<h4>Informations</h4>
				    	Company:<br />
				    	Starting price:<br />
				    	Packages:<br />
				    	...
				    </div>
	        </div>
	        <div class="panel-footer text-center">
	        	<button class="btn btn-primary" name="submit_edit" type="button" data-toggle="modal" data-target="#myEditRouteModal" id="editRouteModal"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_add" type="button" data-toggle="modal" data-target="#myAddRouteModal" id="editRouteModal"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_remove" type="button"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
	        </div>
        </form>
      </div>		
		</div>
		
  </div>
</div>

<!-- MENIU COMPANIES -->
<div class="container">
	<hr /> 
	<div class="row">
		<h4>MENIU COMPANIES</h4>	
		<div class="col-md-3">
			<div class="panel panel-info">
        <div class="panel-heading">
          <h3 class="panel-title text-center">Companies</h3>
        </div>
        <form class="form-inline" action="" method="post">
	        <div class="panel-body">
	        	<div class="form-group">
			        <select class="form-control" id="companies-sel" size="20"> <!-- trage date din tabela companii -->
			          <option>Wizz-Air</option>
			          <option>Panair</option>
			          <option>Rayan Air</option>
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
          <h3 class="panel-title text-center">Packages</h3>
        </div>
        <form class="form-inline" action="" method="post">
	        <div class="panel-body">
	        	<div class="col-md-6">
		        	<div class="form-group">
				        <select class="form-control" id="packages-sel" size="20"> <!-- trage date din tabela pachete functie de companie -->
			          <option>Standard</option>
			          <option>Plus</option>
			          <option>Flexi-Plus</option>
			        </select>
				      </div>
				    </div>
				    <div class="col-md-6" id="route_info"> <!-- se actualizeaza functie de pachetul ales -->
				    	<h4>Informations</h4>
				    	Company:<br />
				    	Package name:<br />
				    	Price:<br />
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
        <form class="form-inline" action="" method="post">
	        <div class="panel-body">
	        	<div class="table-responsive">
						  <table class="table table-striped table-hover">
						  	<thead>
									<tr>
										<th>#</th>
										<th>Departure</th>
										<th>Arrival</th>
										<th>Company</th>
										<th>Airliner</th>
									</tr>
								</thead>
						    <tbody>
						    	<tr>
						    		<td>1</td>
						    		<td>Germany-Hamburg</td>
						    		<td>Italy - Roma</td>
						    		<td>Ryan Air</td>
						    		<td>Boeing 747 - N988NA</td>
						    	</tr>
						    	<tr>
						    		<td>2</td>
						    		<td>Germany-Hamburg</td>
						    		<td>Italy - Roma</td>
						    		<td>Ryan Air</td>
						    		<td>Boeing 747 - N988NA</td>
						    	</tr>
						    	<tr>
						    		<td>3</td>
						    		<td>Germany-Hamburg</td>
						    		<td>Italy - Roma</td>
						    		<td>Ryan Air</td>
						    		<td>Boeing 747 - N988NA</td>
						    	</tr>
						    </tbody>
						  </table>
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
  
   

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

<script>
	<%-- setup things--%>
	$("#editCountryModal").attr("disabled", true);
	
	
	$("#countries-sel").append($("#countries-sel option").remove().sort(function(a, b) {
	    var at = $(a).text(), bt = $(b).text();
	    return (at > bt)?1:((at < bt)?-1:0);
	}));
	<%-- setup things--%>

	function showAddCountryInput(){
		document.getElementById("editCountryInputId").style.display = "inline";
	}
	
	function submitNewCountry(){
		document.getElementById("countriesForm").action("addCountry");
		document.getElementById("countriesForm").submit();
		
	}

	$(document).ready(function(){
		<%-- JQQ --%>
		<%-- add country --%>
		
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
					    value: 1,
					    text: newCountryName
					}));
					console.log("added country to select");
					
					$("#countries-sel").append($("#countries-sel option").remove().sort(function(a, b) {
					    var at = $(a).text(), bt = $(b).text();
					    return (at > bt)?1:((at < bt)?-1:0);
					}));
				}
			})

			
			$('#myAddCountryModal').modal('hide');
			<%-- TODO add country to list
			$( "#countries-sel option:selected" ).text(newCountryName);
			$('#myEditCountryModal').modal('hide');
			--%>
		})
		
		<%-- add country --%>
		
		<%-- edit country --%>
		$('#editCountryModal').click(function(){
			console.log("in modal jq");
			var selectedCountryId = $( "#countries-sel option:selected" ).val();
			var selectedCountry = $( "#countries-sel option:selected" ).text();
			console.log(selectedCountryId);
			console.log(selectedCountry);
			$("#editCountryInputId").val(selectedCountryId);
			$("#editCountryInputDenumire").val(selectedCountry);
		})
		
		$("#countries-sel").change(function() {
			$("#editCountryModal").removeClass("disabled");
			$("#editCountryModal").attr("disabled", false);
		})
		
		$('#saveEditCountryChanges').click(function() {
			var countryIdChange = $('#editCountryInputId').val();
			var countryNameChange = $('#editCountryInputDenumire').val();
			
			$.ajax({
				type: 'POST',
				url: '${pageContext.request.contextPath}/addCountry',
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
		<%-- edit country --%>
		
		
	})
</script>


<!-- Modal Edit Country -->
<div class="modal fade" id="myEditCountryModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Country edit</h4>
      </div>
      <form:form class="form-inline" id="countriesForm" modelAttribute="tara" method="post">
      	<div class="modal-body">
					<form:input path="id" id="editCountryInputId" style="display: none;"/>
					<form:input path="denumire" id="editCountryInputDenumire"/>
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
        <h4 class="modal-title">Country add</h4>
      </div>
      	<div class="modal-body">
			<form:form class="form-inline" id="countriesForm" modelAttribute="tara" method="post">
				<form:input path="denumire" id="addCountryInputDenumire"/>
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
        <h4 class="modal-title">Airport edit</h4>
      </div>
      	<div class="modal-body">
					edit airport
      	</div>
      	<div class="modal-footer">
        	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        	<button type="button" class="btn btn-primary" id="saveCountryChanges">Save changes</button>
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
        <h4 class="modal-title">Airport add</h4>
      </div>
      	<div class="modal-body">
					add airport
      	</div>
      	<div class="modal-footer">
        	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        	<button type="button" class="btn btn-primary" id="saveCountryChanges">Save changes</button>
      	</div>
    </div>
  </div>
</div>
<!-- END Modal -->
 
<!-- Modal Edit Airport -->
<div class="modal fade" id="myEditRouteModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Route edit</h4>
      </div>
      	<div class="modal-body">
					edit route
      	</div>
      	<div class="modal-footer">
        	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        	<button type="button" class="btn btn-primary" id="saveCountryChanges">Save changes</button>
      	</div>
    </div>
  </div>
</div>
<!-- END Modal -->

<!-- Modal Add Airport -->
<div class="modal fade" id="myAddRouteModal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Route add</h4>
      </div>
      	<div class="modal-body">
					add route
      	</div>
      	<div class="modal-footer">
        	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        	<button type="button" class="btn btn-primary" id="saveCountryChanges">Save changes</button>
      	</div>
    </div>
  </div>
</div>
<!-- END Modal -->   
        
  </body>
</html>
