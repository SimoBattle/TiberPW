<!DOCTYPE html>
<html>

  <head>
    <title>Tiber - Carrello</title>
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
  <%@page import="beans.User"%>

  <%@taglib uri="WEB-INF/tlds/security.tld" prefix="security"%>
    

  <body>

    <security:protection/>

    

    <jsp:include page="/getcart"/>

    <br><br><br><br><br><br>


    <%

      String buy = (request.getParameter("buy") != null) ? request.getParameter("buy") : "null";


      if(buy.equals("1")){
    %>
        <div class="d-flex justify-content-center py-2">
					<div class="alert alert-success" role="alert">
						Acquisto effettuato correttamente!
					</div>
				</div>
    <%
      }else if (buy.equals("0")){
    %>

        <div class="d-flex justify-content-center py-2">
					<div class="alert alert-danger" role="alert">
						Non &egrave stato possibile effettuare l'acquisto!
					</div>
				</div>


    <%
      }
    %>

      

    <section class="pt-3">
      <div class="container">
        <div class="row d-flex justify-content-center align-items-center">
          <div class="col">
            <h2 class="h2 fw-normal text-success">Carrello</h2>

    
    <%

        List<Cart> lista = (List<Cart>) session.getAttribute("cart");

        Float total = (Float) session.getAttribute("total");

        

        if(!lista.isEmpty()){
            for(Cart cart : lista){
                String prodotto = cart.getProdotto();
                Integer quantita = cart.getQuantita();
                Integer id = cart.getId();
                Float prezzo = cart.getPrezzo();
                Float totale = cart.getTotale();

    %>

            <div class="card mb-4 border border-dark">
              <div class="card-body p-4"> 
                <div class="row align-items-center">
                  <div class="col-md-2">
                    <img src="img/<%= prodotto.replaceAll("\\s", "") %>.jpg" class="img-fluid card-img" alt="Beer Image">
                  </div>
                  <div class="col-md-2 d-flex justify-content-center">
                    <div>
                      <p class="small text-muted mb-4 pb-2">Birra</p>
                      <p class="lead fw-normal mb-0"><%= prodotto %></p>
                    </div>
                  </div>
                  <div class="col-md-2 d-flex justify-content-center">
                    <div>
                      <p class="small text-muted mb-4 pb-2">Quantit&agrave</p>
                      <p class="lead fw-normal mb-0"><%= quantita %></p>
                    </div>
                  </div>
                  <div class="col-md-2 d-flex justify-content-center">
                    <div>
                      <p class="small text-muted mb-4 pb-2">Prezzo</p>
                      <p class="lead fw-normal mb-0"><%= prezzo %> &#x20AC</p>
                    </div>
                  </div>
                  <div class="col-md-2 d-flex justify-content-center">
                    <div>
                      <p class="small text-muted mb-4 pb-2">Totale</p>
                      <p class="lead fw-normal mb-0"><%= totale %> &#x20AC</p>
                    </div>
                  </div>
                  <div class="col-md-2 d-flex justify-content-center">
                    <div class="pt-3 pe-3">
                      <a href="cartremove?id=<%= id %>" class="text-danger"><h3><i class="bi bi-trash"></i></h3></a>
                    </div>
                  </div>
                </div>
              </div>
            </div>                           

    <%
            }

    %>

            <div class="card mb-5 border border-dark">
              <div class="card-body p-4">
                <div class="d-flex justify-content-between pe-5">
                  <h4 class="mb-0 me-5 d-flex align-items-center fw-normal">
                    Totale dell'ordine: <%= total %>&#x20AC
                  </h4>
                  <a class="btn btn-lg btn-outline-success rounded" href="buycart" role="button">Acquista</a>
                </div>
              </div>
            </div>


    <%
        }else{
    %>
            <h2 class="h2 text-center fw-light pt-5">Non hai nessun prodotto nel carrello <i class="bi bi-emoji-frown"></i></h2>
    <%
        }



    %>

            </div>
          </div>
        </div>
      </section>
  </body>
</html>