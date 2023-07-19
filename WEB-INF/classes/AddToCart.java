import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.*;
import java.sql.*;

import beans.User;




@WebServlet("/addtocart")
public class AddToCart extends HttpServlet {

    private Connection connection; 
    private PreparedStatement addToCart;


    public void init(ServletConfig config) throws ServletException {

        try { 
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e) {
            throw new UnavailableException( e.getMessage(  ));
        }

        
    }


    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        try{

            String url = "jdbc:mysql://localhost:3306/tiber";
            final String root = "root";
            final String pass = "1234";
            connection = DriverManager.getConnection(url, root, pass);

            addToCart = connection.prepareStatement("INSERT INTO carrello (email, id_birra, quantita) VALUES (?, ?, ?)");

        }
        catch ( Exception exception ) {
            exception.printStackTrace();
            throw new UnavailableException( exception.getMessage() );
        }


        HttpSession session = request.getSession();

        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer quantita = Integer.parseInt(request.getParameter("amount"));

        User user = (User) session.getAttribute("User");
        String email = user.getEmail();

        try{

            addToCart.setString(1, email);
            addToCart.setInt(2, id);
            addToCart.setInt(3, quantita);

            addToCart.executeUpdate();

            response.sendRedirect("Birra?id=" + id + "&cart=1");

        }catch(SQLException SQLe){

            SQLe.printStackTrace();
            response.sendRedirect("Birra?id=" + id + "&cart=0");

        }finally{

            if (addToCart != null) 
                try { addToCart.close(  ); } catch (SQLException ignore) { }

            if (connection != null) 
                try { connection.close(  ); } catch (SQLException ignore) { }

        }

    }
    
}
