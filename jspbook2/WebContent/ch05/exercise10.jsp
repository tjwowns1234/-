<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title> include_action.jsp</title>
</head>
<body>
<H3> 10조 전문 프로젝트 과제</H3>

<h2>include_action.jsp에서 footer.jsp 호출</h2>
<HR>
include_action.jsp에서 호출한 메시지입니다.<BR>
<jsp:include page="exercise11.jsp">
<jsp:param name="email" value="test@test.net" />
<jsp:param name="tel" value="000--000-0000"/>
</jsp:include>
</body>
</html>