package com.donzzul.spring.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.donzzul.spring.user.domain.User;
import com.donzzul.spring.user.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	// 회원가입폼
	@RequestMapping(value = "enrollView.dz", method = RequestMethod.GET) 
	public String enrollView() {
		return "user/userJoin";
	}
	
	// 회원등록
	@RequestMapping(value = "userRegister.dz", method = RequestMethod.POST) 
	public String userRegister(@ModelAttribute User user, 
			Model model) {
		int result = service.insertUser(user);
		if (result>0) {
			return "";
		}else {
			return "";
		}
	}
	
	//로그인 뷰
	@RequestMapping(value = "loginView.dz", method = RequestMethod.GET) 
	public String loginView() {
		return "user/userLogin";
	}
	
	//로그인
	@RequestMapping(value = "login.dz", method = RequestMethod.POST)
	public String userLogin(HttpServletRequest request, @ModelAttribute User user, Model model) {
		User uOne = new User();
		User loginUser = service.loginUser(uOne);
		if (loginUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			return "";
		}else {
			model.addAttribute("msg", "로그인 실패");
			return "";
		}
	}
	
	// 카카오 로그인
	@RequestMapping(value = "kakaoLogin.dz", method = RequestMethod.POST)
	public String kakaoLogin(HttpServletRequest request, @ModelAttribute User user, Model model) {
		User uOne = new User();
		User loginUser = service.loginUser(uOne);
		if (loginUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			return "";
		}else {
			model.addAttribute("msg", "로그인 실패");
			return "";
		}
	}
	
	//네이버 로그인
	@RequestMapping(value = "NaverLogin.dz", method = RequestMethod.POST)
	public String NaverLogin(HttpServletRequest request, @ModelAttribute User user, Model model) {
		return "";
	}
	
	//로그아웃
	@RequestMapping(value = "logout.dz", method = RequestMethod.GET) 
	public String userLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "";
	}
	
	
	//회원정보조회
	@RequestMapping(value = "myINfo.dz", method = RequestMethod.GET)
	public String myINfoView() {
			return "";
		
	}
		
	// 정보수정
	@RequestMapping(value = "userUpdate.dz", method = RequestMethod.POST)
	public String userUpdate(@ModelAttribute User user,
								Model model,
								HttpServletRequest request) {
		HttpSession session = request.getSession();
		int result = service.updateUser(user);
		if (result > 0) {
			session.setAttribute("loginUser", user);
			return "";
		}else {
			return "";
		}
	}
	
	//회원탈퇴
	@RequestMapping(value = "userDelete.dz", method = RequestMethod.GET)
	public String userDelete(@RequestParam("userId") String userId, 
								Model model) {
		// 회원탈퇴를 하고 나서 세션파괴를 하지 않으면 
		// 로그인한 상태가 유지되므로 세션파괴를 해줘야함.
		int result = service.deleteUser(userId);
		if (result>0) {
			return "redirect:logout.dz";
		}else {
			return "";
		}
	}
	
	//아이디 중복 검사
	@ResponseBody //반드시 적어줘야함 jsp에서 매개변수로 받아서쓰게 하려면 필요
	@RequestMapping(value = "dupId.kh", method = RequestMethod.GET)
	public String idDuplicateCheck(@RequestParam("userId") String userId) {
		return service.checkIdDup(userId)+""; //@ResponseBody를 적으면 /WEB-INF/views어쩌구 안붙여주고 바로 보낼수 있다. printWriter를 안써도 됨
	}
	
	//아이디 찾기 폼
	@RequestMapping(value = "findIdView.dz", method = RequestMethod.GET)
	public String findIdView() {
		return "";
	}
	
	//아이디 찾기
	@RequestMapping(value = "findId.dz", method = RequestMethod.POST)
	public String findId(@RequestParam("userName") String userName,
						@RequestParam("userEmail") String userEmail) {
		return service.findId(userName, userEmail);
	}
	
	//비밀번호 찾기 폼
	@RequestMapping(value = "findPwView.dz", method = RequestMethod.GET)
	public String findPwView() {
		return "";
	}
	
	//메일 보내기
	@ResponseBody
	@RequestMapping(value = "sendEmail.dz", method = RequestMethod.POST)
	public String sendEmail(@RequestParam("userEmail") String userEmail,
							@RequestParam("userId") String userId,
							@RequestParam("pwCode") String pwCode) {
		return "";
	}
	
	// 비밀번호 재설정 폼
	@RequestMapping(value = "resetPwView.dz", method = RequestMethod.GET)
	public String resetPwView() {
		return "";
	}
	
	// 비밀번호 재설정
	public String resetPw(@RequestParam("userId") String userId,
						@RequestParam("userEmail") String userEmail) {
		return service.resetPw(userId, userEmail);
	}
	
}