import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.*;
import java.sql.*;
import java.util.*;

import beans.User;
import beans.Cart;


@WebServlet("/buycart")
public class BuyCartProduct extends HttpServlet {


    private Connection connection; 
    private PreparedStatement updateDisp, setBuyed, clearCart;
    private Savepoint sp;
    

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

            connection.setAutoCommit(false);

            
            
            updateDisp = connection.prepareStatement("UPDATE birre SET quantita = quantita - ? WHERE id_birra = ?");

            setBuyed = connection.prepareStatement("INSERT IGNORE INTO acquisti (email, id_birra) VALUES (?, ?)");

            clearCart = connection.prepareStatement("DELETE FROM carrello WHERE email = ?");


            sp = connection.setSavepoint("sp");

            
        }
        catch ( Exception exception ) {
            exception.printStackTrace();
            throw new UnavailableException( exception.getMessage() );
        }


        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("User");
        String email = user.getEmail();

        List<Cart> carrello = (List<Cart>) session.getAttribute("cart");

        try{
            if(!carrello.isEmpty()){
                for(Cart cart : carrello){
                    Integer id = cart.getId();
                    Integer quantita = cart.getQuantita();

                    updateDisp.setInt(1, quantita);
                    updateDisp.setInt(2, id);
                    updateDisp.executeUpdate();

                    setBuyed.setString(1, email);
                    setBuyed.setInt(2, id);
                    setBuyed.executeUpdate();

                    clearCart.setString(1, email);
                    clearCart.executeUpdate();

                    connection.commit();
                    
                }

                response.sendRedirect("Carrello?buy=1");
            }

        }catch(Exception e){

            e.printStackTrace();

            try{
                
                connection.rollback(sp);
                response.sendRedirect("Carrello?buy=0");

            }catch(SQLException ignore){
                
            }

            

        }finally{

            if (updateDisp != null) 
                try { updateDisp.close(  ); } catch (SQLException ignore) { }

            if (setBuyed != null) 
                try { setBuyed.close(  ); } catch (SQLException ignore) { }

            if (clearCart != null) 
                try { clearCart.close(  ); } catch (SQLException ignore) { }

            if (connection != null) 
                try { connection.close(  ); } catch (SQLException ignore) { }

        }

    }
    
}
