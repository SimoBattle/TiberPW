<!DOCTYPE html>
<html>

    <head>
        <title>Tiber - Birre</title>
        <link rel="icon" type="image/x-icon" href="img/TiberNero.ico">


         <!-- Libs -->
        <link rel="stylesheet" href="frontend/css/bootstrap.min.css">
        <link rel="stylesheet" href="frontend/css/templatemo.css">
        <link rel="stylesheet" href="frontend/css/custom.css">
        <link rel="stylesheet" href="frontend/css/fontawesome.css">
		<script src="frontend/js/jquery-1.11.0.min.js" type="text/javascript"></script>
		<script src="frontend/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
		<script src="frontend/js/bootstrap.bundle.min.js" type="text/javascript"></script>
		<script src="frontend/js/templatemo.js" type="text/javascript"></script>
		<script src="frontend/js/custom.js" type="text/javascript"></script>
        

        

        <!-- Online libs -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
        

        
        

    </head>

    <%@page import="java.util.List"%>
    <%@page import="java.sql.*"%>
    <%@page import="java.text.*"%>

    <%@page import="beans.Beer"%>

	<%@taglib uri="WEB-INF/tlds/security.tld" prefix="security"%>
    

    <body>

		<security:protection/>

	

		<%
			Integer type = (request.getParameter("tipo") != null) ? Integer.parseInt(request.getParameter("tipo")) : -1;

			Float price = (request.getParameter("prezzo") != null) ? Float.parseFloat(request.getParameter("prezzo")) : -1;

			Float deg = (request.getParameter("grad") != null) ? Float.parseFloat(request.getParameter("grad")) : -1;

			if(type > 0) {
		%>
				<jsp:include page="/searchbeer">
					<jsp:param name="tipo" value="<%= type %>"/>
				</jsp:include>
		<%
			} else if(price > 0) {
		%>
				<jsp:include page="/searchbeer">
					<jsp:param name="prezzo" value="<%= price %>"/>
				</jsp:include>
		<%
			} else if(deg > 0) {	
		%>
				<jsp:include page="/searchbeer">
					<jsp:param name="grad" value="<%= deg %>"/>
				</jsp:include>
		<%
			} else {
		%>
				<jsp:include page="/beer"></jsp:include>
		<%
			}
		%>


		



        <section id="top"></section>
            
        

        <br><br><br><br><br><br>


       

        <h1 class="h1 text-center text-success pb-2">Birre</h1>

        <div class="container py-3">
			<div class="row">
				<div class="col-lg-3 pe-5">
					<h1 class="h2 pb-4">Ricerca</h1>
					<ul class="list-unstyled templatemo-accordion">
						<li class="pb-3">
							<a class="collapsed d-flex justify-content-between h3 text-decoration-none" href="">
								Tipologia
								<i class="bi bi-arrow-down-circle"></i>
							</a>
							<ul class="collapse show list-unstyled pl-3">
								<li><a class="text-decoration-none" href="Birre?tipo=1">Chiara</a></li>
								<li><a class="text-decoration-none" href="Birre?tipo=2">Scura</a></li>
								<li><a class="text-decoration-none" href="Birre?tipo=3">IPA</a></li>
								<li><a class="text-decoration-none" href="Birre?tipo=4">Weiss</a></li>
							</ul>
						</li>
						<li class="pb-3">
							<a class="collapsed d-flex justify-content-between h3 text-decoration-none" href="">
								Prezzo
								<i class="bi bi-arrow-down-circle"></i>
							</a>
							<ul id="collapseTwo" class="collapse list-unstyled pl-3">
								<li><a class="text-decoration-none" href="Birre?prezzo=5">Massimo 5&#x20AC</a></li>
								<li><a class="text-decoration-none" href="Birre?prezzo=6">Massimo 6&#x20AC</a></li>
								<li><a class="text-decoration-none" href="Birre?prezzo=7">Massimo 7&#x20AC</a></li>
							</ul>
						</li>
						<li class="pb-3">
							<a class="collapsed d-flex justify-content-between h3 text-decoration-none" href="">
								Gradazione
								<i class="bi bi-arrow-down-circle"></i>
							</a>
							<ul id="collapseThree" class="collapse list-unstyled pl-3">
								<li><a class="text-decoration-none" href="Birre?grad=5.5">Massimo 5,5&deg</a></li>
								<li><a class="text-decoration-none" href="Birre?grad=7.5">Massimo 7,5&deg</a></li>
								<li><a class="text-decoration-none" href="Birre?grad=9.5">Massimo 9,5&deg</a></li>
							</ul>
						</li>
					</ul>
            	</div>
				<div class="col-lg-9">
					<div class="row">

                
					<%
						List<Beer> lista = (List<Beer>) session.getAttribute("birre");
						
					
						
						
						if(!lista.isEmpty()){
							for(Beer beer : lista){

								String nome = beer.getNome();
								String descrizione = beer.getDescrizione();
								Float gradazione = beer.getGradazione();
								Float prezzo = beer.getPrezzo();
								Integer id_birra = beer.getId_birra();
								Integer id_tipologia = beer.getId_tipologia();
								Integer quantita = beer.getQuantita();
					%>

								<div class="col-md-4">
									<div class="card mb-4 product-wap rounded-0">
										<div class="card rounded-0">
											<a href="Birra?id=<%= id_birra %>"> <img class="card-img rounded-0 img-fluid" src="img/<%= nome %>.jpg" onerror="this.src='img/ErrorIMG.png'"> </a>
										</div>
										<div class="card-body">
											<div class="d-flex justify-content-between">
												<a href="Birra?id=<%= id_birra %>" class="h3 text-decoration-none"><%= nome %></a>

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
											<br>
											<div class="d-flex justify-content-between">
												<p class="text-center mb-0">Prezzo:</p>
												<p class="text-center mb-0">Gradazione:</p>
											</div>
											<div class="d-flex justify-content-between">
												<p class="text-center mb-0"><%= prezzo %> &#x20AC</p>
												<p class="text-center mb-0"><%= gradazione %>&deg</p>
											</div>
										</div>
									</div>
								</div>
								
					<%
							}
						
						}else{
					%>
					
							<h2 class="h2 text-center fw-light pt-5">Non ci sono birre con questi requisiti<i class="bi bi-emoji-frown"></i></h2>

					<%		
						}
					%>

							
					</div>	
				</div>
			</div>
        </div>

		<div class="d-flex justify-content-end pe-5 p-5"><a href="#top"><h3 class="text-dark ps-5"><i class="bi bi-arrow-up-circle"></i></h3></a><h4 class="text-dark ps-2 fw-normal">Torna all'inizio</h4></div>

    </body>
</html>