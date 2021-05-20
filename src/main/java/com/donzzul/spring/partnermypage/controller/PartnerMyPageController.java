package com.donzzul.spring.partnermypage.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.reservation.service.ReservationService;

@Controller
public class PartnerMyPageController {

	@Autowired
	private ReservationService pService;
	
	@RequestMapping(value="partnerMyPage.dz", method=RequestMethod.GET)
	public String PartnerMyPageView(@RequestParam("userNo") int userNo, Model model) { //requestParam("userNo") int userNo
		System.out.println(userNo);
//		System.out.println(shopNo);
		// 예약목록 불러오기(
		ArrayList<Reservation> rList = pService.reservaionListByShop(userNo);
		if(!rList.isEmpty()) {
			model.addAttribute("rList", rList);
			return "partnerMyPage/partnerMyPage";
		}else {
			model.addAttribute("msg", "사업자 예약목록 조회 실패");
			return "common/errorPage";
		}
		
	}
	

}

