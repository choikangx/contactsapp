package org.thinker.oauth.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.thinker.oauth.ResourceTokenVO;
import org.thinker.oauth.TokenSender;

/**
 * Servlet implementation class TwitterHelloServlet
 */
public class TwitterResourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TwitterResourceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String AT = (String)session.getAttribute("AT");
		String ATS = (String)session.getAttribute("ATS");
		
		System.out.println("### AT : " + AT ); //AT가 숫자로 시작해야 한다. 
		System.out.println("### ATS : " + ATS );
		
		ResourceTokenVO vo = new ResourceTokenVO();
		vo.setMethod("GET");
		vo.setRequestURL(Setup.RESOURCE_URL); //사용자 개인 홈 타임라인에 접근(in twitter )
		vo.setConsumerKey(Setup.CONSUMER_KEY);
		vo.setConsumerSecretKey(Setup.CONSUMER_SECRET);
		vo.setRequestOauthToken(AT);
		vo.setRequestOauthTokenSecret(ATS);
		vo.sign(); // 시그니쳐 만들기
		TokenSender sender = new TokenSender(TokenSender.TYPE_PARAM);
		
		try {
			sender.sendHttpClient(vo);
			response.setContentType("application/json");
			response.getWriter().print(vo.getResult());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
}
