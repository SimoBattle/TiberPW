import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.*;
import java.time.*;
import java.sql.Date;

@WebServlet("/checkAge")
public class CheckAge extends HttpServlet {

    public void init() {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        String save = request.getParameter("data");
        Date data = Date.valueOf(save);
        LocalDate now = LocalDate.now();

        Integer differenza = Period.between(data.toLocalDate(), now).getYears();

        if(differenza >= 18){
            session.setAttribute("adult", true);
            response.sendRedirect("Birre");
        }
        else{
            session.setAttribute("adult", false);
            response.sendRedirect("Home");
        }

    }
    
}
