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
		String pageStr = request.getParameter("p");
		if(StringUtils.isBlank(pageStr)){
			pageStr = "1";
		}
	
		String url = "/bookmarks?page=" + pageStr + "&per_page=20";
		Map<String, Object> jsonMap= ReadabilityService.doGetMap(url, request);
		
		Map<String, Object> metaMap = (Map<String, Object>)jsonMap.get("meta");
		
		int num_pages = (int)Double.parseDouble(metaMap.get("num_pages").toString());
		
		out.println("pages : " + num_pages + " page : " + metaMap.get("page") + " & itme_count=" + metaMap.get("item_count"));
		out.println("item_count_total" + metaMap.get("item_count_total"));
		
		out.println("<br/>articles:</br>");
		List<Map<String, Object>> bookmarks = (List<Map<String, Object>>)jsonMap.get("bookmarks");
		out.println("<ol>");
		for(Map<String, Object> map : bookmarks){
			Map<String, Object> articleMap = (Map<String, Object>)map.get("article");
			
			String title = articleMap.get("title").toString();
			String id = articleMap.get("id").toString();
			
			if(StringUtils.isBlank(title)){
				title = "[BLANK]";
			}
			out.println(String.format("<li><a href=\"article?id=%s\">%s</a></li>", id, title));
		}
		out.println("</ol>");
	%>
</body>
</html>