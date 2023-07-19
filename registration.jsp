
<!DOCTYPE html>
<html>
	<head>
		<title>Tiber - Registarazione</title>
        <link rel="icon" type="image/x-icon" href="img/TiberNero.ico">


		<link rel="stylesheet" href="frontend/css/bootstrap.min.css">
        <link rel="stylesheet" href="frontend/css/fontawesome.css">
        <script type="text/javascript" src="frontend/js/bootstrap.bundle.min.js"></script>
        

        

        <!-- Online libs -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
	</head>
	<body>
		


		<br><br><br><br><br>

		<h3 class="h3 text-center text-dark pb-3">Registrazione</h3>

		<%
			String registration = (String) request.getAttribute("registration");

			if(registration != null && registration == "failed"){
		%>
				<div class="d-flex justify-content-center">
					<div class="alert alert-danger" role="alert">
						Non &egrave stato possibile effettuare la registrazione!
					</div>
				</div>
		<%
			}

		%>
		

		

	

		<div class="container pt-3 ps-3 pe-3 pb-2 w-25 border border-dark rounded d-flex justify-content-center	 bg-light" >
			<form method="post" action="register" class="row g-3">
					
				<div class="col-md-6 mb-3">
					<label for="nome" class="form-label">Nome</label>
					<input type="text" class="form-control" id="nome" name="name" required>
				</div>
				<div class="col-md-6 mb-3">
					<label for="conome" class="form-label">Cognome</label>
					<input type="text" class="form-control" id="cognome" name="lastname" required>
				</div>
				
				<div class=" col-12 mb-3">
					<label for="email" class="form-label">Indirizzo e-mail</label>
					<input type="email" class="form-control" id="email" name="email" required>
				</div>


				<div class="col-12 mb-3">
					<label for="password" class="form-label">Password</label>
					<input type="password" class="form-control" id="password" name="password" required>
				</div>
				
				<div class="d-flex justify-content-between">
					<div class="mb-3">
						<label class="form-label" for="date">Data di nascita</label><br>
						<input type="date" class="form-control" id="date" name="data" required>
					</div>
					
					<div class="pb-1 d-flex h-50 mt-4">
						<button type="submit" class="btn btn-outline-success rounded">Invia</button>
					</div>
				</div>
			</form>
		</div>
		
					
					
				
				
				
			
	</body>
</html>