<!DOCTYPE html>
<html>

    <head>
        <title>Tiber - Home</title>
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
    <%@page import="beans.Event"%>
    <%@page import="beans.Beer"%>
    <%@page import="beans.User"%>
    



    <body>

        <section id="top"></section>

        <br><br><br><br><br><br>    
     

        <div class="container d-flex pt-5">
            <div class="row p-5">
                <div class="mx-auto col-md-8 col-lg-6 order-lg-last">
                    <img class="img-fluid" src="img/TiberNero.png" width="400" heigth="500" alt="TiberImage">
                </div>
                <div class="col-lg-6 mb-0 d-flex align-items-center">
                    <div class="text-align-left align-self-center text-end">
                        <h1 class="h1 text-success"><b>Birrificio Tiber</b></h1>
                        
                        <h4 class="h4 fw-light pt-3">
                            Tiber nasce come una piccola azienda agricola a gestione familiare nell' Agosto del 2019, 
                            diventa poi ritrovo per amici e famiglie dove poter guastare diverse tipologie di birre tutte di nostra produzione.<br><br>
                            Ed oggi si evolve ancora dando una nuova possibilit&agrave di interazione e acquisto ai suoi clienti 
                        </h4>
                    </div>
                </div>
            </div>
        </div>   

        

        <jsp:include page="/events"/>

        <br>

        <section class='py-5'>
            <div class='container'>
                <div class='row'>
                    <div class='col-6'>
                        <h2 class='h2 mb-3 text-success fw-normal'>I prossimi eventi</h2>
                    </div>
                    <div class='col-12'>
                        <div class='row'>
        <%
            List<Event> lista = (List<Event>) session.getAttribute("eventi");

            Integer count = 0;
            

            if(lista != null){
                for(Event event : lista){

                    String nome = event.getNome();
                    String descrizione = event.getDescrizione();

                    if(count == 3){
                        break;
                    }
                    else{
                        count++;
        %>
                            <div class='col-md-4 mb-3'>
                                <div class='card'>
                                    <a href='Eventi#<%= nome.replaceAll("\\s", "") %>'>
                                        <img class='img-fluid rounded-top card-img' alt='Event Image' src='img/<%= nome.replaceAll("\\s", "") %>.jpg' onerror="this.src='img/ErrorIMG.png'">
                                    </a>
                                    <div class='card-body rounded-bottom'>
                                        <h4 class='card-title text-success'> <%= nome %> </h4>
                                        <p class='card-text'> <%= descrizione %> </p>
                                     </div>
                                </div>
                            </div>
        <%
                    }
                }

            }
            else if( lista == null){
        %>
                <h3 class='h3 text-center'>Errore durante il recupero degli eventi</h3>
        <%
            }
        %>       
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <jsp:include page="/readcookie"/>
        
                

        <%
			List<Beer> cookie = (List<Beer>) session.getAttribute("beerCookie");

            User user = (User) session.getAttribute("User");
            Boolean adult = (Boolean) session.getAttribute("adult");

						
							
            if(cookie != null){
                if((user != null && user.getMaggiorenne() == true) | (adult != null && adult == true)){

                
            
        %>

        <section class='py-5'>
            <div class='container'>
                <div class='row'>
                    <div class='col-6'>
                        <h2 class='h2 mb-3 text-success fw-normal'>Ultime birre visitate</h2>
                    </div>
                    <div class='col-12'>
                        <div class='row'>



        <%
                    for(Beer beer : cookie){

                        String nome = beer.getNome();
                        String descrizione = beer.getDescrizione();
                        Float gradazione = beer.getGradazione();
                        Float prezzo = beer.getPrezzo();
                        Integer id_birra = beer.getId_birra();
                        Integer id_tipologia = beer.getId_tipologia();
                        Integer quantita = beer.getQuantita();
        %>


        						
                            <div class='col-md-4 mb-3'>
                                <div class="card mb-4 product-wap rounded-0">
                                    <div class="card rounded-0">
                                        <a href="Birra?id=<%= id_birra %>"> <img class="card-img rounded-0 img-fluid" src="img/<%= nome %>.jpg" onerror="this.src='img/ErrorIMG.png'"/> </a>
                                    </div>
                                    <div class="card-body">
                                        <a href="Birra?id=<%= id_birra %>" class="h3 text-decoration-none"><%= nome %></a>
                                        <br><br>
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
        %>

                        </div>
                    </div>
                </div>
            </div>
        </section>
					


        <%
                }
            }
        %>


        <div class="d-flex justify-content-end pe-5 pt-5"><a href="#top"><h3 class="text-dark ps-5"><i class="bi bi-arrow-up-circle"></i></h3></a><h4 class="text-dark ps-2 fw-normal">Torna all'inizio</h4></div>

        <br><br>

        
          







    </body>





</html>