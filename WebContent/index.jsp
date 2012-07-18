<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>readability's java api example</title>
<style type="text/css">
div{margin:50px;}
</style>
</head>
<body>

	<div>This is a simple java wrapper for readability's rest api</div>

	<div>
		You must edit the com.readability.client.ReadabilityConstants.java
		file, input your key and secre at first.<br />
		<pre>
			protected static final String consumerKey = "your key here";
			protected static final String consumerSecret = "your secret here";
		</pre>
	</div>

	<div>
		Demos :
		<ol>
			<li><a href="readability/bookmarks">bookmarks</a></li>
			<li><a href="readability/user">user</a></li>
		</ol>
	</div>

	<div>More usage, please read the source and the readability's
		rest api(http://www.readability.com/developers/api)</div>

</body>
</html>