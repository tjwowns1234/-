<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="team10jsp.ch07.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="addr" class="team10jsp.ch07.AddrBean"></jsp:useBean>
<jsp:setProperty property="*" name="addr"/>
<jsp:useBean id="am" class="team10jsp.ch07.AddrManager" scope="application"></jsp:useBean>
<% am.add(addr); %>
<html>
<head>
<title></title>
</head>
<body>
	<center>
		<h2>등록내용</h2>
		이름 : <jsp:getProperty property="username" name="addr"/><p>
		전화번호 : <%=addr.getTel() %>
		이메일 : <%=addr.getEmail() %>
		성별 : <%=addr.getSex() %>
		<hr>
		<a href="addr_list.jsp">목록 보기</a>
	</center>
</body>
</html>