<%@page pageEncoding="UTF-8"
   import="java.util.*, java.text.*,com.weibolike.model.*"%>
   <% 
   		UserService userService = (UserService) request.getServletContext().getAttribute("USER_SERVICE");	
		String username = (String) request.getSession().getAttribute("login");
   %>
<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>
<html>
<head>
	<meta content='text/html; charset=UTF-8' http-equiv='content-type'>
	<title>Gossip 微网志</title>
	<link rel='stylesheet' href='css/member.css' type='text/css'>
</head>
<body>
	<div class='leftPanel'>
		<img src='images/caterpillar.jpg' alt='Gossip 微网志' /><br><br>
		<a href='logout.do?username="${ sessionScope.login }'>
                            登出 ${ sessionScope.login }</a>
	</div>
	<form method='post' action='message.do'>
	分享新鲜事...<br>
		<%
		String blabla = (String) request.getAttribute("blabla");
		if(blabla == null){
			blabla = "";
		}
		else{
		%>
		信息要在140个字以内
		<% 
		}
		%>
	<textarea cols='60' rows='4' name='blabla'><%= blabla%></textarea><br>
	<br>
	<button type='submit'>送出</button>
	</form>
	<table style='text-align: left; width: 510px; height: 88px;' border='0' cellpadding='2' cellspacing='2'>
		<thead>
		<tr><th><hr></th></tr>
		</thead>
		<tbody>
		<%
			List<Blah> blahs=(List<Blah>)request.getAttribute("blahs");
		    DateFormat dateFomat = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL,Locale.CHINA);
		    for(Blah blah : blahs){
		 %>
		    	<tr>
			    	<td style='vertical-align:top;'>
			    	<%=blah.getUsername()%><br>
			    	<%=blah.getTxt()%> <br>
			    	<%=dateFomat.format(blah.getDate())%>
			    	<a href='delete.do?message=<%=blah.getDate().getTime()%>'>删除</a>
			    	<hr>
			    	</td>
		    	</tr>
		<%
		    }
		%>
		</tbody>
	</table>
	<hr style='width: 100%; height: 1px;'>
	</body>
	</html>