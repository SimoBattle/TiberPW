<!DOCTYPE html>
<html>
	<head>
		<title>Tiber - Accedi</title>
        <link rel="icon" type="image/x-icon" href="img/TiberNero.ico">


		<!-- Libs -->
		<link rel="stylesheet" href="frontend/css/bootstrap.min.css">
		<link rel="stylesheet" href="frontend/css/fontawesome.css">
        <script type="text/javascript" src="frontend/js/bootstrap.bundle.min.js"></script>


		<!-- Online libs -->
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
	</head>
	<body>

		<br><br>

		
		<br><br>

		<h3 class="h3 text-center text-dark pb-3">Accedi</h3>

		<%
			String error = (String) request.getAttribute("error");
			String registration = (String) request.getAttribute("registration");

			if(registration != null){
		%>
				<div class="d-flex justify-content-center">
					<div class="alert alert-success" role="alert">
						Registrazione effettuata con successo, ora effettua l'accesso! 
					</div>
				</div>
		<%
			}

			if(error != null && error == "email"){
		%>
				<div class="d-flex justify-content-center">
					<div class="alert alert-danger" role="alert">
						Questa e-mail non &egrave associata a nessun account!
					</div>
				</div>
				
			
		<%
			}else if (error != null && error == "password"){
		%>
				<div class="d-flex justify-content-center">
					<div class="alert alert-danger" role="alert">
						La password &egrave errata!
					</div>
				</div>
		<%
			}
		%>

	

		<div class="container p-3 w-25 border border-dark rounded d-flex justify-content-center	bg-light" >
			<form method="post" action="login" class="row g-3">
					
				<div class=" col-12 mb-3">
					<label for="email" class="form-label">Indirizzo e-mail</label>
					<input type="email" class="form-control" id="email" name="email" required>
				</div>


				<div class="col-12 mb-3">
					<label for="password" class="form-label">Password</label>
					<input type="password" class="form-control" id="password" name="password" required>
				</div>
			
				<div class="d-flex justify-content-start">
					<button type="submit" class="btn btn-outline-success rounded">Invia</button>
				</div>
				
			</form>
		</div>

		
		
		
	</body>
</html>