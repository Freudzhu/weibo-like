package com.weibolike.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(
	urlPatterns = {"/delete.do","/logout.do","/message.do","/member.view"},
	initParams = {@WebInitParam(name = "LOGIN_VIEW",value="index.html")}
)
public class MemberFilter implements Filter {
	private String LOGIN_VIEW;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)arg0;
		if(req.getSession().getAttribute("login")!=null){
			arg2.doFilter(arg0, arg1);
		}
		else{
			HttpServletResponse resp = (HttpServletResponse)arg1;
			resp.sendRedirect(LOGIN_VIEW);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		this.LOGIN_VIEW = arg0.getInitParameter("LOGIN_VIEW");
	}

}
