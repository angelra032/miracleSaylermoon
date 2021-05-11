package com.donzzul.spring.pick.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.donzzul.spring.pick.domain.Pick;
import com.donzzul.spring.pick.service.PickService;
import com.donzzul.spring.user.domain.User;

@Controller
public class PickController {

	@Autowired
	private PickService pService;
	
	//D 찜 등록
	@RequestMapping(value="enrollPick.dz", method=RequestMethod.GET)
	public String enrollPick(@ModelAttribute Pick pick, Model model) {
		int result = pService.insertPick(pick);
		if(result > 0) {
			
			return "";
		}else {
			
			return "";
		}
	}
	
	//D 찜 해제
	@RequestMapping(value="removePick.dz", method=RequestMethod.GET)
	public String removePick(@ModelAttribute Pick pick, Model model) {
		int result = pService.deletePick(pick);
		if(result > 0) {
			return "";
		}else {
			return "";
		}
	}
	
	//A 찜 목록
	@RequestMapping(value="listPick.dz", method=RequestMethod.GET)
	public String listPick(@ModelAttribute User user) {
		List<Pick> list = pService.selectAllPick(user);
			return "";
	}
}
