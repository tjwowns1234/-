<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>exercise14.jsp</title>
</head>
<body>
<H3> 10조 전문 프로젝트 과제</H3>

<div align="center">
<H2>스크립트릿 테스트1: 1-10까지 출력</H2>
<HR>
<%
	for(int i=1; i<=10;i++){
		out.println(i+"<BR>");
	}
%>
</div>
</body>
</html>