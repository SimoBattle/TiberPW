import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import beans.Booking;
import beans.User;

@WebServlet("/bookings")
public class GetBookings extends HttpServlet{

    private Connection connection;
    private PreparedStatement getBookings;

    ResultSet RSbooking;


    public void init(ServletConfig config) throws ServletException {

        try { 

            Class.forName("com.mysql.cj.jdbc.Driver");

        }catch (ClassNotFoundException e) {
            throw new UnavailableException( e.getMessage(  ));
        }  
    }

    public void doGet ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        try{

            String url = "jdbc:mysql://localhost:3306/tiber";
            final String user = "root";
            final String pass = "1234";
            connection = DriverManager.getConnection(url, user, pass);

            getBookings = connection.prepareStatement("SELECT * FROM prenotazioni WHERE email = ? ORDER BY data_evento ASC");
            
        }
        catch ( Exception exception ) {
            exception.printStackTrace();
            throw new UnavailableException( exception.getMessage() );
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");
        String email = user.getEmail();

        try {

            getBookings.setString(1, email);
            RSbooking = getBookings.executeQuery();

            List<Booking> lista = new ArrayList<> ();

            while (RSbooking.next()){

                Integer posti = RSbooking.getInt("posti_prenotati");
                String evento = RSbooking.getString("nome_evento");
                Date data = RSbooking.getDate("data_evento");

                Booking prenotazione = new Booking(posti, evento, data);
                lista.add(prenotazione);

            }

            session.setAttribute("prenotazioni", lista);

        } catch (SQLException SQLe) {
            
            
        }finally {

            if (RSbooking != null) 
                try { RSbooking.close(  ); } catch (SQLException ignore) { }

            if (getBookings != null) 
                try { getBookings.close(  ); } catch (SQLException ignore) { }

            
            if (connection != null) 
                try { connection.close(  ); } catch (SQLException ignore) { }

            
        }
    }
    
}
