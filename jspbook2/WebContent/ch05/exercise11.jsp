<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>exercise11.jsp</title>
</head>
<body>

fotter.jsp에서 출력한 메세지입니다.
<HR>
<%= request.getParameter("email") %>,
<%= request.getParameter("tel") %>
</body>
</html>