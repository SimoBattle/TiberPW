package filters;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.annotation.WebFilter;
import java.io.*;
import beans.User;


@WebFilter(urlPatterns = {"/Home", "/Eventi", "/Prenotazioni", "/Birre", "/Birra", "/Recensioni", "/Carrello", "/About", "/Acquisti"})
public class Navbar implements Filter {

    public void init() {

    }

    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("User");

        Boolean maggiorenne, admin;
        




        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<nav class='navbar navbar-expand-lg navbar-light bg-white shadow fixed-top'>");
        out.println("<div class='container d-flex justify-content-between align-items-center'>");

        out.println("<a class='justify-contennt-lg-start navbar-brand logo h1' href='Home'>");
        out.println("<img src='img/TiberNero.png'  width='60' height='80' class='d-inline-block align-text-top'>");
        out.println("</a>");
        out.println("<h4 class='h4 p-3 text-success'><b>Tiber</b></h4>");
        

        out.println("<div class='align-self-center collapse navbar-collapse flex-fill  d-lg-flex justify-content-lg-between' id='templatemo_main_nav'>");
        out.println("<div class='flex-fill'>");
        out.println("<ul class='nav navbar-nav d-flex justify-content-between mx-lg-auto'>");

        out.println("<li class='nav-item'>");
        out.println("<a class='nav-link' href='Home'>Home</a>");
        out.println("</li>");

        if( user != null){

            maggiorenne = user.getMaggiorenne();
            if(maggiorenne){
                out.println("<li class='nav-item'>");
                out.println("<a class='nav-link' href='Birre'>Birre</a>");
                out.println("</li>");

            }else{
                out.println("<li class='nav-item'>");
                out.println("<a class='nav-link disabled' href=''>Birre</a>");
                out.println("</li>");

            }

        }else{

            Boolean adult = (Boolean) session.getAttribute("adult");

            if(adult != null){
                if(adult){

                    out.println("<li class='nav-item'>");
                    out.println("<a class='nav-link' href='Birre'>Birre</a>");
                    out.println("</li>");

                }else{

                    out.println("<li class='nav-item'>");
                    out.println("<a class='nav-link disabled' href=''>Birre</a>");
                    out.println("</li>");

                }
            }else{

                out.println("<li class='nav-item'>");
                out.println("<a class='nav-link' href='ControlloEta'>Birre</a>");
                out.println("</li>");

            }

            
        }
        

        out.println("<li class='nav-item'>");
        out.println("<a class='nav-link' href='Eventi'>Eventi</a>");
        out.println("</li>");

        out.println("<li class='nav-item'>");
        out.println("<a class='nav-link' href='About'>Chi siamo</a>");
        out.println("</li>");

        out.println("</ul>");
        out.println("</div>");

        
        out.println("<div class='navbar align-self-center d-flex'>");

        if(user != null){

            out.println("<a class='nav-icon position-relative text-decoration-none' href='Carrello'>");
            out.println("<h5><i class='bi bi-cart mr-1'></i></h5>");
            out.println("</a>");

        }else{
            out.println("<a class='nav-icon position-relative text-decoration-none disabled' href=''>");
            out.println("<h5><i class='bi bi-cart mr-1'></i></h5>");
            out.println("</a>");
        }

    
        out.println("<a class='nav-icon position-relative text-decoration-none' role='button' id='dropdownMenuLink' href='#' data-bs-toggle='dropdown' aria-expanded='false'>");
        out.println("<h5><i class='bi bi-person mr-3'></i></h5>");
        out.println("</a>");

        if(user != null){
            admin = user.getAdmin();

            if(admin){
                out.println("<ul class='dropdown-menu' aria-labelledby='dropdownMenuLink'>");
                out.println("<li><a class='dropdown-item' href='Prenotazioni'>Prenotazioni</a></li>");
                out.println("<li><a class='dropdown-item' href='Acquisti'>Acquisti</a></li>");
                out.println("<li><a class='dropdown-item' href='Recensioni'>Recensioni</a></li>");
                out.println("<li><a class='dropdown-item' href='InserisciEvento'>Aggiungi evento</a></li>");
                out.println("<li><a class='dropdown-item' href='logout'>Logout</a></li>");
            }else{
                out.println("<ul class='dropdown-menu' aria-labelledby='dropdownMenuLink'>");
                out.println("<li><a class='dropdown-item' href='Prenotazioni'>Prenotazioni</a></li>");
                out.println("<li><a class='dropdown-item' href='Acquisti'>Acquisti</a></li>");
                out.println("<li><a class='dropdown-item' href='Recensioni'>Recensioni</a></li>");
                out.println("<li><a class='dropdown-item' href='logout'>Logout</a></li>");
                
            }
        }else{
            out.println("<ul class='dropdown-menu' aria-labelledby='dropdownMenuLink'>");
            out.println("<li><a class='dropdown-item' href='Accedi'>Accedi</a></li>");
            out.println("<li><a class='dropdown-item' href='Registrazione'>Registrati</a></li>");

        }
        

        out.println("</ul>");

        if(user != null){
            String nome = user.getName();

            out.println("<h6 class='h6 fw-light'>Benvenuto " + nome + "</h6>");
        }
        

        out.println("</div>");
        
        

        out.println("</div>");
        out.println("</div>");
        out.println("</nav>");
        

        chain.doFilter(request, response);

    }
    
}
