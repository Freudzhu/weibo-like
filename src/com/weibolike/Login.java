package com.weibolike;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/login.do")
public class Login extends HttpServlet{
	String userHome;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		userHome = req.getServletContext().getRealPath("/")+"/WEB-INF/user";
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if(checkLogin(username,password)){
			req.getRequestDispatcher("member.view").forward(req, resp);
		}
		else{
			resp.sendRedirect("index.html");
		}
	}

	private boolean checkLogin(String username, String password) throws IOException {
		// TODO Auto-generated method stub
		if(username!=null && password!=null){
			for(String file : new File(userHome).list()){
				if(file!=null && file.equals(username)){
					try {
						BufferedReader reader = new BufferedReader(new FileReader(new File(userHome +"/" +username + "/profile")));
						String[] spit = reader.readLine().split("\t");
						return spit[1].equals(password);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		
		}
		return false;
	}
	
}
