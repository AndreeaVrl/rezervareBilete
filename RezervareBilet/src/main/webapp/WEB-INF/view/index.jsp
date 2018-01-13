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
	<c:if test="${not empty exceptie}">
			<div  class="close" data-dismiss="alert" aria-label="close">×</div>
			<div class="alert alert-danger"><strong>Eroare!</strong>${exceptie.message}</div>
		<br>
	</c:if>
		<div class="col-md-6">
    	<form:form class="form-inline" action="getRoute" method="POST" modelAttribute="cursa">
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
			<label for="date">Fly out date</label>
			<input class="form-control" id="date" name="date" placeholder="DD/MM/YYYY" type="text"/>
        <br />
			<input id="retur" type="checkbox">Retur <br>
        	<input class="btn btn-primary" name="submit" type="submit" value="Continue"/></form:form>
		</div>
	</div>
</section>
<c:if test="${not empty zboruriCautare}">
	<hr />
	<!-- afisare zboruri  -->
	<section>
		<div id="cautare">
			<c:forEach items="${zboruriCautare}" var="zbor">
			<c:set var="nrListe" value="1"/>
				<input type="radio" name="zborCautat" value="${zbor.key}">
				<c:forEach items="${zbor.value}" var="zborCautare" varStatus = "status">
					<c:choose>
						<c:when test="${status.first}">
							${zborCautare.cursa.aeroport_1.denumire}-${zborCautare.cursa.aeroport_2.denumire}
							<c:set var="nrListe" value="2"/>
						</c:when>
						<c:otherwise>
							<c:if test="${nrListe eq 2}">
								-${zborCautare.cursa.aeroport_2.denumire}
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:forEach>
		</div>
	</section>
</c:if>
<!-- END Selectare date plecare sosire  -->
<!-- Selectare data plecare 
<section>
	<div class="row">
		<div class="col-md-6 data-plecare">
			<form action="" class="form-inline" method="post">
				<div class="form-group">
					<label for="date">Fly out date</label>
          <input class="form-control" id="date" name="date" placeholder="DD/MM/YYYY" type="text"/>
				</div>
				<br />
				<button class="btn btn-primary" name="submit" type="submit">Continue</button>
			</form>
		</div>
	</div>
</section>
END Selectare data plecare -->

<hr />
<!-- Selectare nr pasageri -->
<section>
	<div class="row">
		<div class="col-md-6">
    	<form class="form-inline" action="" method="post">
        <div class="form-group">
          <label for="passengers">Passengers</label>
          <select class="form-control" id="passengers">
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option>5</option>
          </select>
        </div>
        <br />
        <button class="btn btn-primary" name="submit" type="submit">Continue</button>
      </form>
		</div>
	</div>
</section>
<!-- END Selectare nr pasageri -->

<hr />
<!-- Selectare nr pasageri -->
<section>
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
            <button class="btn btn-primary" name="submit" type="submit"> &euro; 120 </button>
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
            <button class="btn btn-primary" name="submit" type="submit"> &euro; 160 </button>
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
            <button class="btn btn-primary" name="submit" type="submit"> &euro; 185 </button>
          </div>
        </div>
      </div>
    </div>
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
		var date_input=$('input[name="date"]'); //our date input has the name "date"
		var container=$('.data-plecare form').length>0 ? $('.data-plecare form').parent() : "body";
		date_input.datepicker({
			format: 'dd/mm/yyyy',
			container: container,
			todayHighlight: true,
			autoclose: true,
		})
	})
</script> 
    
        
  </body>
</html>
