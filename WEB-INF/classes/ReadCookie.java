import java.io.*;
import java.sql.*;
import java.util.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import beans.Beer;


@WebServlet("/readcookie")
public class ReadCookie extends HttpServlet {

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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			String url = "jdbc:mysql:// localhost:3306/tiber";
			final String user = "root";
			final String pass = "1234";
			Connection connection = DriverManager.getConnection(url, user, pass);

			getBeer = connection.prepareStatement("SELECT * FROM birre WHERE id_birra = ? ");
            		
		}catch ( Exception exception ) {
			exception.printStackTrace();
			throw new UnavailableException( exception.getMessage() );
		}

        Cookie[] cookies = request.getCookies();
        String beerHistory = null;
        LinkedList<String> list = null;

		HttpSession session = request.getSession();
		
		for ( int i = 0 ; cookies != null && i <cookies.length; i++ ) {  
            if (cookies[i].getName () .equals ( "beerHistory" )) {
                beerHistory = cookies[i].getValue();  
            }  
        }
		if (beerHistory == null) {
			session.setAttribute("beerCookie", null); 
		} else {
			String[] srcArray = beerHistory.split( "\\#" );
			List<String> srcList = Arrays.asList(srcArray);   
			List<Beer> lista = new ArrayList<> ();
			
            try {

                for (String id : srcList) {
                    getBeer.setString(1, id);
                    RSbeer = getBeer.executeQuery();
                    if(RSbeer.next()){
                        
                        String nome = RSbeer.getString("nome");
                        String descrizione = RSbeer.getString("descrizione");
                        Float prezzo = RSbeer.getFloat("prezzo");
                        Float gradazione = RSbeer.getFloat("gradazione");
                        Integer id_birra = RSbeer.getInt("id_birra");
                        Integer id_tipologia = RSbeer.getInt("id_tipologia");
                        Integer quantita = RSbeer.getInt("quantita");
    
                        Beer birra = new Beer(nome, descrizione, gradazione, prezzo, id_birra, id_tipologia, quantita);
                        lista.add(birra);     
                
                        
                    }
                }

                session.setAttribute("beerCookie", lista);
                
            } catch(SQLException sqlException) {

                sqlException.printStackTrace();
                session.setAttribute("beerCookie", null);
            
            }finally{
                if (RSbeer != null) 
                    try { RSbeer.close(  ); } catch (SQLException ignore) { }
    
                if (getBeer != null) 
                    try { getBeer.close(  ); } catch (SQLException ignore) { }
    
                if (connection != null) 
                    try { connection.close(  ); } catch (SQLException ignore) { }
            }
		    
        }

	}
}