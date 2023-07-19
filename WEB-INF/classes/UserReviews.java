import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import beans.Review;
import beans.User;

@WebServlet("/ureviews")
public class UserReviews extends HttpServlet {

    private Connection connection;
    private PreparedStatement getReviews, productName, userName;
    
    ResultSet RSreviews, RSproduct, RSuser;


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
            final String user = "root";
            final String pass = "1234";
            Connection connection = DriverManager.getConnection(url, user, pass);

            getReviews = connection.prepareStatement("SELECT * FROM recensioni WHERE email = ? ORDER BY id_birra ASC");

            productName = connection.prepareStatement("SELECT nome FROM birre WHERE id_birra = ?");

            userName = connection.prepareStatement("SELECT nome, cognome FROM utenti WHERE email = ?");

        }
        catch ( Exception exception ) {
            exception.printStackTrace();
            throw new UnavailableException( exception.getMessage() );
        }

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("User");

        String email = user.getEmail();

        


        try{

            getReviews.setString(1, email);

            RSreviews = getReviews.executeQuery();

            List<Review> lista = new ArrayList<> ();

            while(RSreviews.next()){
                Integer id_birra = RSreviews.getInt("id_birra");
                Integer valutazione = RSreviews.getInt("valutazione");
                String recensione = RSreviews.getString("recensione");
                Date data = RSreviews.getDate("data");
                

                String prodotto = null;
                String nome = null;
                String cognome = null;

                

                productName.setInt(1, id_birra);
                RSproduct = productName.executeQuery();
                if(RSproduct.next()){
                    prodotto = RSproduct.getString("nome");
                }

                userName.setString(1, email);
                RSuser = userName.executeQuery();
                if(RSuser.next()){
                    nome = RSuser.getString("nome");
                    cognome = RSuser.getString("cognome");
                }


                Review review = new Review(prodotto, nome, cognome, valutazione, recensione, data);
                lista.add(review);
            }

            session.setAttribute("recensioni", lista);

        }catch(SQLException sqlException){

            sqlException.printStackTrace();

        }finally{
            if (RSproduct != null) 
                try { RSproduct.close(  ); } catch (SQLException ignore) { }

            if (RSreviews != null) 
                try { RSreviews.close(  ); } catch (SQLException ignore) { }
            
            if (RSuser != null) 
                try { RSuser.close(  ); } catch (SQLException ignore) { }

            if (getReviews != null) 
                try { getReviews.close(  ); } catch (SQLException ignore) { }

            if (productName != null) 
                try { productName.close(  ); } catch (SQLException ignore) { }

            if (userName != null) 
                try { userName.close(  ); } catch (SQLException ignore) { }

            if (connection != null) 
                try { connection.close(  ); } catch (SQLException ignore) { }

        }

    }

    
}
