package com.donzzul.spring.dreammypage.controller;

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
public class DreamMyPageController {

	@Autowired
	private ReservationService rService;
	
	@RequestMapping(value="dreamMyPage.dz")
	public String DreamMyPageView() {
		
		return "dreamMyPage/DreamMyPage";
	}
	
	//꿈나무회원 마이페지이 예약목록 불러오기
	@RequestMapping(value="ListByDream.dz", method = RequestMethod.GET)
	public String reservaionListByDream(@RequestParam("userNo") int userNo,
										Model model) {
		System.out.println(userNo);
		ArrayList<Reservation> rList = rService.reservaionListByDream(userNo);
		if(!rList.isEmpty()) {
			model.addAttribute("rList",rList);
			return "dreamMyPage/DreamMyPage";
		}else {
			model.addAttribute("msg","예약목록 불러오는데 실패했지...!!");
			return "common/errorPage";
		}
	}
}

