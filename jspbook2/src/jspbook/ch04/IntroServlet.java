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
@WebServlet(description = "처음 만드는 서블릿", urlPatterns = { "/IntroServlet" })
public class IntroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
	
		//웹브라우저 출력을 위한 PrintWriter 객체 확보
	PrintWriter out = response.getWriter();
	//HTML 형식으로 브로우저 출력 콘텐츠 작성
	out.println("<HTML>");
	out.println("<HEAD><TITLE> Team 10</TITLE></HEAD>");
	out.println("<BODY><H2>조장:서재준(13학번)</H2>");
	out.println("<img src=\"C:\\Users\\computer\\Desktop\\CS\\aa.png\" width = \"180\" height= \"180\">");
	out.println("<H2>조원:이치호(14학번)</H2>");
	out.println("<img src=\"C:\\Users\\computer\\Desktop\\CS\\bb.jpg\" width = \"180\" height= \"180\">");
	out.println("<H2>조원:김능환(14학번)</H2>");
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
