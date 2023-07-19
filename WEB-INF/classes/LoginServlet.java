import java.io.*;
import java.sql.*;
import java.time.*;
import java.util.Base64;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import beans.User;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private Connection connection;
    private PreparedStatement searchUser;
    ResultSet RSuser;


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

            searchUser = connection.prepareStatement("select * from utenti where email = ?");

            
        }
        catch ( Exception exception ) {
            exception.printStackTrace();
            throw new UnavailableException( exception.getMessage() );
        }


		String email = request.getParameter("email");
		String password = request.getParameter("password");	
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher;
        

        
		
		try{

			searchUser.setString(1, email);
			ResultSet RSuser = searchUser.executeQuery();

			if (RSuser.next()) {

                String encoded = new String(Base64.getEncoder().encodeToString(password.getBytes()));

                

                String stored = new String (RSuser.getString("password"));

                if(encoded.equals(stored)){
                    Boolean maggiorenne;
                    String nome = RSuser.getString("nome");
                    String cognome = RSuser.getString("cognome");
                    Date data = RSuser.getDate("data_nascita");
                    Integer save = RSuser.getInt("admin");
                    Boolean admin;

                    if(save == 1){
                        admin = true;
                    }else{
                        admin = false;
                    }

                    LocalDate now = LocalDate.now();

                    Integer differenza = Period.between(data.toLocalDate(), now).getYears();

                    if (differenza >= 18){
                        maggiorenne = true;
                    }
                    else{
                        maggiorenne = false;
                    }

                    User user = new User(nome, cognome, email, maggiorenne, admin);
                    session.setAttribute("User", user);

                    session.setAttribute("adult", null);

                    response.sendRedirect("Home");

                }else{

                    request.setAttribute("error","password");
                    dispatcher = request.getRequestDispatcher("Accedi");
                    dispatcher.forward(request,response);

                }

                
               

			}else{
                
                request.setAttribute("error","email");
                dispatcher = request.getRequestDispatcher("Accedi");
                dispatcher.forward(request,response);

            }

			
            

		}catch (SQLException sqlException) {

            
           	
		}
        finally {

            if (RSuser != null) 
                try { RSuser.close(  ); } catch (SQLException ignore) { }

            if (searchUser != null) 
                try { searchUser.close(  ); } catch (SQLException ignore) { }

            if (connection != null) 
                try { connection.close(  ); } catch (SQLException ignore) { }
                
                
        }

        
    }
}