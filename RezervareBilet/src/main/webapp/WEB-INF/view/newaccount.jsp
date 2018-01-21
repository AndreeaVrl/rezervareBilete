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

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <title>Rezervare Bilete - Create New Account</title>
  </head>
  
	<body>
	<c:if test="${not empty succes}">
			<div class="close" data-dismiss="alert" aria-label="close">�</div>
			<div class="alert alert-success"><strong>Succes!</strong> ${succes}</div>
		<br>
	</c:if>
	
	<c:if test="${not empty exceptie}">
			<div class="close" data-dismiss="alert" aria-label="close">�</div>
			<div class="alert alert-danger"><strong>Eroare!</strong>${exceptie}</div>
		<br>
	</c:if>
	
	<div class="container">

      <form:form class="form-signin" method="POST" action="createNewAccount" modelAttribute="clientBean">
        <h2 class="form-signin-heading">New Account</h2>
        <label for="firstName" class="sr-only">First Name</label>
        <form:input type="text" id="firstName" class="form-control" placeholder="First Name"  value="${clientBean.prenume}" path="nume" required="true"/>
        <label for="lastName" class="sr-only">Last Name</label>
        <form:input type="text" id="lastName" class="form-control" placeholder="Last Name"  value="${clientBean.nume}" path="prenume" required="true"/>
        <label for="userName" class="sr-only">Email address</label>
        <form:input type="text" id="userName" class="form-control" placeholder="Email Adress"  value="${clientBean.email}" path="email" required="true"/>
        <label for="password" class="sr-only">Password</label>
        <form:input type="password" id="password" class="form-control" placeholder="Password" value="${clientBean.parola}" path="parola" required="true"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit" id="login" value="Log In">Log in</button>
      </form:form>

    </div> <!-- /container -->
    
	</body>
</html>