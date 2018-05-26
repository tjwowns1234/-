package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.BoardCommand;
import com.service.BoardDeleteCommand;
import com.service.BoardListCommand;
import com.service.BoardPageCommand;
import com.service.BoardReplyCommand;
import com.service.BoardReplyUICommand;
import com.service.BoardRetrieveCommand;
import com.service.BoardSearchCommand;
import com.service.BoardUpdateCommand;
import com.service.BoardWriteCommand;


@WebServlet("*.do")
public class BoardFrontController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String com = requestURI.substring(contextPath.length());

		
		BoardCommand command  = null;
		String nextPage = null;
		
		if(com.equals("/list.do")){
			command = new BoardListCommand();
			command.execute(request, response);
			nextPage = "list.jsp";
		}
		if(com.equals("/writeui.do")){
			nextPage = "write.jsp";
		}
		if(com.equals("/write.do")){
			command = new BoardWriteCommand();
			command.execute(request, response);
			nextPage = "list.do";
		}
		if(com.equals("/retrieve.do")){
			command = new BoardRetrieveCommand();
			command.execute(request, response);
			nextPage = "retrieve.jsp";
		}	
		
		if(com.equals("/update.do")){
			command = new BoardUpdateCommand();
			command.execute(request, response);
			nextPage = "list.do";
		}	
		
		if(com.equals("/delete.do")){
			command = new BoardDeleteCommand();
			command.execute(request, response);
			nextPage = "list.do";
		}	
		
		if(com.equals("/search.do")){
			command = new BoardSearchCommand();
			command.execute(request, response);
			nextPage = "list.jsp";
		}	
		if(com.equals("/replyui.do")){
			command = new BoardReplyUICommand();
			command.execute(request, response);
			nextPage = "reply.jsp";
		}	
		if(com.equals("/reply.do")){
			command = new BoardReplyCommand();
			command.execute(request, response);
			nextPage = "list.do";
		}	
		if(com.equals("/list.do")){
			command = new BoardPageCommand();
			command.execute(request, response);
			nextPage = "listPage.jsp";
		}	
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);

	}//

}
