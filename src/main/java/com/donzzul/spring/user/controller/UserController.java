package com.donzzul.spring.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.donzzul.spring.user.domain.User;
import com.donzzul.spring.user.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	//로그인
	@RequestMapping(value = "#", method = RequestMethod.POST)
	public String userLogin(HttpServletRequest request, @ModelAttribute User user, Model model) {
		return "";
	}
	
	//로그아웃
	@RequestMapping(value = "", method = RequestMethod.GET) 
	public String userLogout(HttpServletRequest request) {
		return "";
	}
	
	// 회원가입폼
	@RequestMapping(value = "", method = RequestMethod.GET) 
	public String enrollView() {
		return "";
	}
	
	// 회원등록
	@RequestMapping(value = "", method = RequestMethod.POST) 
	public String userRegister() {
		return "";
	}
}
 