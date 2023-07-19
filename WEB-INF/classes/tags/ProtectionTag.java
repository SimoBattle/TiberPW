package tags;

import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import jakarta.servlet.jsp.tagext.*;

import beans.User;

public class ProtectionTag extends TagSupport {

    public int doEndTag () throws JspException {

        HttpSession session = pageContext.getSession();
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        HttpServletResponse response = (HttpServletResponse)pageContext.getResponse();

        String protectedPage = request.getRequestURI();

        User user = (User) session.getAttribute("User");

        Boolean adult = (Boolean) session.getAttribute("adult");

        if(protectedPage.equals("/Tiber/Birre") | protectedPage.equals("/Tiber/Birra")){

            if (user == null && adult == null){

                try{
                    response.sendRedirect("Home");
                    return SKIP_PAGE;
                }
                catch(Exception ex){
                    throw new JspException(ex.getMessage());
                }

            }else if (user!= null && user.getMaggiorenne() == false){

                try{
                    response.sendRedirect("Home");
                    return SKIP_PAGE;
                }
                catch(Exception ex){
                    throw new JspException(ex.getMessage());
                }
                

            }else if(adult != null && adult == false){

                try{
                    response.sendRedirect("Home");
                    return SKIP_PAGE;
                }
                catch(Exception ex){
                    throw new JspException(ex.getMessage());
                }

            }
        }else{

            if(user == null){

                try{
                    response.sendRedirect("Home");
                    return SKIP_PAGE;
                }
                catch(Exception ex){
                    throw new JspException(ex.getMessage());
                }

            }

        }
    
        return EVAL_PAGE;
    }
    
}
