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
		
		if(shop != null) {
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
		
		System.out.println(userPoint);
		System.out.println(rResult);
		
		if(paymentPoint > 0) {
			int point = userPoint - paymentPoint;
			User user = new User();
			user.setUserNo(userNo);
			user.setUserPoint(point);
			pResult = service.updateUserPoint(user);
		}
		
		if( rResult > 0 && rResult > 0) {
			return "redirect:reservationView.kh";
		}else {
			model.addAttribute("msg","어림도 없지!!!!!!");
			return "redirect:reservationView.dz";
		}
	}
	
	// ============================여기까지 완성=============
	

	
	
	
	
	// mz회원별 예약목록 불러오기
	@RequestMapping(value="ListByMZ.dz", method = RequestMethod.GET)
	public String reservationListByMZ(@RequestParam("reservationNo") int reservationNo,
									@RequestParam("userNo") int userNo) {
		ArrayList<Reservation> result = service.reservationListByMZ(userNo);
		return "";	
	}
	
	// 가게별 예약목록 불러오기
	// int reserveNo, int ShopNo
	@RequestMapping(value="ListByShop.dz", method = RequestMethod.GET)
	public String reservationListByShop(@RequestParam("reservationNo") int reservationNo,
										@RequestParam("shopNo") int shopNo) {
		String result = service.reservaionListByShop(reservationNo, shopNo);
		return "";
	}
	
	// 예약취소 int reserveNo
	@RequestMapping(value="deleteReservation.dz", method = RequestMethod.GET)
	public String deleteReservation(@RequestParam("reservationNo") int reservationNo) {
		int result = service.deleteReservation(reservationNo);
		if(result > 0) {
			
		}else {
			
		}
		return "";
	}
	
	
	// 예약승인하기
	@RequestMapping(value="Reservationcomfirm.dz", method=RequestMethod.GET)
	public String comfirmReservation(@RequestParam("reservationNo") int reservationNo) {
		int result = service.comfirmReservation(reservationNo);
		return "";
	}
	
	// 예약취소하기
	@RequestMapping(value="ReservationCancle.dz", method = RequestMethod.GET)
	public String cancleReservation(@RequestParam("reservationNo") int reservationNo) {
		int result = service.cancleReservation(reservationNo);
		return "";
	}
	
	// 예약완료하기
	@RequestMapping(value="ReservationComplete.dz", method = RequestMethod.GET)
	public String completeReservation(@RequestParam("reservationNo") int reservationNo) {
		int result = service.comfirmReservation(reservationNo);
		return "";
	}
}
