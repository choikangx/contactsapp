<%@ page language="java" contentType="application/json; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.opensg.oauth2.client.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.httpclient.*" %>
<%@ page import="org.apache.commons.httpclient.methods.*"%>
<%
	if (session.getAttribute("access_token") == null) {
		throw new Exception("access_token is null!!");	
	}
	
	String access_token = (String)session.getAttribute("access_token");
	String longUrl = request.getParameter("longUrl");
	HashMap<String,String> map = new HashMap<String,String>();
	map.put("access_token", access_token);
	map.put("longUrl", longUrl);
	String param = OAuth2ClientUtil.getParamStringFromMap(map);
	
	String url = Settings.SHORTEN_URL_API_URL + "?" + param;
	HttpClient client = new HttpClient();
	client.getParams().setContentCharset("utf-8");
	GetMethod method = new GetMethod(url);
	
	int status = client.executeMethod(method);
	String result= "";
	if (status == 200) {
		result = method.getResponseBodyAsString();
	} else {
		result = "{ \"status\" : \"오류 발생 : " + status + "\" }";
	}	
%>
<%=result %>
	