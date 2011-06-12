<%@page contentType="text/html" pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>SWAG</title>
	<base href="http://<%=request.getServerName()%>:8080/">
	<script data-main="js/Main" src="js/require-jquery.js"></script>
	<style>
		@import "css/swag.css";
		@import "css/ui-lightness/jquery-ui-1.8.6.custom.css";
	</style> 
	<link rel="shortcut icon" href="img/favicon.ico">
</head>
<body>
    <h1>Hello World!</h1>
    <%@ include file="elements/menu.jsp" %>
    <%@ include file="elements/iewarning.jsp" %>
    
    <div id="content" style="width:800px"></div>
</body>
</html> 
