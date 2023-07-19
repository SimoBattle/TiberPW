import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

  public void init() throws ServletException {
    
  }


  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    
    HttpSession session = request.getSession();

    session.setAttribute("User", null);

    response.sendRedirect("Home");

  }
}