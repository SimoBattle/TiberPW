<!DOCTYPE html>
<html>
	<head>
	    <title>Tiber - Birra</title>
        <link rel="icon" type="image/x-icon" href="img/TiberNero.ico">


        <!-- Libs -->
        <link rel="stylesheet" href="frontend/css/bootstrap.min.css">
        <link rel="stylesheet" href="frontend/css/templatemo.css">
        <link rel="stylesheet" href="frontend/css/custom.css">
        <link rel="stylesheet" href="frontend/css/fontawesome.css">
        <script type="text/javascript" src="frontend/js/bootstrap.bundle.min.js"></script>
        

        

        <!-- Online libs -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    </head>

	
	<%@page import="java.util.*"%>
	<%@page import="java.sql.Date"%>
	<%@page import="java.text.*"%>


	<%@page import="jakarta.servlet.*"%>
	<%@page import="jakarta.servlet.http.*"%> 


	<%@page import="beans.User"%>
	<%@page import="beans.Beer"%>
	<%@page import="beans.Review"%>

	<%@taglib uri="WEB-INF/tlds/security.tld" prefix="security"%>
	
	

	<body>

		<security:protection/>

		<section id="top"></section>


		<%
			String id = request.getParameter("id");

			User user = (User) session.getAttribute("User");

			//controllo se utente loggato o data_n salvata

		%>

		<jsp:include page="/birra">
			<jsp:param name="id" value="<%= id %>"/>
		</jsp:include>

		<jsp:include page="/productreviews">
			<jsp:param name="id" value="<%= id %>"/>
		</jsp:include>


		
		
			
				
		<br><br><br><br><br>

		


		<%
			String added = (request.getParameter("cart") != null) ? request.getParameter("cart") : "null";
			

			if(added.equals("1")){
		%>
				<div class="d-flex justify-content-center py-2">
					<div class="alert alert-success" role="alert">
						Birra aggiunta al carrello!
					</div>
				</div>
		<%
			}else if (added.equals("0")){
		%>
				<div class="d-flex justify-content-center py-2">
					<div class="alert alert-danger" role="alert">
						Non &egrave stato possibile aggiungere la birra al carrello!
					</div>
				</div>

		<%
			}
		%>


		<%

			
			Beer beer = (Beer) session.getAttribute("birra");

			if(beer != null){
				String nome = beer.getNome();
				String descrizione = beer.getDescrizione();
				Float gradazione = beer.getGradazione();
				Float prezzo = beer.getPrezzo();
				Integer id_birra = beer.getId_birra();
				Integer id_tipologia = beer.getId_tipologia();
				Integer quantita = beer.getQuantita();
		%>
		
		
		
		<section class="bg-light">
			<div class="container pb-5">
				<div class="row">
					<div class="col-lg-5 mt-5">
						<div class="card mb-3">
							<img class="card-img img-fluid h-100" src='img/<%= nome %>.jpg' alt='Beer Image' onerror="this.src='img/ErrorIMG.png'">
						</div>
					</div>
					
					<div class="col-lg-7 my-5">
						<div class="card">
							<div class="card-body">
								<div class="d-flex justify-content-between pe-3">
								 	<h1 class="h2"><%= nome %></h1>
		<%
				if(id_tipologia == 1){
		%>
									<h3 class="h3 fw-light">Chiara</h3>
		<%							
				}else if (id_tipologia == 2){
		%>
									<h3 class="h3 fw-light">Scura</h3>
		<%
				}else if (id_tipologia == 3){
		%>
									<h3 class="h3 fw-light">IPA</h3>
		<%
				}else if (id_tipologia == 4){
		%>
									<h3 class="h3 fw-light">Weiss</h3>

		<%
			}
		%>
								</div>
								<p class="h3 py-3"><%= prezzo %> &#x20AC</p>
								<h4 class="h4 fw-normal">Descrizione:</h4>
								<p class="pb-5"><%= descrizione %></p>
								<h4 class="h4 fw-normal pt-3">Gradazione:</h4>
								<p class=""><%= gradazione %>&deg</p>							
								<form action="addtocart" method="post">
									<input type="hidden" name="id" value="<%= id_birra %>"/>
									
									<div class="pb-3 w-25">
										<label class="form-label" for="quantita">Quantit&agrave:</label>
		<%
				if(quantita >= 10){
		%>
										<input type='number' name='amount' max='10' min='1' required/>
									</div>
		<%
				}else{
		%>
										<input type='number' name='amount' max='<%= quantita %>' min='1' required/>
									</div>
		<%
				}
					
				if(quantita == 0){
		%>
										<input type="submit" class="btn btn-success btn-lg w-100" value="Aggiungi al carrello" disabled/>
		<%
				}else{
		%>
										<input type="submit" class="btn btn-success btn-lg w-100" value="Aggiungi al carrello"/>
		<%
				}
		%>     
										
									
									
									

									
										
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
		
		<%
			}else{
		%>

		<h1 class="h1 fw-normal text-center">Non è stato possibile recupare il prodotto<i class="bi bi-emoji-frown"></i></h1>

		<%
			}
		%>


		<div class="container py-5">
			<div class="row height d-flex justify-content-center align-items-center">
				<div class="col-md-10">
					<div class="p-3">
						<h2 class="h2 text-success fw-normal">Recensioni</h2>
					</div>
					<div class="mt-2">

		
		<%
		
			List<Review> lista = (List<Review>) session.getAttribute("recensioni");

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			if(!lista.isEmpty()){
				for(Review review : lista){
					String nome = review.getNome();
					String cognome = review.getCognome();
					Integer valutazione = review.getValutazione();
					String recensione = review.getRecensione();
					java.sql.Date data = review.getData();
		%>

						<div class="d-flex flex-row p-3 border border-dark rounded"> <i class="bi bi-person-circle pe-2 pt-1"></i>
							<div class="w-100">
								<div class="d-flex justify-content-between align-items-center pb-3">
									<h4 class="h4 fw-normal"><%= nome %> <%= cognome %></h4>
									<h5 class="h5 f-light"><%= valutazione %> / 5</h5> 
									<h6 class="h6 fw-light"><%= sdf.format(data) %></h6>
								</div>
								<p class="text-justify comment-text mb-0"><%= recensione %> </p>
							</div>
						</div>
					

							
					

		<%
				}
			}else{
		%>
								<h1 class="h1 fw-normal text-center py-5">Non ci sono recensioni per questo prodotto<i class="bi bi-emoji-frown"></i></h1>
		<%
			}
		%>

					<div>
				</div>
			</div>
		</div>

		<div class="d-flex justify-content-end pe-5 pt-5"><a href="#top"><h3 class="text-dark ps-5"><i class="bi bi-arrow-up-circle"></i></h3></a><h4 class="text-dark ps-2 fw-normal">Torna all'inizio</h4></div>



		
		<%
			Cookie[] cookies = request.getCookies();
			String beerHistory = null;
			LinkedList<String> list = null;
			Cookie cookie = null;
        
        	StringBuffer sb = new StringBuffer();  


			for ( int i = 0 ; cookies != null && i < cookies.length; i++ ) {  
				if (cookies[i].getName().equals( "beerHistory" )) {
					beerHistory = cookies[i].getValue();
					cookie = cookies[i];
				}
			}
			if (beerHistory == null) {
				list = new LinkedList<>();
				list.addFirst(id); 

				for (String l: list) {  
					sb.append(l).append( "#" );  
				} 
				String sc1 =  sb.deleteCharAt(sb.length()- 1 ).toString();
				cookie = new Cookie( "beerHistory" , sc1 );
				cookie.setMaxAge(30*24*60*60);
				cookie.setPath("/Tiber");
				response.addCookie(cookie);  
			} else {

				String[] srcArray = beerHistory.split( "\\#" ); 
				List<String> srcList = Arrays.asList(srcArray);
				list = new LinkedList<>(srcList);			
				if (list.contains(id)) {  
					list.remove(id);  
					list.addFirst(id);  
				} else {
					if (list.size() >= 3) {  
						list.removeLast();  
						list.addFirst(id);				
					} else {
						list.addFirst(id); 	
					}
				}


				for (String l: list) {  
					sb.append(l).append( "#" );  
				} 

				String sc2 =  sb.deleteCharAt(sb.length()- 1 ).toString();

				
				cookie.setMaxAge(30*24*60*60);
				cookie.setPath("/Tiber");
				cookie.setValue(sc2);
				response.addCookie(cookie);

				
			}	
		%>

		

	</body>
</html>

		


	