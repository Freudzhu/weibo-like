package com.weibolike;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/register.do")
public class Register extends HttpServlet{

	private String users_path = "";
	private String SUCESS_VIEW = "success.view";
	private String ERROR_VIEW = "error.view";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
		
	}
	private void creatUser(String username, String email, String password) {
		// TODO Auto-generated method stub
		File userhome = new File(users_path + "/" + username);
        userhome.mkdir();
        try {
        	BufferedWriter writer = new BufferedWriter(new FileWriter(userhome + "/profile"));
			writer.write(email + "\t" + password);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
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
	private boolean isInValidUser(String username){
		if(username == null){
			return false;
		}
		File[] users = new File(users_path).listFiles();
		for(File user : users){
			if(user.getName().equals(username)){
				return true;
			}
		}
		return false;
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		users_path = req.getServletContext().getRealPath("/") + "/WEB-INF/user";
		String email = req.getParameter("email");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confirmedPasswd = req.getParameter("confirmedPasswd");
		List<String> errors = new ArrayList<String>();
		if(isInValidEmail(email)){
			errors.add("电子邮件的格式错误");
		}
		if(isInValidUser(username)){
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
			creatUser(username,email,password);
		}
		req.getRequestDispatcher(result_page).forward(req, resp);
	}

}
