package com.donzzul.spring.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// 로그인 처리를 담당하는 인터셉터
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	
	// preHandle() : 컨트롤러보다 먼저 수행되는 메소드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		// session객체를 가져옴
		HttpSession session = request.getSession();
		// login처리를 담당하는 사용자 정보를 담고 있는 객체를 가져옴
		Object obj = session.getAttribute("loginUser");
		
		if (obj == null) {
			// 로그인이 안되어 있는 상태이로 로그인폼으로 다시 돌려보냄(redirect)
			response.sendRedirect("loginView.dz");
			return false; // 더이상 컨트롤러 요청으로 가지 않도록 false로 반환함
		}
		
		// preHandle의 return은 '컨트롤러 요청 uri로 가도 되냐 안되냐'를 허가하는 의미.
		// 따라서 true로 하면 컨트롤러 uri로 가게 됨.
		return true;
	}
	
	// 컨트롤러가 수행되고 화면이 보여지기 직전에 수행되는 메소드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
}