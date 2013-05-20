package com.weibolike;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weibolike.model.UserService;

@WebServlet("/member.view")
public class Member extends HttpServlet{
	private final String LOGIN_VIEW = "index.html";
	private String users_path = "";
	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		
		UserService userService = (UserService) req.getServletContext().getAttribute("USER_SERVICE");
		
		String username = (String) req.getSession().getAttribute("login");
		resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
        out.println("<html>");
        out.println("<head>");
        out.println("  <meta content='text/html; charset=UTF-8' http-equiv='content-type'>");
        out.println("<title>Gossip 微网志</title>");
        out.println("<link rel='stylesheet' href='css/member.css' type='text/css'>");
        out.println("</head>");
        out.println("<body>");

        out.println("<div class='leftPanel'>");
        out.println("<img src='images/caterpillar.jpg' alt='Gossip 微网志' /><br><br>");
        
        out.println("</div>");
        out.println("<form method='post' action='message.do'>");
        out.println("分享新鲜事...<br>");
        String blabla = (String) req.getAttribute("blabla");
        if(blabla == null){
        	blabla = "";
        }
        else{
        	out.println("信息要在140个字以内");
        }
        out.println("<textarea cols='60' rows='4' name='blabla'>" +  blabla +"</textarea><br>");
        out.println("<br>");
        out.println("<button type='submit'>送出</button>");
        out.println("</form>");
        out.println("<table style='text-align: left; width: 510px; height: 88px;' border='0' cellpadding='2' cellspacing='2'>");
        out.println("<thead>");
        out.println("<tr><th><hr></th></tr>");
        out.println("</thead>");
        out.println("<tbody>");
        Map<Date,String> messages = userService.readMessage(username);
        DateFormat dateFomat = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL,Locale.CHINA);
        for(Date date : messages.keySet()){
        	out.println("<tr><td style='vertical-align:top;'>");
        	out.println(username + "<br>");
        	System.out.println(messages.get(date));
        	out.println(messages.get(date) + "<br>");
        	out.println(dateFomat.format(date));
        	out.println("<a href='delete.do?message=" + date.getTime() + "'>删除</a>");
        	out.println("<hr></td></tr>");
        }
        
        out.println("</tbody>");
        out.println("</table>");
        out.println("<hr style='width: 100%; height: 1px;'>");
        out.println("</body>");
        out.println("</html>");
        
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(req,resp);
	}
	
	
}
