<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>include ���þ� �׽�Ʈ</title>
</head>
<body>
   <center>
   <H3> 10�� ���� ������Ʈ ����</H3>
   
   <h2>include ���þ� �׽�Ʈ</h2>
   <hr>
   <%@include file="exercise6.jsp" %>
   <p>
   <table border=0 cellpadding=5 cellspacing=1>
      <tr>
         <td><font size=3><%@include file="exercise7.jsp" %></font></td>
         <td width="30"> &nbsp </td>
         <td><font size=3><%@include file="exercise8.jsp" %></font></td>
      </tr>
   </table>
   </center>
</body>
</html>