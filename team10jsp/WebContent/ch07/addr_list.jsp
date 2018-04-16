<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="team10jsp.ch07.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="am" class="team10jsp.ch07.AddrManager" scope="application"></jsp:useBean>

<html>
<head>
<title>insert title here</title>
</head>
<body>
	<div align=center>
		<h2>주소록</h2><hr>
		<a href="addr_form.html">주소추가</a><p>
		<table border=1 width=500>
			<tr><td>이름</td><td>전화번호</td><td>이메일</td><td>성별</td></tr>
			
			<%
				for(AddrBean ab : am.getAddrList())
				{
			%>
			<tr>
				<td><%=ab.getUsername() %></td>
				<td><%=ab.getTel() %></td>
				<td><%=ab.getEmail() %></td>
				<td><%=ab.getSex() %></td>
			<%
				}
			%>
		</table>
	</div>

</body>
</html>