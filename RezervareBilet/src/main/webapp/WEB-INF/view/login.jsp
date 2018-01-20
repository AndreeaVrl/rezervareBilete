<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
	<body>
	<c:if test="${not empty succes}">
			<div  class="close" data-dismiss="alert" aria-label="close">×</div>
			<div class="alert alert-success"><strong>Succes!</strong> ${succes}</div>
		<br>
	</c:if>
	
	<c:if test="${not empty exceptie}">
			<div  class="close" data-dismiss="alert" aria-label="close">×</div>
			<div class="alert alert-danger"><strong>Eroare!</strong>${exceptie}</div>
		<br>
	</c:if>
	<div>
		<form:form method="POST" action="login" id="loginForm">
			  <input type="text" id="userName" name="userName" placeholder="Username" value="${loginBean.userName}"/><br>
			  <input type="password" id="password" name="password" placeholder="Password"  value="${loginBean.password}"/><br>
			  <button type="submit" id="login" value="Log In">Log In</button><br>
		</form:form>
	</div>
		<br><a href="goToChangePasswordPage">Forgot your password?</a>
	</body>
</html>