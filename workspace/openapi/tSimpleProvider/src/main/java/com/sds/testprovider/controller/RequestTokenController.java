package com.sds.testprovider.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thinker.oauth.generator.TokenGenerator;
import org.thinker.oauth.parameter.RequestTokenParam;

import com.sds.testprovider.model.ConsumerVO;
import com.sds.testprovider.model.RequestTokenVO;
import com.sds.testprovider.service.ConsumerService;
import com.sds.testprovider.service.RequestTokenService;

@Controller
public class RequestTokenController {

	@Autowired
	private ConsumerService consumerService;

	@Autowired
	private RequestTokenService requestTokenService;

	@RequestMapping(value = "request_token")
	public ModelAndView requestToken(HttpServletRequest request) throws Exception {
		
		//1.쿼리스트링 또는 Post 파라미터 파싱
		RequestTokenParam param  = new RequestTokenParam(request);
		
		// 2.테이블에서 cs정보 읽어옴
		ConsumerVO consumerVO = consumerService.selectByConsumerKey(param.getConsumerKey());
		String consumerSecret = consumerVO.getConsumerSecret();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("request_token");
		try {
			//3 시그니쳐 validation 체크 // 현재 callback url검증을 안한다.  request param을 추가해서 url검증도 추가가능.
			param.validateRequestToken(consumerSecret);
			//4. 유효하다면 RequestToken 생성 하고 tbl_request_token 테이블에 저장!!!
			RequestTokenVO tokenVO = new RequestTokenVO();
			tokenVO.setConsumerKey(consumerVO.getConsumerKey());
			tokenVO.setCallback(param.getCallback());
			TokenGenerator.generateRequestToken(tokenVO);
			requestTokenService.createRequestToken(tokenVO);
			
			String oauth_callback_confirmed = "true";
			
			StringBuilder builder = new StringBuilder();
			builder.append("oauth_token=" + tokenVO.getRequestToken() + "&");
			builder.append("oauth_token_secret=" + tokenVO.getRequestTokenSecret() + "&");
			builder.append("oauth_callback_confirmed=" + oauth_callback_confirmed);
		
			mav.addObject("isValid",true);
			mav.addObject("message",builder.toString());
			
		}catch(Exception e) {
			mav.addObject("isValid",false);
			mav.addObject("message", e.getMessage());
			
		}
		
		
		return mav;
	}
}