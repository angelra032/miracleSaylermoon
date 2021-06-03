package com.donzzul.spring.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// 로그인 처리를 담당하는 인터셉터
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	
	private void saveDest(HttpServletRequest req) {
		String uri = req.getRequestURI();
		String query = req.getQueryString();
		System.out.println("uri>>>" + uri + ", query>>>" + query);
		// 192.168.0.96/sample/delete?id=1234

		if (query == null)
			query = "";
		else
			query = "?" + query;

		req.getSession().setAttribute("dest", uri + query);
	}
	
	// preHandle() : 컨트롤러보다 먼저 수행되는 메소드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		System.out.println("========< 전처리 >==========");

		// 로그인 사용자 인증!! (만약, 로그인 하였다면 login키:success데이터)
		HttpSession session = request.getSession();
		Object login = session.getAttribute("loginUser");

		if (login == null || !login.equals("success")) {
			saveDest(request);// 진행하려고 했던 URL을 세션에 저장!!
			response.sendRedirect("/loginView.dz");
			return false;
		}
		
		// preHandle의 return은 '컨트롤러 요청 uri로 가도 되냐 안되냐'를 허가하는 의미.
		// 따라서 true로 하면 컨트롤러 uri로 가게 됨.
		return true;
		// return true; -----> 매핑URL실행O (계속진행)
		// return false; -----> 매핑URL실행X (실행정지)
	}
	
	// 컨트롤러가 수행되고 화면이 보여지기 직전에 수행되는 메소드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("========< 후처리 >==========");
	}
}
