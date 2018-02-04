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
	<style>
		.datagrid table { border-collapse: collapse; text-align: left; width: 100%; } .datagrid {font: normal 12px/150% Arial, Helvetica, sans-serif; background: #fff; overflow: hidden; border: 1px solid #006699; -webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px; }.datagrid table td, .datagrid table th { padding: 3px 10px; }.datagrid table thead th {background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F) );background:-moz-linear-gradient( center top, #006699 5%, #00557F 100% );filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699', endColorstr='#00557F');background-color:#006699; color:#FFFFFF; font-size: 15px; font-weight: bold; border-left: 1px solid #0070A8; } .datagrid table thead th:first-child { border: none; }.datagrid table tbody td { color: #00557F; border-left: 1px solid #E1EEF4;font-size: 12px;font-weight: normal; }.datagrid table tbody .alt td { background: #E1EEf4; color: #00557F; }.datagrid table tbody td:first-child { border-left: none; }.datagrid table tbody tr:last-child td { border-bottom: none; }.datagrid table tfoot td div { border-top: 1px solid #006699;background: #E1EEf4;} .datagrid table tfoot td { padding: 0; font-size: 12px } .datagrid table tfoot td div{ padding: 2px; }.datagrid table tfoot td ul { margin: 0; padding:0; list-style: none; text-align: right; }.datagrid table tfoot  li { display: inline; }.datagrid table tfoot li a { text-decoration: none; display: inline-block;  padding: 2px 8px; margin: 1px;color: #FFFFFF;border: 1px solid #006699;-webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px; background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F) );background:-moz-linear-gradient( center top, #006699 5%, #00557F 100% );filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699', endColorstr='#00557F');background-color:#006699; }.datagrid table tfoot ul.active, .datagrid table tfoot ul a:hover { text-decoration: none;border-color: #00557F; color: #FFFFFF; background: none; background-color:#006699;}div.dhtmlx_window_active, div.dhx_modal_cover_dv { position: fixed !important; }
    </style>
    
    <script>
    $(document).ready(function() {
        $('#listaBilete').DataTable({
        	columnDefs: [
		        		  { 'bSortable': true, 'aTargets': [ 1 ] }
		        		],
			        "language": {
			            "lengthMenu": "Afiseaza _MENU_ inregistrari pe pagina",
			            "zeroRecords": "Nici o inregistrare gasita",
			            "info": "Pagina _PAGE_ din _PAGES_",
			            "infoEmpty": "Nici o inregistrare gasita",
			            "infoFiltered": "(filtrare din _MAX_ inregistrari)",
			            "search": "Cauta:",    
			            "paginate": {
			                "first":      "Prima",
			                "last":       "Ultima",
			                "next":       "Urmatoare",
			                "previous":   "Precedenta"
			            }
			        }
		        });
		    } );
    function setIdForDelete(idBilet){
    	var ask = window.confirm("Doriti sa anulati aceasta rezervare?");
	    if (ask) {
			var id = idBilet;
			$.ajax({
				type: 'POST',
				url: '${pageContext.request.contextPath}/deleteReservation',
				data: {id: id},
				dataType : 'text',
				success: function(data) {
					mesaj=data;
					console.log("id="+id);
					$('#'+id).hide();
					alert(mesaj);
				}
			})
	    }
    }
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
<h2 class="section-title">Lista rezervari</h2>
<div class="row">
	<div class="col-md-12">
		<c:if test="${not empty succesDelete}">
			<div  class="close" data-dismiss="alert" aria-label="close">×</div>
			<div class="alert alert-success"><strong>Success!</strong>${succesDelete}</div>
		<br>
		</c:if>
		<c:if test="${not empty erorreDelete}">
			<div  class="close" data-dismiss="alert" aria-label="close">×</div>
			<div class="alert alert-danger"><strong>Eroare!</strong>${erorreDelete}</div>
		<br>
		</c:if>
		<table id="listaBilete" class="table" >
			<thead>
		      <tr>
		        <th>Numar bilet</th>
		        <th>Zbor</th>
		        <th>Nume pasager</th>
		        <th>Pachet facilitati</th>
				<th>Loc</th>
		        <th></th>
		      </tr>
		    </thead>
		    <tbody>
		      <c:forEach items="${listaBilete}" var="bilet">
			      <tr id="${bilet.id}">
			        <td>${bilet.id}</td>
			        <td>${bilet.zbor.cursa.aeroport_1.denumire}-${bilet.zbor.cursa.aeroport_2.denumire}</td>
			        <td>${bilet.clientBilet.nume} ${bilet.clientBilet.prenume}</td>
			        <td>${bilet.pachet.denumire}</td>
			        <td>${bilet.loc.rand}-${bilet.loc.coloana}</td>
			        <td></td>
					<td>
						<img src="${pageContext.request.contextPath}/resources/images/deleteButton.jpg" title="Delete" style="width:20px;" onClick="setIdForDelete(${bilet.id})">
					</td>
				 </tr>
			 </c:forEach>
		    </tbody>
		  </table>
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
