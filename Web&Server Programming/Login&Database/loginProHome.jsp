<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="advisor.DatabaseBean2" %>

<%
	request.setCharacterEncoding("utf-8");
	int id = Integer.parseInt(request.getParameter("id"));
	String passwd  = request.getParameter("passwd");

	DatabaseBean2 manager = DatabaseBean2.getInstance();
	int check = manager.userCheck(id, passwd);
	out.println(check);
%>