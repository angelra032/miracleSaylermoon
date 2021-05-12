package com.donzzul.spring.payment.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.donzzul.spring.payment.service.PaymentService;
import com.donzzul.spring.user.domain.User;

@Controller
public class PaymentController {

	@Autowired
	private PaymentService service;
	
	
	// 돈쭐 결제 폼
	@RequestMapping(value="paymentFormView.dz", method=RequestMethod.POST)
	public String paymentFormView() {
		return "payment/paymentForm";
	}
	
	// 포인트 사용(ajax)
	@ResponseBody
	@RequestMapping(value="usePoint.dz", method=RequestMethod.POST)
	public String usePoint(HttpServletRequest request, @ModelAttribute User user, Model model) {
							// 세션의 user 포인트 가져오기
		
		return ""; // 사용하고 결제폼으로 다시
	}
	
	// 결제하기(API로 넘어감)
	@RequestMapping(value="kakaoPay.dz", method=RequestMethod.POST)
	public String kakaoPay() {
		return "";
	}
	
	// 돈쭐 내역 저장
	@RequestMapping(value="insertDonList.dz", method=RequestMethod.GET)
	public String insertDonList(HttpServletRequest request, @ModelAttribute User user, Model model) {
									// 결제 후 반환정보 파라미터로
		return "";
	}
	
	// 돈쭐 내역 출력
	@RequestMapping(value="printDonList.dz", method=RequestMethod.GET)
	public String printDonList(HttpServletRequest request, @ModelAttribute User user, Model model) {
									// 결제 후 반환정보 파라미터로
		return "";
	}
	
	
	// 룰렛 페이지
	@RequestMapping(value="rouletteView.dz", method=RequestMethod.GET)
	public String rouletteView() {
		return "";
	}
	
	// 룰렛 포인트 정립
	@RequestMapping(value="saveRoulettePoint.dz", method=RequestMethod.POST)
	public String saveRoulettePoint(HttpServletRequest request, @ModelAttribute User user, Model model) {
		String winningPoint = request.getParameter("winningPoint"); // 앞단에서 당첨된 포인트
		
		return "";
	}
	
	
	// 인증샷 페이지
	@RequestMapping(value="snsPhotoView.dz", method=RequestMethod.GET)
	public String snsPhotoView() {
		return "";
	}
	
	// 리뷰 포인트 정립(MZ 마이페이지에서)
	@RequestMapping(value="saveReviewPoint.dz", method=RequestMethod.POST)
	public String saveReviewPoint(HttpServletRequest request, @ModelAttribute User user, Model model) {
		String winningPoint = request.getParameter("winningPoint"); // 리뷰작성 포인트
		
		return "";
	}
	
	// 포인트 조회(MZ 마이페이지에서)
	@RequestMapping(value="printMyPoint.dz", method=RequestMethod.POST)
	public String printMyPoint(HttpServletRequest request, @ModelAttribute User user, Model model) {
		return "";
	}
	
	// 환급 신청 /////////어디서? 사업자
//	@RequestMapping(value="refundsPoint.dz", method=RequestMethod.GET)
//	public String refundsPoint(HttpServletRequest request, @ModelAttribute User user, Model model) {
//		return "";
//	}
//	public int updateRefundsPoint(User user);
	
	
}
