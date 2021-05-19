package com.donzzul.spring.mzmypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.donzzul.spring.user.domain.User;

@Controller
public class MZMyPageController {

	@RequestMapping(value = "mzMyPage.dz")
	public String MZMyPageView() {
		return "mzMyPage/MZMyPage";
	}
}
