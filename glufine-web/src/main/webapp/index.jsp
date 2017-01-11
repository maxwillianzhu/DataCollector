<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
<title></title>
<link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" />
<link href="<%=path %>/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link href="<%=path %>/css/index.css" rel="stylesheet" />
</head>
<script src="<%=path %>/static/js/jquery-1.7.2.js"></script>

</head>
<body>
	welcome
</body>

</html>