<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/validation.js"></script>
    <script type="text/javascript">
     $(document).ready(function(){
		 $('form').submit(function(){
			 return (!isEmpty('factAdresa','Va rog completati adresa de facturare!') &&
   		    		 !isEmpty('factLocalitate','Va rog completati denumirea localitatii!') &&
   		    		 !isEmpty('factZipcode','Va rog completati zipcodul!'))
    	});
    });
    </script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-md-3">
                <c:if test="${not empty succes}">
                	<div class="alert alert-success">
					  <strong>Success!</strong> ${succes}
					</div>
                </c:if>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                Date facturare
                            </h3>
                        </div>
                        <div class="panel-body" id="persFizica">
                            <form:form action="makingPayment" modelAttribute="factura">
	                            <div class="form-group">
	                                <label for="adresa"> Adresa</label>
	                                <div class="input-group">
	                                    <form:input class="form-control" path="factAdresa" placeholder="Adresa" />
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label for="factLocalitate"> Localitate</label>
	                                <div class="input-group">
	                                    <form:input class="form-control" path="factLocalitate" placeholder="Localitate" />
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label for="factZipcode"> Zipcode </label>
	                                <div class="input-group">
	                                    <form:input class="form-control" path="factZipcode" placeholder="Zipcode" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
	                                </div>
	                            </div>
	                            <input type="submit" value="Continua rezervarea!" />
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>