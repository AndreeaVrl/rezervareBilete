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
	                                <div>
	                                    <form:input class="form-control" path="factAdresa" placeholder="Adresa" />
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label for="factLocalitate"> Localitate</label>
	                                <div>
	                                    <form:input class="form-control" path="factLocalitate" placeholder="Localitate" />
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label for="factZipcode"> Zipcode </label>
	                                <div>
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