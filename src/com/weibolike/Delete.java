package com.weibolike;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weibolike.model.Blah;
import com.weibolike.model.UserService;
@WebServlet(
	    urlPatterns={"/delete.do"},
	    initParams={
	            @WebInitParam(name = "SUCCESS_VIEW", value = "message.do")
	    }
	)
public class Delete extends HttpServlet{
	private String SUCCESS_VIEW;
	@Override
    public void init() throws ServletException {
        SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
    }
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stu
		String username = (String)req.getSession().getAttribute("login");
    	String message= (String) req.getParameter("message");
    	UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");
        Blah blah = new Blah();
        blah.setUsername(username);
        blah.setDate(new Date(Long.parseLong(message)));
    	userService.deleteBlah(blah);
    	req.getRequestDispatcher(SUCCESS_VIEW).forward(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = (String)req.getSession().getAttribute("login");
    	String message= (String) req.getParameter("message");
    	UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");
    	Blah blah = new Blah();
        blah.setUsername(username);
        blah.setDate(new Date(Long.parseLong(message)));
    	userService.deleteBlah(blah);
    	req.getRequestDispatcher(SUCCESS_VIEW).forward(req, resp);
	}
	
}
