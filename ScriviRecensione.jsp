<!DOCTYPE html>
<html>
	<head>
        <title>Tiber - Scrivi Recensione</title>
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

        <security:protection/>

	<% 
		String id = request.getParameter("id");
        String nome = request.getParameter("n");
	%>
        <br><br><br><br>

    

        <h1 class="h1 text-center text-success fw-light pb-3">Scrivi la recensione per : <%= nome %></h1>


    <%

        String review = (request.getParameter("review") != null) ? request.getParameter("review") : "null";

        if(review.equals("0")){
    %>

        <div class="d-flex justify-content-center pt-3">
            <div class="alert alert-danger" role="alert">
                &Egrave stato impossibile inserire la recensione!
            </div>
        </div>

    <%
        }
    %>

        <div class="container p-3 w-25 border border-dark rounded d-flex justify-content-center bg-light" >
            <form method="post" action="newreview" class="row g-3" id="review">
                

                <div class="col-12 mb-3">
                    <label for="recensione" class="form-label">Valutazione: </label>			
                    <select name="valutazione" class="form-control" name="valutazione" required>
                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>

                


                <div class="col-12 mb-3">
                    <label for="recensione">Recensione</label><br>
					<textarea form="review" class="border border-gray mt-2" name="recensione" id="recensione" rows="4" cols="40" placeholder="Scrivi qui la tua recensione"></textarea>
                </div>

                

                <input type="hidden" name="id" value="<%= id %>">
                <input type="hidden" name="name" value="<%= nome %>">

                
                
                <div class="d-flex justify-content-start">
                    <button type="submit" class="btn btn-outline-success rounded">Invia</button>
                </div>
                    
            </form>

        </div>
	</body>
</html>