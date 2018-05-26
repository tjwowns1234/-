<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html;charset=UTF-8"); %>
<%@ page import="com.entity.PageTO" %>

<%
		PageTO to  = ( PageTO)request.getAttribute( "page" );		
		int curPage = to.getCurPage();
		int perPage = to.getPerPage();
		int totalCount  = to.getTotalCount();
		int totalPage = totalCount / perPage;  // 보여줄 페이지 번호개수
		if( totalCount  %  perPage  != 0 ) totalPage++;

		for( int i = 1 ; i <=  totalPage ; i++){

          if(  curPage == i ){
			 out.print( "<font size=10 color='red'>"+i +"</font>" );				
		  }else{
			out.print( "<a href='list.do?curPage="+i+"'>"+i  +"</a>&nbsp;" );
		  }//end if
		}
%>
		