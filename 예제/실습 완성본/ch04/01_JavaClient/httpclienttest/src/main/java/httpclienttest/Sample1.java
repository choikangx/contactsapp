package httpclienttest;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

public class Sample1 {

	public static void main(String[] args) throws HttpException, IOException {
		// TODO Auto-generated method stub
		HttpClient client = new HttpClient();
		client.getParams().setContentCharset("utf-8");
		
		String url = "http://tfactory.com:8000/contactsvc4/contacts?output=json";
		GetMethod method = new GetMethod(url);
		int status = client.executeMethod(method);
		if (status == 200) {
			String json = method.getResponseBodyAsString();
			System.out.println("## 응답 데이터 : " + json);
		} else {
			System.out.println("## 응답 오류 : 상태 코드 => " + status);
		}
	}

}
