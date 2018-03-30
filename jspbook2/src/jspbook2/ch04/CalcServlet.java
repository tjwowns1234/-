package jspbook2.ch04;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.PrintWriter;

/**
/**
 * Servlet implementation class CalcServlet
 */
@WebServlet(description = "Calc1 ����", urlPatterns = { "/CalcServlet" })
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
		// TODO Auto-generated method stub
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub		
			int num1, num2;
			int result;
			String op;
			
			res.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = res.getWriter();
			
			num1 = Integer.parseInt(req.getParameter("num1"));
			num2 = Integer.parseInt(req.getParameter("num2"));
			op = req.getParameter("operator");
			
			result = calc(num1,num2,op);

			out.println("<HTML>");
			out.println("<HEAD><TITLE>����</TITLE></HEAD>");
		out.println("<BODY><center>");
		out.println("<H2>�����</H2>");
		out.println("<HR>");
		out.println(num1+" "+op+" "+num2+" = "+result);
		out.println("</BODY></HTML>");
	}
		//���� ��� ����� �����ϴ� �޼���
		public int calc(int num1, int num2, String op) {
			int result = 0;
			if(op.equals("+")) {
				result = num1 + num2;
			}
			else if(op.equals("-")) {
				result = num1 - num2;
			}
			else if(op.equals("*")) {
				result = num1 * num2;
			}
			else if(op.equals("/")) {
				result = num1 / num2;
			}
			return result;
			
		}
}
	