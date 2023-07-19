import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import beans.User;
import beans.Cart;


@WebServlet("/getbuyed")
public class GetBuyed extends HttpServlet {


    private Connection connection; 
    private PreparedStatement getBuyed, getName;

    ResultSet RSbuyed, RSname;

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

            getBuyed = connection.prepareStatement("SELECT * FROM acquisti WHERE email = ? ORDER BY id_birra ASC");

            getName = connection.prepareStatement("SELECT nome FROM birre WHERE id_birra = ?");

        }
        catch ( Exception exception ) {
            exception.printStackTrace();
            throw new UnavailableException( exception.getMessage() );
        }


        HttpSession session = request.getSession();

        

        User user = (User) session.getAttribute("User");
        String email = user.getEmail();


        try{

            List<Cart> lista = new ArrayList<>();

            getBuyed.setString(1, email);
            RSbuyed = getBuyed.executeQuery();

            while(RSbuyed.next()){

                String prodotto = "";

                Integer quantita = null;
                Float prezzo = null;
                Float totale = null;

                Integer id = RSbuyed.getInt("id_birra");

                getName.setInt(1, id);
                RSname = getName.executeQuery();

                if(RSname.next()){
                    prodotto = RSname.getString("nome");
                }


                Cart product = new Cart(prodotto, quantita, id, prezzo, totale);
                lista.add(product);

            }

            session.setAttribute("buyed", lista);


        }catch(SQLException sqlException){

            sqlException.printStackTrace();

        }finally{

            if (RSbuyed != null) 
                try { RSbuyed.close(  ); } catch (SQLException ignore) { }

            if (RSname != null) 
                try { RSname.close(  ); } catch (SQLException ignore) { }

            if (getBuyed != null) 
                try { getBuyed.close(  ); } catch (SQLException ignore) { }

            if (getName != null) 
                try { getName.close(  ); } catch (SQLException ignore) { }

            if (connection != null) 
                try { connection.close(  ); } catch (SQLException ignore) { }
        }
    }
    
}
