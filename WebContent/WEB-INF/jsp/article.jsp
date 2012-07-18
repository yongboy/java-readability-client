<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.util.*"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.readability.client.ReadabilityService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>choose</title>
</head>
<body>
	<%
		String id = request.getParameter("id");
		String url = "/articles/" + id;
		Map<String, Object> articleMap= ReadabilityService.doGetMap(url, request);
		
		String title = articleMap.get("title").toString();
		if(StringUtils.isBlank(title)){
			title = "[BLANK]";
		}
		String content = articleMap.get("content").toString();
	%>
	<h2><%=title %></h2>
	<div>
		<%=content %>
	</div>
</body>
</html>