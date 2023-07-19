import java.io.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import beans.Beer;


@WebServlet("/birra")
public class SingleBeer extends HttpServlet {

    private Connection connection;
	private PreparedStatement getBeer;
	
    ResultSet RSbeer;


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
			getBeer = connection.prepareStatement("SELECT * FROM birre WHERE id_birra = ?");
			
            		
		}catch ( Exception exception ) {
			exception.printStackTrace();
			throw new UnavailableException( exception.getMessage() );
		}

        String id = request.getParameter("id");
        HttpSession session = request.getSession();

        


        try {

            Beer beer = null;
			getBeer.setString(1,id);
			RSbeer = getBeer.executeQuery();
			
			
			if(RSbeer.next()){
				String nome = RSbeer.getString("nome");
				String descrizione = RSbeer.getString("descrizione");
				Float gradazione = RSbeer.getFloat("gradazione");
				Float prezzo = RSbeer.getFloat("prezzo");
				Integer id_birra = RSbeer.getInt("id_birra");
				Integer id_tipologia = RSbeer.getInt("id_tipologia");
				Integer quantita = RSbeer.getInt("quantita");


                beer = new Beer(nome, descrizione, gradazione, prezzo, id_birra, id_tipologia, quantita);
                session.setAttribute("birra", beer);

				
            }
            
            
            
        }catch (SQLException sqlException) {


			sqlException.printStackTrace();

            
            
        }finally {

            if (RSbeer != null) 
                try { RSbeer.close(  ); } catch (SQLException ignore) { }
			if (getBeer != null) 
                try { getBeer.close(  ); } catch (SQLException ignore) { }
            if (connection != null) 
				try { connection.close(  ); } catch (SQLException ignore) { }
		}
	}
}
    