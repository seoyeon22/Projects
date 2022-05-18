<%@ page language= "java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="advisor.DatabaseBean2" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입 처리</title>
</head>
<body>
<% request.setCharacterEncoding("utf-8");%>
<jsp:useBean id="employee" class="advisor.EmployeeBean">
	<jsp:setProperty name="employee" property="*"/>
</jsp:useBean>
<%
DatabaseBean2 manager = DatabaseBean2.getInstance();
manager.insertEmployee(employee);
response.sendRedirect("indexHome.jsp");
%>
</body>
</html>