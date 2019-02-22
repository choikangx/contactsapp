package com.sds.testprovider.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sds.testprovider.model.RequestTokenVO;
import com.sds.testprovider.model.UsersVO;
import com.sds.testprovider.service.RequestTokenService;
import com.sds.testprovider.service.UsersService;
import com.sds.testprovider.util.SessionUtil;

@Controller
@RequestMapping(value = "/authorize")
public class AuthorizeController {

	@Autowired
	private RequestTokenService requestTokenService;

	@Autowired
	private UsersService usersService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView authorizeGet(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		String oauth_token = (String) request.getParameter("oauth_token");
		System.out.println("@@@@ " + oauth_token);
		if (oauth_token != null) {
			RequestTokenVO requestTokenVO = requestTokenService.getRequestToken(oauth_token);
			if (requestTokenVO != null) {
				mav.setViewName("authorize");
				mav.addObject("requestTokenVO", requestTokenVO);
			}
		} else {
			mav.setViewName("autohrize_error");
			mav.addObject("errorMessage", "invalid oauth_token!");
		}

		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView authorizePost(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		/// 1. Query값 파싱
		String allow_deny = request.getParameter("allow_deny");
		String oauth_token = request.getParameter("oauth_token");
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");

		// 2.임시 생성된 requestToken 값 읽어오기 ( from tbl_request_token )
		RequestTokenVO tokenVO = (RequestTokenVO) requestTokenService.getRequestToken(oauth_token);
		ModelAndView mav = new ModelAndView();
		if (tokenVO == null) {
			mav.setViewName("authorize_error");
			mav.addObject("errorMessage", "유효하지 않은 토큰입니다.");
			return mav;

		}
		mav.addObject("requestTokenVO", tokenVO);
		mav.setViewName("authorize");

		if (allow_deny.equals("allow")) {
			UsersVO usersVO = null;
			if (!SessionUtil.isLoginned(session)) {
				UsersVO inputVO = new UsersVO(userid, password, "", 0);
				usersVO = usersService.selectUsers(inputVO);
				if (usersVO != null) {
					SessionUtil.loginUser(session, usersVO);

				} else {
					// 승인버튼을 눌렀으나 사용자 계정정보가 다르다면 다시 authorize페이지로 이동
					mav.addObject("loginResult", "false");
					mav.setViewName("authorize");
					return mav;
				}
			}
			// RequestTokenTable의 usersno 필드값을 어플리케이션의 접근을 허용한 사용자의 userNo로 변경.
			tokenVO.setUserNo(SessionUtil.getUserInfo(session).getUserno());
			requestTokenService.updateUserNo(tokenVO);

			// 로그인된 상태에서 app을 승인하면 callback url로 이동.
			response.sendRedirect(tokenVO.getCallback() + "?oauth_token=" + tokenVO.getRequestToken()
					+ "&oauth_verifier=" + tokenVO.getVerifier());

		} else {
			// 승인 거부 하였다면 임시 생성한 RequestToken값을 삭제하고 승인 거부 화면으로
			requestTokenService.deleteRequestToken(oauth_token);
			;
			mav.setViewName("authorize_error");
			mav.addObject("errorMessage", "사용자가 어플리케이션의 접근을 허용하지 않았습니다.");
			SessionUtil.logoutUser(session);
		}
		return mav;
	}

}
