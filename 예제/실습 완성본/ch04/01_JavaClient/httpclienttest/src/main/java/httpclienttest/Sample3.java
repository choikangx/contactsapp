package httpclienttest;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

public class Sample3 {

	public static void main(String[] args) throws HttpException, IOException {
		// TODO Auto-generated method stub
		HttpClient client = new HttpClient();
		client.getParams().setContentCharset("utf-8");
		
		String url = "http://tfactory.com:8000/contactsvc4/contacts";
		PostMethod method = new PostMethod(url);
		method.setParameter("name", "ȫ�浿");
		method.setParameter("tel", "010-3434-5678");
		method.setParameter("address", "���ֵ�");
		
		int status = client.executeMethod(method);
		if (status == 200) {
			String json = method.getResponseBodyAsString();
			System.out.println("## ���� ������ : " + json);
		} else {
			System.out.println("## ���� ���� : ���� �ڵ� => " + status);
		}
	}

}
