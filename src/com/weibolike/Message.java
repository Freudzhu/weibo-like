package com.weibolike;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weibolike.model.Blah;
import com.weibolike.model.UserService;
@WebServlet("/message.do")
public class Message extends HttpServlet{
    private final String MEMBERVIEW = "member.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
               throws ServletException, IOException {
    	UserService userService = (UserService) request.getServletContext().getAttribute("USER_SERVICE");
    	String username = (String) request.getSession().getAttribute("login");
    	request.setCharacterEncoding("UTF-8");
    	String blabla= (String) request.getParameter("blabla");
    	if(blabla !=null && blabla.length() != 0){
    		if(blabla.length() < 140){
    			Blah blah = new Blah();
    			blah.setUsername(username);
    			blah.setTxt(blabla);
    			blah.setDate(new Date());
    			userService.addBlah(blah);
    		}
    		else{
    			request.setAttribute("blabla", blabla);
    		}
    		
    	}
    	List<Blah> blahs = userService.getBlahs(username);
        request.setAttribute("blahs", blahs);
        request.getRequestDispatcher(MEMBERVIEW).forward(request, response);

}
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
 	UserService userService = (UserService) request.getServletContext().getAttribute("USER_SERVICE");
 	String username = (String) request.getSession().getAttribute("login");
 	request.setCharacterEncoding("UTF-8");
 	String blabla= (String) request.getParameter("blabla");
 	if(blabla !=null && blabla.length() != 0){
 		if(blabla.length() < 140){
 			Blah blah = new Blah();
 			blah.setUsername(username);
 			blah.setTxt(blabla);
 			blah.setDate(new Date());
 			userService.addBlah(blah);
 		}
 		else{
 			request.setAttribute("blabla", blabla);
 		}
 		
 	}
 	List<Blah> blahs = userService.getBlahs(username);
     request.setAttribute("blahs", blahs);
     request.getRequestDispatcher(MEMBERVIEW).forward(request, response);

}

}
