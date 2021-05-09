package com.donzzul.spring.reservation.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.reservation.service.ReservationService;

@Controller
public class ReservationController {

	@Autowired
	private ReservationService service;
	//@RequestParam -> 화면에서 가져오는 하나의 값
	//@ModelAttribute
	
	// 예약할때 받아와야할 값
	// 날짜, 시간, 인원수, 가게고유번호, 회원고유번호, 회원타입번호
	@RequestMapping(value="reservationInsert.do", method=RequestMethod.POST)
	public String reservationInsert(@ModelAttribute Reservation reservation) {
		int result = service.insertReservation(reservation);
		if(result > 0) {
			
		}else {
			
		}
		return "";
	}
	
	//회원별 예약목록 불러오기
	// int reserveNo, int userNo로 예약목록 불러오기
	@RequestMapping(value="ListByUser.do", method = RequestMethod.GET)
	public String reservationListByUser(@RequestParam("reservationNo") int reservationNo,
										@RequestParam("userNo") int userNo){
		HashMap<String, String> result = service.reservaionListByUser(reservationNo, userNo);
		return "";
	}
	
	// 가게별 예약목록 불러오기
	// int reserveNo, int ShopNo
	@RequestMapping(value="ListByShop.do", method = RequestMethod.GET)
	public String reservationListByShop(@RequestParam("reservationNo") int reservationNo,
										@RequestParam("shopNo") int shopNo) {
		HashMap<String, String>result = service.reservaionListByShop(reservationNo, shopNo);
		return "";
	}
	
	// 예약취소 int reserveNo
	@RequestMapping(value="deleteReservation.do", method = RequestMethod.GET)
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
