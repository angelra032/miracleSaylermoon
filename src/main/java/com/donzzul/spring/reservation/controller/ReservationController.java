package com.donzzul.spring.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;

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

import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.reservation.service.ReservationService;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.user.domain.User;

@Controller
public class ReservationController {

	@Autowired
	private ReservationService service;
	//@RequestParam -> 화면에서 가져오는 하나의 값
	//@ModelAttribute
	
	// 노쇼도 생각하자
	@RequestMapping(value="reservationView.dz")
	public String reservationView(Model model,
								HttpServletRequest request) {
		HttpSession session = request.getSession();
		User userOne = (User)session.getAttribute("loginUser");

		Shop shop = new Shop();
		shop.setShopNo(3);
		shop.setStartTime("9");
		shop.setEndTime("18");
		shop.setBusinessDay(12345);
		shop.setShopName("라공방");
		shop.setShopMaxReserv(9);
		
		int startTime = Integer.parseInt(shop.getStartTime());
		int endTime = Integer.parseInt(shop.getEndTime());
		
		if(shop != null) {
			model.addAttribute("startTime",startTime);
			model.addAttribute("endTime",endTime);
			model.addAttribute("shop", shop);
			return "reservation/viewReservation";
		}else {
			model.addAttribute("msg","응 아니야 돌아가!!!");
			return "reservation/viewReservation";
		}
	}
		
	
	
	
	// 예약할때 받아와야할 
	// 날짜, 시간, 인원수, 가게고유번호, 회원고유번호, 회원타입번호
	@RequestMapping(value="reservationInsert.dz", method=RequestMethod.POST)
	public String reservationInsert(@ModelAttribute Reservation reservation,
									@RequestParam("paymentPoint") int paymentPoint,
									@RequestParam("userPoint") int userPoint,
									@RequestParam("userNo") int userNo,
									 Model model
									) {
		int rResult = service.insertReservation(reservation);
		int pResult = 0;
		System.out.println("여기까지는 왔니");
		if(paymentPoint > 0) {
			System.out.println("if문");
			int sequenceNo = service.getReservNo(userNo);
			System.out.println(reservation.toString());
			System.out.println(sequenceNo);
		
			pResult = service.updateUserPoint(sequenceNo);
			System.out.println(pResult);
		}
		
		if( rResult > 0) {
			return "redirect:reservationView.dz";
		}else {
			model.addAttribute("msg","어림도 없지!!!!!!");
			return "redirect:reservationView.dz";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="rCountCheck.dz", method=RequestMethod.POST)
	public String rCountCheck(@ModelAttribute Reservation reservation) {
		System.out.println(reservation.toString());
		int result = service.confirmRCount(reservation);
		System.out.println(result);
		return ""+result;
	}
	
	// ============================여기까지 완성=============
	
	
	// 예약승인하기
	@RequestMapping(value="Reservationcomfirm.dz", method=RequestMethod.GET)
	public String comfirmReservation(@RequestParam("reservationNo") int reservationNo) {
		int result = service.comfirmReservation(reservationNo);
		return "";
	}
	
//	// 예약취소하기
//	@RequestMapping(value="ReservationCancle.dz", method = RequestMethod.GET)
//	public String cancleReservation(@RequestParam("reservationNo") int reservationNo) {
//		int result = service.cancleReservation(reservationNo);
//		return "";
//	}
	
	// 예약완료하기
	@RequestMapping(value="ReservationComplete.dz", method = RequestMethod.GET)
	public String completeReservation(@RequestParam("reservationNo") int reservationNo) {
		int result = service.comfirmReservation(reservationNo);
		return "";
	}
}
