<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% if (session.getAttribute("access_token") == null) { 
	response.sendRedirect("index.jsp");	 
}  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	긴 URL : <input type="text" id="long" value="" /><br />
	<button id="shorten">URL 짧게!!</button>
	<hr />
	짧은 URL : <span id="short"></span><br />
	수신 데이터 : 
	<pre id="json"></pre>
	<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
	<script type="text/javascript">
	var param = { longUrl : ""  };
	$("#shorten").click(function() {
		param.longUrl = $("#long").val();
		$.get("shorten.jsp", param, function(response) {
			$("#json").html(JSON.stringify(response));	
			if (response.status_code == 200) {
				$("#short").html(response.data.url);
			} else {
				$("#short").html("오류 발생 : " + response.status_txt)
			}
		});		
	})
	</script>
</body>
</html>
	