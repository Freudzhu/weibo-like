package com.weibolike.web;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.weibolike.model.UserService;

@WebListener
public class WeiboListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		String USERS = arg0.getServletContext().getInitParameter("USERS");
		arg0.getServletContext().setAttribute("USER_SERVICE", new UserService(USERS));
		
	}
	
}
