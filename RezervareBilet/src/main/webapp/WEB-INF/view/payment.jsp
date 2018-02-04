<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
		<script src="${pageContext.request.contextPath}/resources/js/validation.js"></script>
    </head>
    <script>
    $(document).ready(function(){
    	$('#cardDiv').show();
        $('#paypalDiv').hide();
        $('#opDiv').hide();
       
        $('#Op').click(function(){
        	$('#cardDiv').hide();
            $('#paypalDiv').hide();
            $('#opDiv').show();
        });
        $('#Paypal').click(function(){
        	$('#cardDiv').hide();
            $('#paypalDiv').show();
            $('#opDiv').hide();
        });
        $('#Card').click(function(){
        	$('#cardDiv').show();
            $('#paypalDiv').hide();
            $('#opDiv').hide();
        });
        $('form').submit(function(){
        	if($('#Card').data('clicked')) {
        		var luna=$('#cardLuna').val();
        		var an=$('#cardLuna').val();
				return (!isEmpty('tipCard','Va rog selectati tipul de card!') &&
	  		    		 !isEmpty('cardNumar','Va rog completati numarul cardului!') &&
	  		    		 !isEmpty('cardLuna','Va rog completati luna in care expira cardul!') &&
	  		    		 !isEmpty('cardAn','Va rog completati anul in care expira cardul!') &&
	  		    		 !lunaInvalida(luna,'Nu puteti utiliza acest card deoarece este expirat!') &&
	  		    		 !anInvalid(an,'Nu puteti utiliza acest card deoarece este expirat!') &&
	  		    		 !isEmpty('cardDetinator','Va rog completati numele persoanei care detine acest card!'))
        	}
        	if($('#Op').data('clicked')) {
   				return (!isEmpty('opNumar','Va rog completati pentru Op numarul!'))
           	}
        	if($('#Paypal').data('clicked')) {
    			 return (!isEmpty('Paypal','Va rog completati contul Paypal!'))
            }
   		});
    });
    </script>
    <body>
        <div class="container">
        <c:if test="${not empty succes}">
				<div class="close" data-dismiss="alert" aria-label="close">×</div>
				<div class="alert alert-success"><strong>Succes!</strong> ${succes}</div>
			<br>
		</c:if>
		
		<c:if test="${not empty exceptie}">
				<div class="close" data-dismiss="alert" aria-label="close">×</div>
				<div class="alert alert-danger"><strong>Eroare!</strong>${exceptie}</div>
			<br>
		</c:if>
            <div class="row">
                <div class="col-xs-12 col-md-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                Plata
                            </h3>
                            <div>
                           		<input type="button" id="Card" value="Card" style="margine: 5px;"> 
                           		<input type="button" id="Op" value="Op" style="margine: 5px;">
                           		<input type="button" id="Paypal" value="Paypal" style="margine: 5px;">
                        	</div>
                        </div>
                        <form:form action="payment" modelAttribute="modalitatePlata">
	                        <div class="row">
								<div class="col-xs-12 col-md-12 pull-left">
	                        		<div class="form-group">
		                                <label for="cardNumber" class="col-xs-4 col-md-4">Suma plata=</label>
		                                <div class="input-group col-xs-8 col-md-8">
		                                    <input type="text" class="form-control" id="sumaPlata" disabled="disabled" value="${rezultatTotal}"/>
		                                </div>
		                            </div>
		                         </div>
                            </div>
	                        <div class="panel-body" id="cardDiv">
	                       		<div class="row">
									<div class="col-xs-5 col-md-5 pull-left">
	                                    <div class="form-group">
	                                        <label for="cvCode">TIP CARD</label>
	                                         <div class="input-group">
		                                         <div>
			                                        <form:select path = "tipCard">
			                                        	<form:options items="${tipuriCarduri}" itemValue="id" itemLabel="denumire"/>
													</form:select>
								                  </div>
							                 </div>
	                                    </div>
	                                </div>	
	                                <div class="col-xs-7 col-md-7 pull-right">                                
			                            <div class="form-group">
			                                <label for="cardNumber">NUMAR CARD</label>
			                                <div class="input-group">
			                                	<div>
				                                    <form:input class="form-control" path="cardNumar" placeholder="Numar card" maxlength="13" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
				                                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
				                                </div>
			                                </div>
			                            </div>
			                        </div>
			                    </div>
	                            <div class="row">
	                                <div class="col-xs-7 col-md-7">
	                                    <div class="form-group">
	                                        <label for="expityMonth">DATA EXPIRARE</label>
	                                        <div class="col-xs-6 col-lg-6 pl-ziro">
	                                            <form:input class="form-control" path="cardLuna"  placeholder="MM" maxlength="2" onkeypress='return event.charCode >= 48 && event.charCode <= 57' />
	                                        </div>
	                                        <div class="col-xs-6 col-lg-6 pl-ziro">
	                                            <form:input class="form-control" path="cardAn" placeholder="YYYY" maxlength="4" onkeypress='return event.charCode >= 48 && event.charCode <= 57' />
	                                        </div>
	                                    </div>
	                                </div>
	                                <div class="col-xs-5 col-md-5 pull-right">
	                                    <div class="form-group">
	                                        <label for="cvCode">DETINATOR</label>
	                                       	<form:input class="form-control" path="cardDetinator" placeholder="DETINATOR" />
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
                            <div class="panel-body" id="opDiv">
	                            <div class="row">
									<div class="col-xs-12 col-md-12">
			                            <div class="form-group">
			                                <label for="cardNumber">NUMAR OP</label>
			                                <div class="input-group">
			                                    <form:input type="numbar" class="form-control" path="opNumar" placeholder="Numar OP" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
			                                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
			                                </div>
			                            </div>
			                        </div>
			                    </div>
		                    </div>
	                        <div class="panel-body" id="paypalDiv">
		                        <div class="row">
									<div class="col-xs-12 col-md-12">
			                            <div class="form-group">
			                                <label for="paypal_cont">Cont paypal</label>
			                                <div class="input-group">
			                                    <form:input class="form-control" path="paypalCont" placeholder="Valid Card Number" />
			                                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
			                                </div>
			                            </div>
		                            </div>
		                        </div>
	                        </div>                       
	                        <form:hidden path=""/>
	                        <input type="submit" value="Plateste" />
	                     </form:form>
                    </div>
                    <br/>
                </div>
            </div>
        </div>
    </body>
</html>