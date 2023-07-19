import java.io.*;
import java.util.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/writecookie")
public class WriteCookie extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getAttribute("id");
		Cookie[] cookies = request.getCookies();
        String beerHistory = null;
        LinkedList<String> list = null;
        Cookie cookie = null;
        
        StringBuffer sb = new StringBuffer();  


		for ( int i = 0 ; cookies != null && i < cookies.length; i++ ) {  
			if (cookies[i].getName().equals( "beerHistory" )) {
				beerHistory = cookies[i].getValue();
                cookie = cookies[i];
			}
		}
		if (beerHistory == null) {
            list = new LinkedList<>();
			list.addFirst(id); 

            for (String l: list) {  
                sb.append(l).append( "#" );  
            } 
            String sc1 =  sb.deleteCharAt(sb.length()- 1 ).toString();
            cookie = new Cookie( "beerHistory" , sc1 );
            cookie.setMaxAge(30*24*60*60);
            cookie.setPath("/Tiber");
            response.addCookie(cookie);  
		} else {

			String[] srcArray = beerHistory.split( "\\#" ); 
			List<String> srcList = Arrays.asList(srcArray);
			list = new LinkedList<>(srcList);			
			if (list.contains(id)) {  
                list.remove(id);  
                list.addFirst(id);  
        	} else {
				if (list.size() >= 3) {  
                    list.removeLast();  
                    list.addFirst(id);				
				} else {
					list.addFirst(id); 	
				}
			}


            for (String l: list) {  
                sb.append(l).append( "#" );  
            } 

            String sc2 =  sb.deleteCharAt(sb.length()- 1 ).toString();

            
            cookie.setMaxAge(30*24*60*60);
            cookie.setPath("/Tiber");
            cookie.setValue(sc2);
            response.addCookie(cookie);

            
		}	

        
    	 
	}
}