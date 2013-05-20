package com.weibolike;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/delete.do")
public class Delete extends HttpServlet{
	private String USERS;
	private final String LOGIN_VIEW = "index.html";
	private final String SUCCESS_VIEW = "member.view";
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stu
    	String username = (String)req.getSession().getAttribute("login");
    	String message= (String) req.getParameter("message");
    	File delete = new File(USERS + "/" + username + "/" + message + ".txt");
    	if(delete.exists()){
    		delete.delete();
    	}
    	resp.sendRedirect(SUCCESS_VIEW);
	}
	
}
