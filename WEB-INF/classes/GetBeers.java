import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import beans.Beer;

@WebServlet("/beer")
public class GetBeers extends HttpServlet {

    private Connection connection;
    private PreparedStatement getBeers;
    
    
    ResultSet RSbeers;

    
    

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


            getBeers = connection.prepareStatement("SELECT * FROM birre ORDER BY id_birra ASC");

            

            
            
        }
        catch ( Exception exception ) {
            exception.printStackTrace();
            throw new UnavailableException( exception.getMessage() );
        }

        HttpSession session = request.getSession();

    
        try{
            RSbeers = getBeers.executeQuery();

            List<Beer> lista = new ArrayList<> ();

            while(RSbeers.next()){

                String nome = RSbeers.getString("nome");
                String descrizione = RSbeers.getString("descrizione");
                Float prezzo = RSbeers.getFloat("prezzo");
                Float gradazione = RSbeers.getFloat("gradazione");
                Integer id_birra = RSbeers.getInt("id_birra");
                Integer id_tipologia = RSbeers.getInt("id_tipologia");
                Integer quantita = RSbeers.getInt("quantita");

                
                Beer birra = new Beer(nome, descrizione, gradazione, prezzo, id_birra, id_tipologia, quantita);
                lista.add(birra);     
                   
            }

            session.setAttribute("birre", lista);

        }catch(SQLException sqlException){

            sqlException.getStackTrace();

        }finally{
            if (RSbeers != null) 
                try { RSbeers.close(  ); } catch (SQLException ignore) { }

            if (getBeers != null) 
                try { getBeers.close(  ); } catch (SQLException ignore) { }

            if (connection != null) 
                try { connection.close(  ); } catch (SQLException ignore) { }
        }

        

        
        
        
        
        

        
        

        
    }

    
}
