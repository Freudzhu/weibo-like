package com.weibolike;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.weibolike.model.UserService;
@WebServlet("/message.do")
public class Message extends HttpServlet{
	
    private final String LOGIN_VIEW = "index.html";
    private final String SUCCESS_VIEW = "member.view";
    private final String ERROR_VIEW = "member.view";
    protected void doPost(HttpServletRequest request, 
            HttpServletResponse response) 
               throws ServletException, IOException {
    	UserService userService = (UserService) request.getServletContext().getAttribute("USER_SERVICE");
    	request.setCharacterEncoding("UTF-8");
    	String blabla= (String) request.getParameter("blabla");
    	if(blabla !=null && blabla.length() != 0){
    		if(blabla.length() < 140){
    			String username = (String) request.getSession().getAttribute("login");
    			System.out.println(blabla);
    			userService.addMessage(username,blabla);
        		response.sendRedirect(SUCCESS_VIEW);
    		}
    		else{
    			request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
    		}
    		
    	}else{		
    		response.sendRedirect(ERROR_VIEW);
    	}
    	

}



}
