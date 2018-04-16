<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="login" class="team10jsp.ch07.LoginBean" scope="page"></jsp:useBean>
<jsp:setProperty name="login" property="*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
</head>
<body>
hello
	<center>
		<h2>로그인 예제</h2><hr>
		<%
			if(!(login.checkUser(request.getParameter("userid"),request.getParameter("passwd"))))
			{
				out.println("로그인 실패!!");
			}
			else
			{
				out.println("로그인 성공!!");
			}
		%>
		<hr>
		사용자 아이디 : <jsp:getProperty property="userid" name="login"/><br>
		사용자 패스워드 : <jsp:getProperty property="passwd" name="login"/>
	</center>
	
</body>
</html>