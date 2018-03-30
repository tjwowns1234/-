<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>exercise12.jsp</title>
</head>
<body>
<H3> 10조 전문 프로젝트 과제</H3>

<h2>exercise12.jsp에서 exercise11.jsp 호출</h2>
<HR>
forward_action.jsp에서 호출된 메시지입니다.<BR>
<jsp:forward page="exercise11.jsp">
<jsp:param name="email" value="test@test.net" />
<jsp:param name="tel" value="000--000-0000"/>
</jsp:forward>
</body>
</html>