package com.weibolike;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.weibolike.model.UserService;
@WebServlet("/login.do")
public class Login extends HttpServlet{
	
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
		if(userService.checkLogin(username,password)){
			req.getSession().setAttribute("login", username);
			req.getRequestDispatcher("member.view").forward(req, resp);
		}
		else{
			resp.sendRedirect("index.html");
		}
	}
	
	
}
