<!DOCTYPE html>
<html>
    <head>
        <title>Tiber - Prenotazioni</title>
        <link rel="icon" type="image/x-icon" href="img/TiberNero.ico">


         <!-- Libs -->
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
    <%@page import="java.sql.Date"%>
    <%@page import="java.text.*"%>
    <%@page import="beans.Booking"%>

    <%@taglib uri="WEB-INF/tlds/security.tld" prefix="security"%>

    <body>


        <security:protection/>

        <section id="top"></section>

        <jsp:include page="bookings"/>

        <br><br><br><br><br><br><br>

        <div class="container py-2">
            <h1 class="h1 text-center text-success pb-5">Eventi prenotati</h1>

             <%
                String error = (request.getParameter("booking") != null) ? request.getParameter("booking") : "null";
                

                if(error.equals("success")){
            %>
                    <div class="d-flex justify-content-center p-3">
                        <div class="alert alert-success" role="alert">
                            Prenotazione effettuata con successo!
                        </div>
				    </div>
            <%
                }

            %>

    

            <%
                List<Booking> lista = (List<Booking>) session.getAttribute("prenotazioni");
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                
                
                if(!lista.isEmpty()){
                    for(Booking booking : lista){

                        String evento = booking.getEvento();
                        Integer posti = booking.getPrenotati();
                        Date data = booking.getData();
            %>
                        
                        <div class="d-flex justify-content-center">    
                            <article class='postcard p-5 border border-dark w-75'>
                                
                                <div class='postcard__text'>
                                    <h1 class='h1 text-success'> <%= evento %> </h1>
                        
                                    
                                    <div class='d-flex justify-content-between mt-5'>
                                        <h4 class='h4 text-dark fw-normal'><i class='bi bi-calendar-event'></i> Data:  <%= sdf.format(data) %> </h4>
                                        <h4 class='h4 text-dark fw-normal'><i class='bi bi-people'></i> Posti prenotati:  <%= posti %> </h4>

                                    </div>
                                </div>
                            </article>
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
    </body>
</html>

