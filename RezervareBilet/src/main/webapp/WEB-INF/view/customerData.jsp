<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
    
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	    <link href="${pageContext.request.contextPath}/resources/css/rezervareBilete.css" rel="stylesheet">
	    <!-- Datepicker -->
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
		
		<!-- Include Date Range Picker -->
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
		
	
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-md-3">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                Pasageri
                            </h3>
                        </div>
                        <div class="panel-body">
                            <form:form action="invoicing" modelAttribute="pasageri">
	                            <c:forEach var = "i" begin = "0" end = "${flight.passengers-1}"> 
		                            <div class="form-group">
		                                <label for="nume">Nume</label>
		                                <div class="input-group">
		                                    <form:input class="form-control nume" path="listaClienti[${i}].nume" placeholder="Nume"/>
		                                </div>
		                            </div>
		                            <div class="form-group">
		                                <label for="prenume"> Prenume</label>
		                                <div class="input-group">
		                                    <form:input class="form-control prenume" path="listaClienti[${i}].prenume" placeholder="Prenume"/>
		                                </div>
		                            </div>
		                            <div class="form-group">
		                                <label for="dataNasterii">Data nasterii</label>
		                                <div class="input-group">
		                                    <form:input class="datepicker form-control dataNasterii" path="listaClienti[${i}].dataNasterii" placeholder="dd/mm/yyyy" />
		                                </div>
		                            </div>
		                           <br><br>
	                           </c:forEach>
	                           <input type="submit" value="Continua!"/>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
		
        <script>
        $(document).ready(function(){
			$(function() {
				$( ".datepicker" ).datepicker({ 
					format: 'dd/mm/yyyy',
					todayHighlight: true,
					autoclose: true,
				});
			}); 
			 $('form').submit(function(){
	        	  var nume = document.getElementsByClassName('nume');
	        	  var prenume = document.getElementsByClassName('prenume');
	        	  var dataNasterii = document.getElementsByClassName('dataNasterii');
	    		  return !completDatePassengers(nume,prenume,dataNasterii, 'Va rog completati toate datele fiecarui pasager!');
	  		});
        });
        </script>
</body>
</html>