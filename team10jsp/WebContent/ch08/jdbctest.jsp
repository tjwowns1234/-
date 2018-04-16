<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.sql.*" import="team10jsp.ch08.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setCharacterEncoding("UTF-8");  %>
<jsp:useBean id="am" class="team10jsp.ch08.test" scope="application"></jsp:useBean>
<html>
<head>
<title>JDBC 테스트</title>
</head>
<body>
<!-- 
	작성자 : 서재준
	학번 :  2013041066 
	조 : 10조
 -->
<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	String jdbc_driver = "com.mysql.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost:3308/jjsdb";
	int num = am.setnum();
	try{
		Class.forName(jdbc_driver);
		conn = DriverManager.getConnection(jdbc_url,"root","rnfl12");
		String sql = "insert into member values(?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "mem"+Integer.toString(num));num++;
		pstmt.setString(2, request.getParameter("ID"));
		pstmt.setString(3, request.getParameter("username"));
		pstmt.setString(4, request.getParameter("sex"));
		pstmt.setString(5, request.getParameter("pnumber"));
	
		if(request.getParameter("username") != null) pstmt.executeUpdate();
	}catch(Exception e){
		System.out.println(e);
	}

%>


	<center>
		<h2>10조의 member Mysql</h2><hr>
		<form name="form1" method="POST">
			회원ID : <input type="text" name="ID">
			회원이름 : <input type="text" name="username" size="20">
			성별 : <select name="sex">
					<option value="남" selected>남</option>
					<option value="여" >여</option>
				</select>
			전화번호 : <input type="text" name="pnumber" size="20">
			<input type="submit" value="등록"> 
		</form><hr>
	</center>
	#등록목록----<p>
	
<%
	try{
		String sql = "select ID, username, sex, pnumber from member";
		pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		int i=0;
		while(rs.next())
		{
			out.println(i + " : " + rs.getString(1) + " , " + rs.getString(2)
			+ " , " + rs.getString(3)+ " , " + rs.getString(4)+"<br>");
			i++;
		}
		rs.close();
		pstmt.close();
		conn.close();
	}catch(Exception e){
		System.out.println(e);	
	}
%>

</body>
</html>