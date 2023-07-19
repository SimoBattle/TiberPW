package tags;

import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import jakarta.servlet.jsp.tagext.*;

import beans.User;

public class AdminTag extends TagSupport {


    public int doEndTag () throws JspException {

        HttpSession session = pageContext.getSession();
        HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
        HttpServletResponse response = (HttpServletResponse)pageContext.getResponse();

    
        User user = (User) session.getAttribute("User");


        if(user == null){

            try{
                response.sendRedirect("Home");
                return SKIP_PAGE;
            }
            catch(Exception ex){
                throw new JspException(ex.getMessage());
            }

        }else if (user.getAdmin() == false){

            try{
                response.sendRedirect("Home");
                return SKIP_PAGE;
            }
            catch(Exception ex){
                throw new JspException(ex.getMessage());
            }

        }

        return EVAL_PAGE;
    }
    
}
