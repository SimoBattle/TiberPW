import java.io.*;
import java.sql.*;
import java.time.LocalDate;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import beans.User;

@WebServlet("/newreview")
public class NewReview extends HttpServlet {

    private Connection connection;
    private PreparedStatement checkReview, newReview, updateReview;

    ResultSet RSreview;



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

            checkReview = connection.prepareStatement("SELECT * FROM recensioni WHERE id_birra = ? AND email = ?");
            newReview = connection.prepareStatement("INSERT INTO recensioni (id_birra, email, valutazione, recensione, data) VALUES (?,?,?,?,?)");
            updateReview = connection.prepareStatement("UPDATE recensioni SET valutazione = ?, recensione = ?, data = ? WHERE id_birra = ? AND email = ?");
        }
        catch ( Exception exception ) {
            exception.printStackTrace();
            throw new UnavailableException( exception.getMessage() );
        }

	
	    Integer id = Integer.parseInt(request.getParameter("id"));
        String prodotto = request.getParameter("name");

        Integer valutazione = Integer.parseInt(request.getParameter("valutazione"));
        String recensione = request.getParameter("recensione");
        LocalDate now = LocalDate.now();
        Date data = Date.valueOf(now);

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("User");
        String email = user.getEmail();

        
        try{

            checkReview.setInt(1, id);
            checkReview.setString(2, email);

            RSreview = checkReview.executeQuery();

            if(RSreview.next()){
                updateReview.setInt(1, valutazione);
                updateReview.setString(2, recensione);
                updateReview.setDate(3, data);
                updateReview.setInt(4, id);
                updateReview.setString(5, email);

                updateReview.executeUpdate();
            }else{

                newReview.setInt(1, id);
                newReview.setString(2, email);
                newReview.setInt(3, valutazione);
                newReview.setString(4, recensione);
                newReview.setDate(5, data);
                
                newReview.executeUpdate();
            }

            response.sendRedirect("Acquisti?review=1");

        }catch(SQLException sqlException){

            sqlException.printStackTrace();
            response.sendRedirect("ScriviRecensione?review=0&id=" + id + "&n=" + prodotto);


        }finally{

            if (newReview != null) 
                try { newReview.close(  ); } catch (SQLException ignore) { }

            if (connection != null) 
                try { connection.close(  ); } catch (SQLException ignore) { }

        }












    }
}