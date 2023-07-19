<!DOCTYPE html>
<html>

    <head>
        <title>Tiber - Acquisti</title>
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
    <%@page import="beans.Cart"%>

    <%@taglib uri="WEB-INF/tlds/security.tld" prefix="security"%>
    

    <body>

        <security:protection/>


        <jsp:include page="/getbuyed"/>


        <section id="top"></section>

        <br><br><br><br><br><br>


        <div class="container py-2">
            <h1 class="h1 text-center text-success pb-4">Birre acquistate</h1>


        <%

            String review = (request.getParameter("review") != null) ? request.getParameter("review") : "null";

            if(review.equals("1")){
        %>

                <div class="d-flex justify-content-center pb-3">
                    <div class="alert alert-success" role="alert">
                        Recensione inserita correttamente!
                    </div>
                </div>

        <%
            }
        
        

            List<Cart> lista = (List<Cart>) session.getAttribute("buyed");


            

            if(!lista.isEmpty()){
                for(Cart cart : lista){
                    String prodotto = cart.getProdotto();
                    Integer id = cart.getId();
                    

        %>

                            <article class='postcard p-5 border border-dark'>
                                <div>
                                    <img class='postcard__img text-dark rounded-3 text-center' src='img/<%= prodotto.replaceAll("\\s", "") %>.jpg' alt='Event Image' onerror="this.src='img/ErrorIMG.png'"/>
                                </div>
                                <div class='postcard__text'>
                                    <div class="d-flex justify-content-between pt-5">
                                        <h1 class='h1 text-dark'> <%= prodotto %> </h1>
                                        <a href="ScriviRecensione?id=<%= id %>&n=<%= prodotto %>" role="button" class="btn btn-lg btn-success rounded-pill pt-3">Scrivi Recensione</a>
                                    </div>
                                <div>
                            </article>



 

         <%      
                }

        %>
                            <div class="d-flex justify-content-end pe-5 p-5"><a href="#top"><h3 class="text-dark ps-5"><i class="bi bi-arrow-up-circle"></i></h3></a><h4 class="text-dark ps-2 fw-normal">Torna all'inizio</h4></div>


        <%
            }else{
         %>
                <h2 class="h2 text-center fw-light pt-5">Non hai acquistato nessun prodotto ancora <i class="bi bi-emoji-frown"></i></h2>
        <%
            }

        %>
 
        </div>

        

    </body>
</html>