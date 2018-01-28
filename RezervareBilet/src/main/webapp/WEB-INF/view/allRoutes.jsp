<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/rezervareBilete.css" rel="stylesheet">
    <title>Rezervare Bilete</title>
    
  </head>
  <body>
		<c:if test="${not empty allRoutesMap}">
			<h4>Zboruri</h4>
		</c:if>
		<c:choose>
			<c:when test="${not empty allRoutesMap}">
				<hr />
				<!-- afisare zboruri  -->
				<section>
					<div id="cautare">
						<table>
							<c:forEach items="${allRoutesMap}" var="zbor">
								<c:set var="first" value="1"/>
								<tr>
								<td>
									<input type="radio" name="zborCautat" value="${zbor.key}">
									<input type="hidden" id="${zbor.key}">
								</td>
								<c:forEach items="${zbor.value}" var="zboruriCautare" >
									<td>
										<c:choose>
											<c:when test="${first eq 1}">
												${zboruriCautare[0].cursa.aeroport_1.denumire}-${zboruriCautare[0].cursa.aeroport_2.denumire}
												<select id="${zboruriCautare[0].cursa.id}">
													<c:forEach items="${zboruriCautare}" var="zborCautare">
														<option value="${zborCautare.companie.id}"/>
														Companie: ${zborCautare.companie.denumire}, Pret: ${zborCautare.pret}€
													</c:forEach>
												</select>
											</c:when>
											<c:otherwise>
												-${zboruriCautare[0].cursa.aeroport_2.denumire}
												<select id="${zboruriCautare[0].cursa.id}">
													<c:forEach items="${zboruriCautare}" var="zborCautare">
														<option value="${zborCautare.companie.id}"/>
														Companie: ${zborCautare.companie.denumire}, Pret: ${zborCautare.pret}€
													</c:forEach>
												</select>
											</c:otherwise>
										</c:choose>
									</td>
								</c:forEach>
								</tr>
							</c:forEach>
						</table>
					</div>
				</section>
			</c:when>
			<c:otherwise>
				<div  class="close" data-dismiss="alert" aria-label="close">×</div>
				<div class="alert alert-info"><strong>Atentie!</strong>Ne pare rau, nu a fost gasit nicun zbor pentru datele specificate!</div>
			</c:otherwise>
		</c:choose>
		<c:if test="${not empty cursaRequestView && cursaRequestView.retur}">
			<hr />
			<c:choose>
				<c:when test="${empty mapZboruriRetur}">
					<div  class="close" data-dismiss="alert" aria-label="close">×</div>
					<div class="alert alert-info"><strong>Atentie!</strong>Nu au fost gasite zboruri de retur!</div>
				<br>
				</c:when>
				<c:otherwise>
					<!-- afisare zboruri  -->
					<section>
						<div id="cautare">
							<table>
								<c:forEach items="${mapZboruriPlecare}" var="zbor">
									<c:set var="first" value="1"/>
									<tr>
									<td>
										<input type="radio" name="zborCautat" value="${zbor.key}">
									</td>
									<c:forEach items="${zbor.value}" var="zboruriCautare" >
										<td>
											<c:choose>
												<c:when test="${first eq 1}">
													${zboruriCautare[0].cursa.aeroport_1.denumire}-${zboruriCautare[0].cursa.aeroport_2.denumire}
													<select>
														<c:forEach items="${zboruriCautare}" var="zborCautare">
															<option value="${zborCautare.companie.id}"/>
															Companie: ${zborCautare.companie.denumire}, Pret: ${zborCautare.pret}€
														</c:forEach>
													</select>
												</c:when>
												<c:otherwise>
													-${zboruriCautare[0].cursa.aeroport_2.denumire}
													<select>
														<c:forEach items="${zboruriCautare}" var="zborCautare">
															<option value="${zborCautare.companie.id}"/>
															Companie: ${zborCautare.companie.denumire}, Pret: ${zborCautare.pret}€
														</c:forEach>
													</select>
												</c:otherwise>
											</c:choose>
										</td>
									</c:forEach>
									</tr>
								</c:forEach>
							</table>
						</div>
					</section>
				</c:otherwise>
			</c:choose>
		</c:if>
		<input type="hidden" id="companii"/>
		<input class="btn btn-primary" name="createComp" id="createComp" type="button" value="valoare"/>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			$(document).ready( function() {
				$('#createComp').click( function(){
					var zborAles = $('input[name=zborCautat]:checked').val();
					console.log("zborAles="+zborAles);
					<c:forEach items="${allRoutesMap}" var="zbor">
						var mapKey = ${zbor.key};
						console.log("zbor= "+zborAles+"mapKey="+mapKey+"comparare="+${zborAles.intValue ==+ mapKey.intValue}+"comparare="+${zborAles eq zbor.key});
						<c:forEach items="${zbor.value}" var="zboruriCautare" >
							<c:forEach items="${zboruriCautare}" var="zborCautare">
								console.log("aici");
							</c:forEach>
						</c:forEach>
					</c:forEach>
				});		
			});
			
			
		</script>
	</body>
</html>
