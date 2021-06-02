package com.donzzul.spring.pick.controller;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.donzzul.spring.pick.domain.Pick;
import com.donzzul.spring.pick.service.PickService;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.user.domain.User;

@Controller
public class PickController {

	@Autowired
	private PickService service;
	
	
	//D 찜 등록
	@RequestMapping(value="enrollPick.dz", method=RequestMethod.GET)
	public String enrollPick(@RequestParam int shopNo, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		hash.put("userNo", user.getUserNo());
		hash.put("shopNo", shopNo);
		int result = service.insertPick(hash);
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	//D 찜 해제
	@RequestMapping(value="removePick.dz", method=RequestMethod.GET)
	public String removePick(@RequestParam int pickNo, HttpServletRequest request) {
		
		int result = service.deletePick(pickNo);
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	
}
