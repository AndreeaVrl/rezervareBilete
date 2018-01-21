<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
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
    <link href="${pageContext.request.contextPath}/resources/css/rezervareLocuri.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <title>Rezervare Bilete - Locuri</title>
  </head>
<body>

<!-- REPREZENTARE LOCURI AVION -->
<div class="container">
	<hr /> 
	<div class="row">
		<h4>REPREZENTARE LOCURI AVION</h4>
		<div class="col-md-6">
			<table class="table locuri">
				<thead>
					<tr><!-- cap de coloana - A, B, C ... -->
						<th>A</th>
						<th>B</th>
						<th>C</th>
						<th>&nbsp;</th>
						<th>D</th>
						<th>E</th>
						<th>F</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-success"><!-- btn-default in cazul unui loc rezervat deja -->
									<input type="checkbox" autocomplete="off"><!-- nu se mai afiseaza in cazul unui loc rezervat deja -->
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-success">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<td>&nbsp;</td>
						<th>1</th><!-- cap de rand - 1, 2, 3 ... -->
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-warning">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-warning">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<td>&nbsp;</td>
						<th>2</th><!-- cap de rand - 1, 2, 3 ... -->
						<td>&nbsp;</td>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-success">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-success">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-warning">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-warning">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<td>&nbsp;</td>
						<th>3</th><!-- cap de rand - 1, 2, 3 ... -->
						<td>&nbsp;</td>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-warning">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-warning">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-success">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-success">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-success">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<th>4</th><!-- cap de rand - 1, 2, 3 ... -->
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-success">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-success">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-default">
									<span class="glyphicon glyphicon-remove"></span>
								</label>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-info">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-default">
									<span class="glyphicon glyphicon-remove"></span>
								</label>
							</div>
						</td>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-info">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<th>5</th><!-- cap de rand - 1, 2, 3 ... -->
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-info">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-info">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-info">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
					</tr><tr>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-info">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-info">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-info">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<th>6</th><!-- cap de rand - 1, 2, 3 ... -->
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-info">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-info">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
						<td>
							<div data-toggle="buttons">
								<label class="btn btn-info">
									<input type="checkbox" autocomplete="off">
									<span class="glyphicon glyphicon-ok"></span>
								</label>
							</div>
						</td>
					</tr>
					<!-- se continua popularea cu date -->
				</tbody>
			</table>
		</div>
	</div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>