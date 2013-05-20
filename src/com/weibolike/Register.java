package com.weibolike;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weibolike.model.UserService;
@WebServlet("/register.do")
public class Register extends HttpServlet{

	private String SUCESS_VIEW = "success.view";
	private String ERROR_VIEW = "error.view";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
		
	}
	
	private boolean isInvalidPassword(String password, String confirmedPasswd) {
		// TODO Auto-generated method stub
		return password == null || confirmedPasswd==null || password.length() < 6 || password.length() > 16 || !password.equals(confirmedPasswd);
	}
	private boolean isInValidEmail(String email){
        return email == null
                || !email.matches("^[_a-z0-9-]+([.]"
                        + "[_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$");
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");
		String email = req.getParameter("email");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confirmedPasswd = req.getParameter("confirmedPasswd");
		List<String> errors = new ArrayList<String>();
		if(isInValidEmail(email)){
			errors.add("电子邮件的格式错误");
		}
		if(userService.isInValidUser(username)){
			errors.add("用户名为空或者用户名已经存在");
		}
		if(isInvalidPassword(password,confirmedPasswd)){
			errors.add("密码格式不对或者密码和确认密码不一致");
		}
		String result_page = ERROR_VIEW;
		if(!errors.isEmpty()){
			result_page = ERROR_VIEW;
			req.setAttribute("errors", errors);
		}else{
			result_page = SUCESS_VIEW;
			userService.creatUser(username,email,password);
		}
		req.getRequestDispatcher(result_page).forward(req, resp);
	}

}
