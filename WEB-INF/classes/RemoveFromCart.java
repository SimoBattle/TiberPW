import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.*;
import java.sql.*;

import beans.User;

@WebServlet("/cartremove")
public class RemoveFromCart extends HttpServlet{

    private Connection connection; 
    private PreparedStatement removeFromCart;


    public void init(ServletConfig config) throws ServletException {

        try { 
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e) {
            throw new UnavailableException( e.getMessage(  ));
        }

        
    }


    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        try{

            String url = "jdbc:mysql://localhost:3306/tiber";
            final String root = "root";
            final String pass = "1234";
            connection = DriverManager.getConnection(url, root, pass);

            removeFromCart = connection.prepareStatement("DELETE FROM carrello WHERE id_birra = ? AND email = ?");

        }
        catch ( Exception exception ) {
            exception.printStackTrace();
            throw new UnavailableException( exception.getMessage() );
        }


        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("User");
        String email = user.getEmail();

        Integer id = Integer.parseInt(request.getParameter("id"));

        try{

            removeFromCart.setInt(1, id);
            removeFromCart.setString(2, email);

            removeFromCart.executeUpdate();

            response.sendRedirect("Carrello?delete=1");

        }catch(SQLException sqlException){

            sqlException.printStackTrace();
            response.sendRedirect("Carrello?delete=0");


        }finally{

            if (removeFromCart != null) 
                try { removeFromCart.close(  ); } catch (SQLException ignore) { }

            if (connection != null) 
                try { connection.close(  ); } catch (SQLException ignore) { }

        }
    }
    
}
