<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.opensg.oauth2.client.*" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.httpclient.*" %>
<%@ page import="org.apache.commons.httpclient.methods.*"%>
<%
	String queryString = request.getQueryString();
	HashMap<String,String> map = OAuth2ClientUtil.getMapFromParamString(queryString);
	//code는 이전 행에서 이미 획득 : callback.jsp?code=xxxxxx
	map.put("client_id", Settings.CLIENT_ID);
	map.put("client_secret", Settings.CLIENT_SECRET);
	map.put("redirect_uri", Settings.REDIRECT_URI);
	map.put("grant_type", "authorization_code");
	
	String url = Settings.ACCES_TOKEN_URL;
	HttpClient client = new HttpClient();
	client.getParams().setContentCharset("utf-8");
	PostMethod method = new PostMethod(url);
	method.addParameter("client_id", map.get("client_id"));
	method.addParameter("client_secret", map.get("client_secret"));
	method.addParameter("redirect_uri", map.get("redirect_uri"));
	method.addParameter("grant_type", map.get("grant_type"));
	method.addParameter("code", map.get("code"));

	int status = client.executeMethod(method);
	String result= "";
	String body="";
	if (status == 200) {
		body = method.getResponseBodyAsString();
		HashMap<String,String> tokenMap = OAuth2ClientUtil.getMapFromParamString(body);
		session.setAttribute("access_token", tokenMap.get("access_token"));
		response.sendRedirect("main.jsp");
	} else {
		result = "인증 실패!!";
	}
%>
<%=result %>
	