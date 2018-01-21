<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="constants.ApplicationConstants"%>

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
    <title>Rezervare Bilete - Change password</title>
  </head>

<body>
	<br>
	<c:if test="${not empty exceptie}">
			<div  class="close" data-dismiss="alert" aria-label="close">×</div>
			<div class="alert alert-danger"><strong>Eroare!</strong>${exceptie}</div>
		<br>
	</c:if>
		
	<div class="container">
		<div class="featured-content">
			<div class="col-md-12">
				<div class="module form-module">
					<div class="toggle"></div>
					<div class="form">
						<form:form method="POST" action="changepassword" id="loginForm" modelAttribute="loginBean">
							<form:label path="userName">Cont</form:label>
							<form:input path="userName" type="text" placeholder="username" value="${loginBean.userName}"/><br>
							<form:input path="password" type="password" placeholder="Parola"/><br>
							<form:input path="repeatPassword" type="password" placeholder="Repeta Parola" value=""/><br>
							<button type="submit" id="login" value="Schimba parola">Modifica parola</button>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>