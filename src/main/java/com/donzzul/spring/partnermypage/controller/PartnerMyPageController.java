package com.donzzul.spring.partnermypage.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.donzzul.spring.payment.service.PaymentService;
import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.reservation.service.ReservationService;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.user.domain.User;

@Controller
public class PartnerMyPageController {

	@Autowired
	private ReservationService rService;
	
	@Autowired
	private PaymentService pService;
	
	// 사업자 마이페이지 출력
	@RequestMapping(value="partnerMyPage.dz", method=RequestMethod.GET)
	public String PartnerMyPageView(HttpSession session, Model model) { //@RequestParam("userNo") int userNo, Model model, requestParam("userNo") int userNo
		
		User loginUser = (User)session.getAttribute("loginUser"); // 로그인세션(사업자)
//		loginUser.toString();
		int userNo = loginUser.getUserNo();
	
		Shop myShop = pService.selectMyShop(userNo); // 사업자 가게 불러오기
		System.out.println("userNo"+userNo);
		
		int shopNo = myShop.getShopNo();
		System.out.println("shopNo" + shopNo);
		
		// 예약관리 불러오기
		ArrayList<Reservation> rList = rService.rListByShopUpToThree(shopNo);
		System.out.println(rList);
		
		if(!rList.isEmpty()) {
			model.addAttribute("rList", rList);
			return "partnerMyPage/partnerMyPage";
		}else {
			return "common/errorPage";
		}
		
	}
	
	// 예약 상태(업데이트)
	
	// 예약 더보기(풀 리스트)
	
	// 내가 쓴 글 목록(띄우기, 수정, 삭제)
	
	// 내가 쓴 글 목록 더보기
	
	
	
	// 환급신청
	
	// 가게정보 등록
	
	// 회원정보 수정
	
	// 회원탈퇴 요청
	

}

