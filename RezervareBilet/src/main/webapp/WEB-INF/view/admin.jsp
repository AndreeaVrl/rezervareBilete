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
			        <select class="form-control" id="countries-sel" size="20"> <!-- trage date din tabela tari -->
			        	<c:forEach items="${countriesList}" var="country">
			        		<option>${country.denumire}</option>
			        	</c:forEach>
			        </select>
			      </div>
	        </div>
	        <div class="panel-footer text-center">
	        	<button class="btn btn-primary" name="submit_edit" type="button"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_plus" type="button"><span class="glyphicon glyphicon-plus" aria-hidden="true" onClick="showAddCountryInput();"></span></button>
	        	<button class="btn btn-primary" name="submit_remove" type="button"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
	        </div>
	        <form:input path="denumire" id="addCountryInputId" style="display: none;"/>
	        <button class="btn btn-primary" name="submit_plus" id="confirmCountryButton" type="button"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
	        <button class="btn btn-primary" name="submit_remove" type="button"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
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
	        	<button class="btn btn-primary" name="submit_edit" type="submit"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_plus" type="submit"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_remove" type="submit"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
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
	        	<button class="btn btn-primary" name="submit_edit" type="submit"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_plus" type="submit"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
	        	<button class="btn btn-primary" name="submit_remove" type="submit"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
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
	        	<button class="btn btn-primary" name="submit_plus" type="submit"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
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
	        	<button class="btn btn-primary" name="submit_plus" type="submit"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
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
	        	<button class="btn btn-primary" name="submit_plus" type="submit"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
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
	        	<button class="btn btn-primary" name="submit_plus" type="submit"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
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
	        	<button class="btn btn-primary" name="submit_plus" type="submit"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></button>
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
	function showAddCountryInput(){
		document.getElementById("addCountryInputId").style.display = "inline";
	}
	
	function submitNewCountry(){
		document.getElementById("countriesForm").action("addCountry");
		document.getElementById("countriesForm").submit();
		
	}
	
	$(document).ready(function(){
		$('#confirmCountryButton').click(function(){
			var countryName = $('#addCountryInputId').val();
			countryName = jquery.trim(countryName);
			if(countryName != ''){
				alert("we did it" + countryName);
			}
		})
	}
</script>

    
        
  </body>
</html>
