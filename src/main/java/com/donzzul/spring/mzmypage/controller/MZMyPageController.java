package com.donzzul.spring.mzmypage.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.user.domain.User;

@Controller
public class MZMyPageController {

	// 마이페이지 메인
	@RequestMapping(value = "mzMyPage.dz")
	public String MZMyPageView() {
		
//		ArrayList<Reservation> rList = Service.selectListById(userId);
		return "mzMyPage/MZMyPage";
	}
	
	
	// 예약페이지보기
	@RequestMapping(value = "mzReservationList.dz")
	public String MZReservationListView() {
		
		return "mzMyPage/MZReservation";
	}
}
