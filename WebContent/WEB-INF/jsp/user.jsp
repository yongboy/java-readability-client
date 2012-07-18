<%@page import="java.util.*"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.readability.client.ReadabilityService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user</title>
</head>
<body>
	<%
		String url = "/users/_current";
		Map<String, Object> userMap= ReadabilityService.doGetMap(url, request);
		/**
			{
		    "username": "jdoe",
		    "first_name": "John",
		    "last_name": "Doe",
		    "date_joined": "2010-10-08 12:00:17",
		    "has_active_subscription": false,
		    "reading_limit": 20
			}
		*/
		out.write(userMap.toString());
	%>
</body>
</html>