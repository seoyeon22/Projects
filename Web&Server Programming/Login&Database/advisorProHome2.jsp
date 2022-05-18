<%@ page language= "java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="advisor.*" %>
<%@ page import="java.util.ArrayList" %>
	
<%
request.setCharacterEncoding("utf-8");
int emplid = Integer.parseInt(request.getParameter("emplid"));

DatabaseBean2 manager = DatabaseBean2.getInstance();
EmployeeBean employee = manager.getEmployee(emplid);

if(employee.getName() != null)
	out.println("<h5>이름:" + employee.getName() + " 부서:" + employee.getDept() + "</h5>");

%>

