package com.donzzul.spring.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// 로그인 처리를 담당하는 인터셉터
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("LoginInterceptor");
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("loginUser");
		Object dest = session.getAttribute("dest");

		response.sendRedirect(dest != null ? (String) dest : "/");
	}
}
