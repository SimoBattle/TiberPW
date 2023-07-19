<!DOCTYPE html>
<html>
	<head>
		<title>Tiber - Controllo Et&agrave</title>
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
        <h3 class="h3 text-center text-dark pb-3">Verifica la tua et&agrave</h3>

		<div class="container p-3 w-25 border border-dark rounded d-flex justify-content-center	 bg-light" >
			<form method="post" action="checkAge" class="row g-3">
					
				<div class=" col-12 mb-3">
					<label for="data" class="form-label">Data di nascita</label><br>
					<input type="date" class="form-control" id="data" name="data" required>
				</div>

				<div class="d-flex justify-content-center">
					<button type="submit" class="btn btn-outline-success rounded">Invia</button>
				</div>
				
			</form>
		</div>> 

    </body>
</html>