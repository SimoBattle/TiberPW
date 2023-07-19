<!DOCTYPE html>
<html>
	<head>
		<title>Tiber - Inserisci Evento</title>
        <link rel="icon" type="image/x-icon" href="img/TiberNero.ico">


		<!-- Libs -->
		<link rel="stylesheet" href="frontend/css/bootstrap.min.css">
        <link rel="stylesheet" href="frontend/css/custom.css">
		<link rel="stylesheet" href="frontend/css/fontawesome.css">
        <script type="text/javascript" src="frontend/js/bootstrap.bundle.min.js"></script>


		<!-- Online libs -->
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
	</head>

	<%@taglib uri="WEB-INF/tlds/security.tld" prefix="security"%>
	
	<body>

		<security:admin/>


        <br><br><br>

        <h1 class="h1 text-center text-success fw-light pb-3">Inserisci un nuovo evento</h1>



        <div class="container p-3 w-25 border border-dark rounded d-flex justify-content-center	bg-light">
			<form method="post" action="upload" class="row g-3" id="newevent">
					
				<div class="col-12 mb-3">
					<label for="nome" class="form-label">Nome evento</label>
					<input type="text" class="form-control" id="nome" name="nome" required>
				</div>

                <div class="col-12 mb-3">
                    <label for="descrizione">Descrizione</label><br>
					<textarea form="newevent" class="border border-gray mt-2" id="descrizione" name="descrizione" rows="4" cols="40"></textarea>
				</div>

                <div class="row pt-2">
                    <div class="col-md-6 mb-3">
                        <label for="data" class="form-label">Data evento</label>
                        <input type="date" class="form-control" id="data" name="data" required>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="posti" class="form-label">Posti evento</label>
                        <input type="number" class="form-control" min="1" id="posti" name="posti" required>
                    </div>
                </div>


			
				<div class="d-flex justify-content-end pe-3">
					<button type="submit" class="btn btn-outline-success rounded">Inserisci</button>
				</div>
				
			</form>
		</div>


    </body>
</html>