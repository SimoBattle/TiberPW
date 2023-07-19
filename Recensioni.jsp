<!DOCTYPE html>
<html>

    <head>
        <title>Tiber - Recensioni</title>
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

    <%@page import="java.sql.Date"%>
    <%@page import="java.text.*"%>
    <%@page import="java.util.*"%>
    
    <%@page import="beans.Review"%>

    <%@taglib uri="WEB-INF/tlds/security.tld" prefix="security"%>
    

    <body>

        <security:protection/>

        <section id="top"></section>

        <jsp:include page="/ureviews"/>

        <br><br><br><br><br><br>

        
        <h1 class="h1 text-center text-success pb-5">Le tue recensioni</h1>

        <div class="container py-5">
                <div class="row height d-flex justify-content-center align-items-center">
                    <div class="col-md-10">
                        <div class="mt-2">
            <%
                List<Review> lista = (List<Review>) session.getAttribute("recensioni");

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                
                
                if(!lista.isEmpty()){
                    for(Review review : lista){

                        String prodotto = review.getProdotto();
                        String nome = review.getNome();
                        String cognome = review.getCognome();
                        Integer valutazione = review.getValutazione();
                        String recensione = review.getRecensione();
                        java.sql.Date data = review.getData();
                        

            %>
                            <div class="py-3">
                                <div class="d-flex flex-row p-3 border border-dark rounded">
                                    <div class="w-100">
                                        <div class="d-flex justify-content-between align-items-center pb-3">
                                            <h4 class="h4 fw-normal"> <%= prodotto %></h4>
                                            <h5 class="h5 fw-light"><%= valutazione %> / 5</h5>
                                            <h6 class="h6 fw-light"><%= sdf.format(data) %></h6> 
                                        </div>
                                        <p class="text-justify comment-text mb-0"><%= recensione %> </p>
                                    </div>
                                </div>
                            </div>
                            

            <%
                  }
            %>

                            <div class="d-flex justify-content-end pe-5 p-5"><a href="#top"><h3 class="text-dark ps-5"><i class="bi bi-arrow-up-circle"></i></h3></a><h4 class="text-dark ps-2 fw-normal">Torna all'inizio</h4></div>



            <%
                }else{
            %>

                            <h2 class="h2 text-center fw-light pt-5">Non hai nessuna prenotazione <i class="bi bi-emoji-frown"></i></h2>

            <%
                }
            %>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>