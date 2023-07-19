import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.*;
import java.sql.*;

import beans.User;


@WebServlet("/prenota")
public class BookEvent extends HttpServlet {

    private Connection connection; 
    private PreparedStatement updateEvent, getDate,setBooking;
    private Savepoint sp;
    

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

            connection.setAutoCommit(false);

            
            
            updateEvent = connection.prepareStatement("UPDATE eventi SET posti_disponibili = posti_disponibili - ? WHERE nome = ?");

            getDate = connection.prepareStatement("SELECT data FROM eventi WHERE nome = ?");

            setBooking = connection.prepareStatement("INSERT INTO prenotazioni (posti_prenotati, email, nome_evento, data_evento) VALUES (?, ?, ?, ?)");


            sp = connection.setSavepoint("sp");

            
        }
        catch ( Exception exception ) {
            exception.printStackTrace();
            throw new UnavailableException( exception.getMessage() );
        }

        

        Integer posti = Integer.parseInt(request.getParameter("posti"));
        String nome = request.getParameter("nome");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        String email = user.getEmail();
        RequestDispatcher dispatcher;

       
       
        try{

            Date data;
            
            updateEvent.setInt(1, posti);
            updateEvent.setString(2, nome);
            updateEvent.executeUpdate();
            



            getDate.setString(1, nome);
            ResultSet RSdata = getDate.executeQuery();

            if(RSdata.next()){
                data = RSdata.getDate(1);

                setBooking.setInt(1, posti);
                setBooking.setString(2, email);
                setBooking.setString(3, nome);
                setBooking.setDate(4, data);
                setBooking.executeUpdate();
                
            }

            connection.commit();

           
            
            

            response.sendRedirect("Prenotazioni?booking=success");



        }catch(SQLException SQLe){

            SQLe.printStackTrace();

            try{
                
                connection.rollback(sp);
                response.sendRedirect("Eventi?booking=failed");

            }catch(SQLException ignore){
                
            }

        }finally {

            if (updateEvent != null) 
                try { updateEvent.close(  ); } catch (SQLException ignore) { }

            if (setBooking != null) 
                try { setBooking.close(  ); } catch (SQLException ignore) { }

            if (connection != null) 
                try { connection.close(  ); } catch (SQLException ignore) { }
            
        }

        
    }

}
