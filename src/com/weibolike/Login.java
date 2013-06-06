package com.weibolike;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weibolike.model.Account;
import com.weibolike.model.UserService;
@WebServlet(
	    urlPatterns={"/login.do"},
	    initParams={
	            @WebInitParam(name = "SUCCESS_VIEW", value = "message.do"),
	            @WebInitParam(name = "ERROR_VIEW", value = "index.jsp")
	    }
	)
public class Login extends HttpServlet{
	private String SUCCESS_VIEW;
	private String ERROR_VIEW;
	@Override
    public void init() throws ServletException {
        SUCCESS_VIEW = getServletConfig().getInitParameter("SUCCESS_VIEW");
        ERROR_VIEW = getServletConfig().getInitParameter("ERROR_VIEW");
    }
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(req,resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(req,resp);
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Account t = new Account();
		t.setName(username);
		t.setPassword(password);
		String page;
		if(userService.checkLogin(t)){
			req.getSession().setAttribute("login", username);
			page = SUCCESS_VIEW;
		}
		else{
			req.setAttribute("error", "用户名密码错误");
			page = ERROR_VIEW;
		}
		req.getRequestDispatcher(page).forward(req, resp);
	}
	
	
}
