package com.sds.testprovider.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.thinker.oauth.parameter.OAuthTokenParam;
import org.thinker.oauth.util.OAuthException;

import com.sds.testprovider.model.ConsumerVO;
import com.sds.testprovider.model.UsersVO;
import com.sds.testprovider.service.ConsumerService;
import com.sds.testprovider.service.UsersService;

@Service
public class OauthValidateInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UsersService usersService;
	@Autowired
	private ConsumerService consumerService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub

		OAuthTokenParam param = new OAuthTokenParam(request);
		long userNo = param.getUserNo();
		String consumerKey = param.getConsumerKey();

		UsersVO usersVO = usersService.selectUserByUserNo(userNo);
		ConsumerVO consumerVO = consumerService.selectByConsumerKey(consumerKey);

		try {
			param.validateRequestToken(consumerVO.getConsumerSecret(), usersVO.getPassword());
		}catch(OAuthException e) {
			throw e;
		}
		
		request.setAttribute("user", usersVO);
		// true면 실행 false 실행시 아무것도 안함.
		// return super.preHandle(request, response, handler);
		return true;
	}

}
