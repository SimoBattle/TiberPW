import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;



import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/upload")

public class NewEvent extends HttpServlet {

    private Connection connection;
    private PreparedStatement newEvent;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    


    public void init(ServletConfig config) throws ServletException {

        try { 
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e) {
            throw new UnavailableException( e.getMessage(  ));
        }
       
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        try{
            String url = "jdbc:mysql://localhost:3306/tiber";
            final String user = "root";
            final String pass = "1234";
            connection = DriverManager.getConnection(url, user, pass);

            newEvent = connection.prepareStatement("INSERT INTO eventi (nome,descrizione,data,posti_disponibili) VALUES (?,?,?,?)");
        }
        catch ( Exception exception ) {
            exception.printStackTrace();
            throw new UnavailableException( exception.getMessage() );
        }


        String nome = request.getParameter("nome");
        String descrizione = request.getParameter("descrizione");
        String save = request.getParameter("data");
        Date data = Date.valueOf(save);
        Integer posti = Integer.parseInt(request.getParameter("posti"));

        RequestDispatcher dispatcher;

        


        try{

            

            newEvent.setString(1, nome);
            newEvent.setString(2, descrizione);
            newEvent.setDate(3, data);
            newEvent.setInt(4, posti);

            newEvent.executeUpdate();

           

            response.sendRedirect("Eventi?event=1");

        }catch(SQLException sqlException){

            sqlException.printStackTrace();
            response.sendRedirect("InserisciEvento?event=0");


        }finally{

            if (newEvent != null) 
                try { newEvent.close(  ); } catch (SQLException ignore) { }

            if (connection != null) 
                try { connection.close(  ); } catch (SQLException ignore) { }

        }












    }
}