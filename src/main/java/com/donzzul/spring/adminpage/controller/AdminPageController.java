package com.donzzul.spring.adminpage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminPageController {

	// 관리자 페이지 출력
	@RequestMapping(value="adminPage.dz")
	public String adminPageView() {
		
		return "adminPage/adminPage";
	}
}
