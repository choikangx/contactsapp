package httpClient_Test;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class Sample1 {

	public static void main(String[] args) throws HttpException, IOException{
		
		HttpClient client = new HttpClient();
		client.getParams().setContentCharset("UTF-8");
		
		String url = "http://70.12.113.130:8000/contactsvc1/contacts";
		
		GetMethod method = new GetMethod(url);
		method.addRequestHeader("Accept" , "application/json");
		
		//int status = client.executeMethod(method);
		
		// 데이터 인서트
		PostMethod method2 = new PostMethod(url);
		method2.setRequestHeader("Content-type","application/json");
		String jsonRequest="{\"name\":\"최광섭\" , \"tel\":\"1234-1234-1234\", \"address\":\"서울시\" }";
		StringRequestEntity entity = new StringRequestEntity(jsonRequest,"application/json", "UTF-8");
		
		method2.setRequestEntity(entity);
		int status2 = client.executeMethod(method2);
		if(status2==200) {
			String json = method2.getResponseBodyAsString();
			System.out.println("##응답데이터 : " + json);
		}else {
			System.out.println("## status Error " + status2);
		}
		
				
		
	}
}