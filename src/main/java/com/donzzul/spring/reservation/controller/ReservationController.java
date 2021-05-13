package com.donzzul.spring.reservation.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping(value="reservationView.kh")
	public String reservationView(Model model) {
		Shop shop = new Shop();
		shop.setShopNo(1);
		shop.setUserNo(2);
		shop.setStartTime("9");
		shop.setEndTime("18");
		shop.setBusinessDay(12345);
		
		User user = new User();
		user.setUserPoint(100);
		
		Reservation reservation = new Reservation();
		reservation.setReserveDate("2021-05-13");
		reservation.setReserveTime(17);
		reservation.setReserveCount(4);
		reservation.setPointYn("Y");
		
		if(shop != null && user != null && reservation != null) {
			model.addAttribute("shop", shop);
			model.addAttribute("user", user);
			model.addAttribute("reservation", reservation);
			return "reservation/viewReservation";
		}else {
			model.addAttribute("msg","응 아니야 돌아가!!!");
			return "reservation/viewReservation";
		}
	}
		
	
	
	
	// 예약할때 받아와야할 
	// 날짜, 시간, 인원수, 가게고유번호, 회원고유번호, 회원타입번호
	@RequestMapping(value="reservationInsert.kh", method=RequestMethod.POST)
	public String reservationInsert(@ModelAttribute Reservation reservation,
									@ModelAttribute Shop shop,
									@ModelAttribute User user
									) {
		
		int rResult = service.insertReservation(reservation);
		int uResult= service.updateUserPoint(user);
//		if(result > 0) {
//			
//		}else {
//			
//		}
		return "";
	}
	
	
	
	
	//꿈나무회원별 예약목록 불러오기
	// int reserveNo, int userNo로 예약목록 불러오기
	@RequestMapping(value="ListByDream.kh", method = RequestMethod.GET)
	public String reservationListByDream(@RequestParam("reservationNo") int reservationNo,
										@RequestParam("userNo") int userNo){
		HashMap<String, String> result = service.reservaionListByDream(reservationNo, userNo);
		return "";
	}
	
	// mz회원별 예약목록 불러오기
	@RequestMapping(value="ListByMZ.kh", method = RequestMethod.GET)
	public String reservationListByMZ(@RequestParam("reservationNo") int reservationNo,
									@RequestParam("userNo") int userNo) {
		HashMap<String, String> result = service.reservationListByMZ(reservationNo, userNo);
		return "";	
	}
	
	
	// 가게별 예약목록 불러오기
	// int reserveNo, int ShopNo
	@RequestMapping(value="ListByShop.kh", method = RequestMethod.GET)
	public String reservationListByShop(@RequestParam("reservationNo") int reservationNo,
										@RequestParam("shopNo") int shopNo) {
		HashMap<String, String>result = service.reservaionListByShop(reservationNo, shopNo);
		return "";
	}
	
	// 예약취소 int reserveNo
	@RequestMapping(value="deleteReservation.kh", method = RequestMethod.GET)
	public String deleteReservation(@RequestParam("reservationNo") int reservationNo) {
		int result = service.deleteReservation(reservationNo);
		if(result > 0) {
			
		}else {
			
		}
		return "";
	}
	
	
	// 예약승인하기
	@RequestMapping(value="Reservationcomfirm.do", method=RequestMethod.GET)
	public String comfirmReservation(@RequestParam("reservationNo") int reservationNo) {
		int result = service.comfirmReservation(reservationNo);
		return "";
	}
	
	// 예약취소하기
	@RequestMapping(value="ReservationCancle.do", method = RequestMethod.GET)
	public String cancleReservation(@RequestParam("reservationNo") int reservationNo) {
		int result = service.cancleReservation(reservationNo);
		return "";
	}
	
	// 예약완료하기
	@RequestMapping(value="ReservationComplete.do", method = RequestMethod.GET)
	public String completeReservation(@RequestParam("reservationNo") int reservationNo) {
		int result = service.comfirmReservation(reservationNo);
		return "";
	}
}
