import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import beans.User;
import beans.Cart;


@WebServlet("/getcart")
public class GetCart extends HttpServlet {

    private Connection connection; 
    private PreparedStatement getCart, getBeer;

    ResultSet RScart, RSbeer;


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

            getCart = connection.prepareStatement("SELECT * FROM carrello WHERE email = ? ORDER BY id_birra ASC");

            getBeer = connection.prepareStatement("SELECT nome, quantita, prezzo FROM birre WHERE id_birra = ?");

        }
        catch ( Exception exception ) {
            exception.printStackTrace();
            throw new UnavailableException( exception.getMessage() );
        }


        HttpSession session = request.getSession();

        

        User user = (User) session.getAttribute("User");
        String email = user.getEmail();


        try{

            getCart.setString(1, email);
            RScart = getCart.executeQuery();

            List<Cart> lista = new ArrayList<> ();

            Float totalCart = 0.0f;

            while(RScart.next()){

                Integer id = RScart.getInt("id_birra");
                Integer quantita = RScart.getInt("quantita");

                getBeer.setInt(1, id);
                RSbeer = getBeer.executeQuery();

                if(RSbeer.next()){
                    String prodotto = RSbeer.getString("nome");
                    Integer disponibili = RSbeer.getInt("quantita");
                    Float prezzo = RSbeer.getFloat("prezzo");

                    if (quantita > disponibili){
                        if(disponibili == 0){
                            continue;
                        }
                        else{
                            quantita = disponibili;
                        }
                    }
    
                    Float totale = prezzo * quantita;
    
                    totalCart = totalCart + totale;
    
                    Cart cart = new Cart(prodotto, quantita, id, prezzo, totale);
                    lista.add(cart);
                }

                

            }

            session.setAttribute("cart", lista);
            session.setAttribute("total", totalCart);

            


            

        }catch(SQLException sqlException){

            sqlException.getStackTrace();

        }finally{

            if (RSbeer != null) 
                try { RSbeer.close(  ); } catch (SQLException ignore) { }

            if (RScart != null) 
                try { RScart.close(  ); } catch (SQLException ignore) { }

            if (getCart != null) 
                try { getCart.close(  ); } catch (SQLException ignore) { }

            if (getBeer != null) 
                try { getBeer.close(  ); } catch (SQLException ignore) { }

            if (connection != null) 
                try { connection.close(  ); } catch (SQLException ignore) { }

        }
    }
    
}
