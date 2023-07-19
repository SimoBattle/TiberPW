<!DOCTYPE html>
<html>

    <head>
        <title>Tiber - Eventi</title>
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

    <%@page import="java.util.List"%>
    <%@page import="java.sql.*"%>
    <%@page import="java.text.*"%>
    <%@page import="beans.Event"%>
    <%@page import="beans.User"%>
    
    

    <body>

        <jsp:include page="/events"/>
            
        <section id="top"></section>

        <br><br><br><br><br><br>


        

       

        

        <div class="container py-2">
            <h1 class="h1 text-center text-success pb-5">Eventi disponibili</h1>

            <%

                User user = (User) session.getAttribute("User");

                if(user == null){
            %>

                     <h6 class="h6 text-center fw-light">Per prenotare &egrave necessario aver effettuato l'accesso</h6>

            <%
                }
            %>


            
            



            <%
                String error = (request.getParameter("booking") != null) ? request.getParameter("booking") : "null";
                String evento = (request.getParameter("event") != null) ? request.getParameter("event") : "null";

                if(error.equals("failed")){
            %>
                    <div class="d-flex justify-content-center pt-3">
                        <div class="alert alert-danger" role="alert">
                            Non &egrave stato possibile effettuare la prenotazione!
                        </div>
				    </div>
            <%
                }

                if(evento.equals("1")){
            %>

                    <div class="d-flex justify-content-center pt-3">
                        <div class="alert alert-success" role="alert">
                            Evento inserito correttamente!
                        </div>
				    </div>

            <%
                }
            %>

        



            <%
                List<Event> lista = (List<Event>) session.getAttribute("eventi");
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                
                
                if(!lista.isEmpty()){
                    for(Event event : lista){

                        String nome = event.getNome();
                        String descrizione = event.getDescrizione();
                        Integer posti = event.getPosti();
                        Date data = event.getData();
            %>
                        <section id='<%= nome.replaceAll("\\s", "") %>'>
                            <br><br>
                        </section>
                    
                        <article class='postcard p-5 border border-dark'>
                            <img class='postcard__img text-dark rounded-3 text-center' src='img/<%= nome.replaceAll("\\s", "") %>.jpg' alt='Event Image' onerror="this.src='img/ErrorIMG.png'"/>

                            <div class='postcard__text'>
                                <h1 class='h1 text-success'> <%= nome %> </h1>
                    
                                <div class='postcard__preview-txt'>
                                    <h3 class='h3 text-dark fw-normal'> <%= descrizione %> </h3>
                                </div>
                    
                                <div class='d-flex justify-content-between mt-5'>
                                    <h4 class='h4 text-dark fw-normal'><i class='bi bi-calendar-event'></i> Data:  <%= sdf.format(data) %> </h4>
                                    <h4 class='h4 text-dark fw-normal'><i class='bi bi-people'></i> Posti disponibili:  <%= posti %> </h4>

                                    <!-- Form non presente se utente non loggato  -->

                                    <form method='post' action='prenota'>
                                        <input type="hidden" name="nome" value="<%= nome %>"/>
                        
                        
            <%
                        if(posti >= 10){
            %>
                                        <input type='number' name='posti' size='3' max='10' min='1' required/>
            <%
                        }else{
            %>
                                        <input type='number' name='posti' size='3' max='<%= posti %>' min='1' required/>
            <%
                        }
                        
                        if(posti == 0 || user == null){
            %>
                                        <input class='btn btn-outline-success rounded-pill ' type='submit' disabled value='Prenota'/>
            <%
                        }else{
            %>
                                        <input class='btn btn-outline-success rounded-pill' type='submit' value='Prenota'/>
            <%
                        }
            %>     
                                    </form>
                                </div>
                            </div>
                        </article>
            <%
                    }
                
                }else{
            %>

                    <h2 class="h2 text-center fw-light pt-5">Non ci sono eventi disponibili <i class="bi bi-emoji-frown"></i></h2>
            

            <%
                }
            %>


        </div>

        <div class="d-flex justify-content-end pe-5 py-5"><a href="#top"><h3 class="text-dark ps-5"><i class="bi bi-arrow-up-circle"></i></h3></a><h4 class="text-dark ps-2 fw-normal">Torna all'inizio</h4></div>


    </body>
</html>