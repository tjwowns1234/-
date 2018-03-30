package jspbook.ch04;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class IntroServlet
 */
@WebServlet(description = "ó�� ����� ����", urlPatterns = { "/IntroServlet" })
public class IntroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
	
		//�������� ����� ���� PrintWriter ��ü Ȯ��
	PrintWriter out = response.getWriter();
	//HTML �������� ��ο��� ��� ������ �ۼ�
	out.println("<HTML>");
	out.println("<HEAD><TITLE> Team 10</TITLE></HEAD>");
	out.println("<BODY><H2>����:������(13�й�)</H2>");
	out.println("<img src=\"C:\\Users\\computer\\Desktop\\CS\\aa.png\" width = \"180\" height= \"180\">");
	out.println("<H2>����:��ġȣ(14�й�)</H2>");
	out.println("<img src=\"C:\\Users\\computer\\Desktop\\CS\\bb.jpg\" width = \"180\" height= \"180\">");
	out.println("<H2>����:���ȯ(14�й�)</H2>");
	out.println("<img src=\"C:\\Users\\computer\\Desktop\\CS\\cc.jpg\" width = \"180\" height= \"180\">");
	out.println("</BODY></HTML>");
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
