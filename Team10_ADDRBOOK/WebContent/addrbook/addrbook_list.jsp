<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="addrbook_error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
	<link rel = "stylesheet" href="addrbook.css" type="text/css" media="screen">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>주소록 : 목록화면</title>
</head>
<body>
	<div align="center">
		<h2>주소록:목록화면</h2><hr>
		<form>
			<a href="addrbook_form.jsp">주소록 등록</a>
			<table border="1">
				<tr><th>번호</th><th>이 름</th><th>전화번호</th><th>생 일</th><th>회 사</th><th>메 모</th></tr>
				<tr>
					<td><a href="adrrbook_deit_form.jsp">1</a></td><td>홍길동</td><td>010-7697-8826</td><td>1994-12-08</td>
					<td>가천대학교</td><td>IT대학</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>