package com.donzzul.spring.adminpage.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.donzzul.spring.payment.domain.Don;
import com.donzzul.spring.payment.service.PaymentService;

@Controller
public class AdminPageController {

	@Autowired
	private PaymentService pService;
	
	// 관리자 페이지 출력
	@RequestMapping(value="adminPage.dz")
	public ModelAndView adminPageView(ModelAndView mv) {
//		ArrayList<Don> pList = pService.selectAllDonList();
//		test();
//		if(!pList.isEmpty()) {
//			mv.addObject("pList", pList).setViewName("adminPage/adminPage");
//		} else {
			mv.addObject("msg", "목록 불러오기 실패").setViewName("adminPage/adminPage");
//		}
		
		return mv;
	}
	
	// 포인트 출력
	@RequestMapping(value="allPointAdmin.dz")
	public String allPoint() {
		
		return "";
	}
	
	public void test() {
//		SimpleDateFormat sDate = new SimpleDateFormat("yyyyMMdd");
//		HashMap<String, String> dateMap = new HashMap<String, String>();
//		dateMap.put("date1", "20210101");
//		dateMap.put("date2", "20210601");
//		ArrayList<Don> don = pService.selectAllDonList(dateMap);
////		ArrayList<Don> donCount = pService.selectAllDonListSum(dateMap);
//		System.out.println(don.toString());
//		System.out.println(donCount.toString());
	}
	
}
