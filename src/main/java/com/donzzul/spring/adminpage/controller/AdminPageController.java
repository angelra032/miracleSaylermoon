package com.donzzul.spring.adminpage.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.donzzul.spring.payment.domain.Don;
import com.donzzul.spring.payment.domain.DonCount;
import com.donzzul.spring.payment.service.PaymentService;

@Controller
public class AdminPageController {

	@Autowired
	private PaymentService pService;
	
	// 관리자 페이지 출력
	@RequestMapping(value="adminPage.dz")
	public ModelAndView adminPageView(ModelAndView mv) {
		//Calendar cal = Calendar.getInstance();
		//int year = cal.get(Calendar.YEAR);
		//int month = cal.get(Calendar.MONTH)+1; // 이번달주소
		//for(int i = 1; i >= month; i++) {}
		//String monthDate = Integer.toString(year+month)+"01";
		
		HashMap<String, String> dateMap = new HashMap<String, String>();
		dateMap.put("date1", "20210101");
		dateMap.put("date2", "20210601");
//		ArrayList<DonCount> donCount = pService.selectAllDonListSum(dateMap);
//		ArrayList<Don> pList = pService.selectAllDonList();
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
	
	public void test(String monthDate) {
//		HashMap<String, String> dateMap = new HashMap<String, String>();
//		dateMap.put("date1", "20210101");
//		dateMap.put("date2", "20210601");
//		ArrayList<Don> don = pService.selectAllDonList(dateMap);
//		ArrayList<DonCount> donCount = pService.selectAllDonListSum(dateMap);
//		System.out.println(don.toString());
//		System.out.println(donCount.toString());
	}
	
}
