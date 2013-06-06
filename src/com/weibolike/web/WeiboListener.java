package com.weibolike.web;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import com.weibolike.model.Account;
import com.weibolike.model.AccountDAO;
import com.weibolike.model.AccountDAOJdbcImpl;
import com.weibolike.model.BlahDAOjdbcImpl;
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
		
			Context initContext;
			try {
				initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup("java:/comp/env");
				DataSource dataSource = (DataSource) envContext.lookup("jdbc/goosip");
				arg0.getServletContext().setAttribute("USER_SERVICE", new UserService(USERS,new AccountDAOJdbcImpl(dataSource),new BlahDAOjdbcImpl(dataSource)));
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
	}
	
}
