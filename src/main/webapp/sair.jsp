<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="interfaces.ITokenServico" %>
<%@ page import="servico.TokenServico" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<%
		ITokenServico token = new TokenServico();
		token.zerarCookie(request, response);
		response.sendRedirect("index.jsp");

	%>
</body>
</html>

