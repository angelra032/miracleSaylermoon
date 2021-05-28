package com.donzzul.spring.adminpage.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.common.Pagination;
import com.donzzul.spring.payment.domain.Don;
import com.donzzul.spring.payment.domain.DonCount;
import com.donzzul.spring.payment.service.PaymentService;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.shop.service.ShopService;
import com.donzzul.spring.user.domain.User;
import com.donzzul.spring.user.service.UserService;

@Controller
public class AdminPageController {

	@Autowired
	private PaymentService pService;
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private ShopService sService;
	
	// 관리자 페이지 출력
	@RequestMapping(value="adminPage.dz")
	public ModelAndView adminPageView(ModelAndView mv) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+2; // 이번달주소
		for(int i = 1; i >= month; i++) {}
		String monthDate = Integer.toString(year);
		monthDate = monthDate + "0" + Integer.toString(month) +"01";
		System.out.println(year);
		System.out.println(month);
		System.out.println(monthDate);
		
		// *** 포인트출력
		HashMap<String, String> dateMap = new HashMap<String, String>();
		dateMap.put("date1", "20210101");
		dateMap.put("date2", monthDate);
//		ArrayList<DonCount> donCount = pService.selectAllDonListSum(dateMap); 
//		ArrayList<Don> pList = pService.selectAllDonList(dateMap);
		
		// *** 회원출력
		ArrayList<User> userList = uService.selectUserListThree();
		
		// *** 사업자출력
		ArrayList<Shop> shopList = sService.selectAllShopListDESC();
		
		if(!userList.isEmpty() && !shopList.isEmpty()) { // !pList.isEmpty()
			mv.addObject("userList", userList).addObject("shopList", shopList).setViewName("adminPage/adminPage"); // addObject("pList", pList)
		} else {
			mv.addObject("msg", "불러올 데이터가 없습니다.").setViewName("adminPage/adminPage");
		}
		
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
	
	// 사업자관리 더보기
	@RequestMapping(value="adminAllShopList.dz")
	public ModelAndView shopListPrint(ModelAndView mv, @RequestParam(value="page", required=false) Integer page) {
		int currentPage = (page != null) ? page : 1;
//		int listCount = sService.getListCount();
//		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
//		ArrayList<Shop> shopList = sService.selectAllShopList(pi);
		ArrayList<Shop> shopList = sService.selectAllShopListDESC();
		if(!shopList.isEmpty()) {
			mv.addObject("shopList", shopList);
		} else {
			mv.addObject("msg", "불러올 데이터가 없습니다");
		}
		mv.setViewName("adminPage/adminShopList");
		return mv;
	}
	
	// 회원관리 더보기
	@RequestMapping(value="adminAllUserList.dz")
	public ModelAndView userListPrint(ModelAndView mv, @RequestParam(value="page", required=false) Integer page) {
		int currentPage = (page != null) ? page : 1;
		int listCount = uService.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<User> userList = uService.selectAllUserList(pi);
		
//		ArrayList<User> userList = uService.selectAllUserList();
		if(!userList.isEmpty()) {
			mv.addObject("userList", userList);
		} else {
			mv.addObject("msg", "불러올 데이터가 없습니다");
		}
		mv.setViewName("adminPage/adminUserList");
		return mv;
	}
	
	// 게시판관리 더보기
	
	
}
