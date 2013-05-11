package com.weibolike;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/message.do")
public class Message extends HttpServlet{
	private String USERS;
    private final String LOGIN_VIEW = "index.html";
    private final String SUCCESS_VIEW = "member.view";
    private final String ERROR_VIEW = "member.view";
    protected void doPost(HttpServletRequest request, 
            HttpServletResponse response) 
               throws ServletException, IOException {
    	USERS = request.getServletContext().getRealPath("/")+"/WEB-INF/user";
    	if(request.getSession().getAttribute("login") == null){
    		response.sendRedirect(LOGIN_VIEW);
		}
    	request.setCharacterEncoding("UTF-8");
    	String blabla= (String) request.getParameter("blabla");
    	System.out.println(blabla);
    	if(blabla !=null && blabla.length() != 0){
    		if(blabla.length() < 140){
    			String username = (String) request.getSession().getAttribute("login");
        		addMessage(username,blabla);
        		response.sendRedirect(SUCCESS_VIEW);
    		}
    		else{
    			request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
    		}
    		
    	}else{		
    		response.sendRedirect(ERROR_VIEW);
    	}
    	

}

private void addMessage(String username, String blabla) throws IOException {
	String file = USERS + "/" + username + "/" + new Date().getTime() + ".txt";
	System.out.println(file);
	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
	writer.write(blabla);
	writer.close();
}

}
