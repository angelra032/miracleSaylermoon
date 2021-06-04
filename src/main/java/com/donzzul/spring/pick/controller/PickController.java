package com.donzzul.spring.pick.controller;


import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.common.Pagination;
import com.donzzul.spring.pick.domain.Pick;
import com.donzzul.spring.pick.service.PickService;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.user.domain.User;

@Controller
public class PickController {

	@Autowired
	private PickService service;
	
	//D 찜 등록
	@ResponseBody
	@RequestMapping(value="enrollPick.dz", method=RequestMethod.POST)
	public String enrollPick(@RequestParam int shopNo, @RequestParam int userNo) {
		
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		hash.put("userNo", userNo);
		hash.put("shopNo", shopNo);
		int result = service.insertPick(hash);
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	//D 찜 해제
	@ResponseBody
	@RequestMapping(value="removePick.dz", method=RequestMethod.POST)
	public String removePick(@RequestParam int pickNo, HttpServletRequest request) {
		
		int result = service.deletePick(pickNo);
		if(result > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	// 드림 마이페이지에서 삭제했다가 다시 탑 3 불러오기
	@ResponseBody
	@RequestMapping(value="myPageMainPickDelete.dz", method = RequestMethod.GET)
	public ArrayList<Pick> drmMpMainPickDelete(@RequestParam int pickNo,
												HttpSession session){
		int result = service.deletePick(pickNo);
		
		User user = (User)session.getAttribute("loginUser");
		ArrayList<Pick> list= service.dreamPickUpToThree(user.getUserNo());
		
		System.out.println(list);
		return list;
	}
	
	// 삭제 후 목록 가져오기
	@ResponseBody
	@RequestMapping(value="removeMyPagePick.dz", method=RequestMethod.POST)
	public HashMap<String, Object> removeMyPagePick(@RequestParam("pickNo") int pickNo, HttpSession session, 
									@RequestParam(value="page", required=false) Integer page) {
		
		User user = (User)session.getAttribute("loginUser");
		int userNo = user.getUserNo();
		int currentPage = (page != null) ? page : 1;
		int listCount = service.pickListCount(userNo);
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		
		List<Pick> list = service.deleteAndSelectPick(pickNo, userNo, pi);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		System.out.println(pi.getCurrentPage());
		hashMap.put("pi", pi);
		hashMap.put("list", list);
		
		if(!hashMap.isEmpty()) {
			return hashMap;
		}else {
			return null;
		}
	}
	
	
	
}
