package com.sds.testprovider.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thinker.oauth.generator.TokenGenerator;
import org.thinker.oauth.parameter.AccessTokenParam;
import org.thinker.oauth.util.OAuthMsgConstants;

import com.sds.testprovider.model.AccessTokenVO;
import com.sds.testprovider.model.ConsumerVO;
import com.sds.testprovider.model.RequestTokenVO;
import com.sds.testprovider.model.UsersVO;
import com.sds.testprovider.service.ConsumerService;
import com.sds.testprovider.service.RequestTokenService;
import com.sds.testprovider.service.UsersService;

@Controller
public class AccessTokenController {

	@Autowired
	private RequestTokenService requestTokenService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private ConsumerService consumerService;

	@RequestMapping(value = "access_token")
	public ModelAndView getAccessToken(HttpServletRequest request) throws Exception {

		/// 1.파라미터 파싱
		AccessTokenParam param = new AccessTokenParam(request);
		// 1.1 db테이블ㄹ에서 consumer / requesttoken. user정보 읽음
		ConsumerVO consumerVO = consumerService.selectByConsumerKey(param.getConsumerKey());
		RequestTokenVO requestTokenVO = requestTokenService.getRequestToken(param.getRequestToken());

		UsersVO usersVO = usersService.selectUserByUserNo(requestTokenVO.getUserNo());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("access_token");

		try{
			//2. 시그니쳐 벨리데이션 하자. 유효하지 않읍면 exception
			param.validateRequestToken(requestTokenVO.getRequestTokenSecret(), 
					requestTokenVO.getVerifier(), 
					consumerVO.getConsumerSecret());
			
			//2.1 유효하다면 리퀘스트 토큰 테이블의 레코드 삭제 : 임시 토큰이니까. 
			requestTokenService.deleteRequestToken(requestTokenVO.getRequestToken());
			
			//3.accessToken 생성. 
			AccessTokenVO accessTokenVO = TokenGenerator.generateAccessToken(usersVO,  consumerVO);
			
			StringBuilder builder = new StringBuilder();
			builder.append(OAuthMsgConstants.OAUTH_TOKEN + "=" +accessTokenVO.getAccessToken()+"&");
			builder.append(OAuthMsgConstants.OAUTH_TOKEN_SECRET+"="+ accessTokenVO.getAccessTokenSecret()+"&");
			builder.append("userno="+ accessTokenVO.getUserNo()+"&");
			builder.append("userid="+ accessTokenVO.getUserID()+"&");
			
			mav.addObject("isValid",true);
			mav.addObject("message",builder.toString());
			
		    
			
			
		}catch(Exception e) {
			mav.addObject("isValid",false);
			mav.addObject("message",e.getMessage());
		}
		return mav;
	}
}
