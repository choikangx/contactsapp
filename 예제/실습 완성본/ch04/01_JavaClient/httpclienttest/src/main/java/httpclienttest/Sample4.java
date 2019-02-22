package httpclienttest;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class Sample4 {

	public static void main(String[] args) throws HttpException, IOException {
		HttpClient client = new HttpClient();
		client.getParams().setContentCharset("utf-8");
		
		String url = "http://tfactory.com:8000/contactsvc4/contacts";
		PostMethod method = new PostMethod(url);
		method.setRequestHeader("Content-type", "application/json");
		String jsonRequest = "{\"name\":\"이몽룡\",\"tel\":\"010-3434-7878\",\"address\":\"전북 남원\"}";
		StringRequestEntity requestEntity = new StringRequestEntity(jsonRequest, "application/json", "UTF-8");
		method.setRequestEntity(requestEntity);
		
		int status = client.executeMethod(method);
		if (status == 200) {
			String json = method.getResponseBodyAsString();
			System.out.println("## 응답 데이터 : " + json);
		} else {
			System.out.println("## 응답 오류 : 상태 코드 => " + status);
		}// TODO Auto-generated method stub

	}

}
