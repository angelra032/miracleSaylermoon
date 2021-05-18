package com.donzzul.spring.dreammypage.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.reservation.service.ReservationService;

@Controller
public class DreamMyPageController {

//	@Autowired
//	private ReservationService rService;
//	
	@RequestMapping(value="dreamMyPage.dz")
	public String DreamMyPageView() {
		
		return "dreamMyPage/DreamMyPage";
	}
	
//	@RequestMapping(value="dMyPageReservationList.dz")
//	public String reservaionListByDream(@RequestParam("userNo") int userNo,
//										Model model) {
//		ArrayList<Reservation> rList;
//		return null;
//	}
	
}
