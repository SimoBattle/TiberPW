import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import beans.Event;

@WebServlet("/events")
public class GetEvents extends HttpServlet {

    private Connection connection;
    private PreparedStatement getEvents, removeEvents;
    
    ResultSet RSevents;
    

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
            connection = DriverManager.getConnection(url, user, pass);


            getEvents = connection.prepareStatement("SELECT * FROM eventi ORDER BY data ASC");
            
            removeEvents = connection.prepareStatement("DELETE FROM eventi WHERE nome = ?");

        }
        catch ( Exception exception ) {
            exception.printStackTrace();
            throw new UnavailableException( exception.getMessage() );
        }

        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        
        
        

        try {
            RSevents = getEvents.executeQuery();

            List<Event> lista = new ArrayList<> ();

            while(RSevents.next()){
                String nome = RSevents.getString("nome");
                String descrizione = RSevents.getString("Descrizione");
                Date data = RSevents.getDate("data");
                Integer posti = RSevents.getInt("posti_disponibili");

                //check if the event is still available if is not delete it
                long millis = System.currentTimeMillis();
                Date now = new Date(millis);
                if(data.compareTo(now) == -1){
                    removeEvents.setString(1, nome); 
                    removeEvents.executeUpdate();
                    continue;
                }

                

                Event evento = new Event(nome, descrizione, data, posti);
                lista.add(evento);
                

                
            }

            session.setAttribute("eventi", lista);
            
            
        } catch (SQLException sqlException) {
            
            
        }finally {

            if (RSevents != null) 
                try { RSevents.close(  ); } catch (SQLException ignore) { }

            if (getEvents != null) 
                try { getEvents.close(  ); } catch (SQLException ignore) { }

            if (removeEvents != null) 
                try { removeEvents.close(  ); } catch (SQLException ignore) { }
            
            if (connection != null) 
                try { connection.close(  ); } catch (SQLException ignore) { }

            
        }

       
        

        
    }

    
}