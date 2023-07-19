import java.io.*;
import java.sql.*;
import java.util.Base64;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;




@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private Connection connection;
    private PreparedStatement registerUser;


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

            registerUser = connection.prepareStatement("INSERT INTO utenti (email,password,nome,cognome,data_nascita) VALUES (?,?,?,?,?)");
        }
        catch ( Exception exception ) {
            exception.printStackTrace();
            throw new UnavailableException( exception.getMessage() );
        }


        String name = request.getParameter("name");
		String lastname = request.getParameter("lastname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
        String save = request.getParameter("data");
        Date data = Date.valueOf(save);
		RequestDispatcher dispatcher;

        
        
		
		try{

            String encoded = Base64.getEncoder().encodeToString(password.getBytes());
			
			registerUser.setString(1, email);
			registerUser.setString(2, encoded);
			registerUser.setString(3, name);
			registerUser.setString(4, lastname);
			registerUser.setDate(5, data);
			
			registerUser.executeUpdate();

            request.setAttribute("registration","success");
			dispatcher = request.getRequestDispatcher("Accedi");
            dispatcher.forward(request,response);


            
		}catch(SQLException sqlException) {

            request.setAttribute("registration","failed");
            dispatcher = request.getRequestDispatcher("Registrazione");
            dispatcher.forward(request,response);
           
        }
        finally {

            if (registerUser != null) 
                try { registerUser.close(  ); } catch (SQLException ignore) { }

            if (connection != null) 
                try { connection.close(  ); } catch (SQLException ignore) { }
        }

        
        
    }
}