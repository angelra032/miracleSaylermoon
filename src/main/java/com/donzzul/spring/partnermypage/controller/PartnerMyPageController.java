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
import org.springframework.web.servlet.ModelAndView;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.common.Pagination;
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
	public String partnerMyPageView(HttpSession session, Model model) { //@RequestParam("userNo") int userNo, Model model, requestParam("userNo") int userNo
		
		User loginUser = (User)session.getAttribute("loginUser"); // 로그인세션(사업자)
		int userNo = loginUser.getUserNo();
	
		Shop myShop = pService.selectMyShop(userNo); // 사업자 가게 불러오기
		int shopNo = myShop.getShopNo();
		
		ArrayList<Reservation> rList = rService.rListByShopUpToThree(shopNo);
		if(!rList.isEmpty()) {
			model.addAttribute("rList", rList);
			return "partnerMyPage/partnerMyPage";
		}else {
			return "common/errorPage";
		}
	}
	
	// 예약 상태(업데이트 - 대기, 승인, 거부)
	@RequestMapping(value="updateReservation.dz", method=RequestMethod.POST)
	public String updateReservationState(@RequestParam("reservationNo") int reservationNo,
										@RequestParam("rState") String rState,
										@RequestParam("shopNo") int shopNo,
										Model model) {
		System.out.println("예약번호얌"+reservationNo);
		
		Reservation resultReservation = rService.selectOne(reservationNo);
		String rStateResulut = resultReservation.getrState();

//		예약기본상태 O(default)
//		예약승인 Y(comfirm)
//		예약취소 X(cancle)
//		예약완료 C(complete)
		if(rStateResulut.equals("O")) {
			switch(rState) {
			case "Y" : 
				int cResult = rService.comfirmReservation(reservationNo);
				int pResult = rService.updateShopPoint(resultReservation);
				
				 	if(pResult > 0 && cResult > 0) { 
				 		model.addAttribute("pResult", pResult); 
				 		return "";
				 }
				
				break;
			case "X" :
				break;
			case "C" :
				break;
			}
		}

		return "partnerMyPage/partnerMyPage";
	}
	
	
	// 예약 더보기(풀 리스트)
	@RequestMapping(value="partnerReserveList.dz", method=RequestMethod.GET)
	public ModelAndView partnerReserveAllList(ModelAndView mv, 
												HttpSession session,
												@RequestParam(value="page", required = false) Integer page ) {
		// loginUser, shop 가져오기
		User loginUser = (User)session.getAttribute("loginUser");
		Shop myShop = pService.selectMyShop(loginUser.getUserNo());
		System.out.println("getShopNo" + myShop.getShopNo());

		// 페이징 pi
		int currentPage = (page != null) ? page : 1;
		int listCount = rService.getListCount(loginUser.getUserNo());
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		
		// 예약 전체 리스트 가져오기
		ArrayList<Reservation> rList = rService.reservaionListByShop(myShop.getShopNo(), pi);
		if(!rList.isEmpty()) {
			mv.addObject("rList", rList);
			mv.addObject("pi", pi);
			mv.setViewName("partnerMyPage/partnerMyPage"); // 전체 예약페이지로 가기!!!(바꾸기)
		}else {
			mv.addObject("msg", "사업자 예약 더보기 조회 실패");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	
	
	// 내가 쓴 글 목록(띄우기, 수정, 삭제)
	
	// 내가 쓴 글 목록 더보기
	
	
	
	// 사업자 포인트 환급신청
	
	
	// 가게정보 등록 화면(view)
	@RequestMapping(value="shopRegisterView.dz", method=RequestMethod.GET)
	public String shopRegisterView() {
		return "";
	}
	
	// 가게정보 등록
	@RequestMapping(value="shopRegister.dz", method=RequestMethod.POST )
	public String shopRegister() {
		
		// 가게 파일 저장(서버, 디비)
		
		
		return "";
	}
	
	// 가게정보 수정 ?
	
	
	// 회원정보 수정
	
	// 회원탈퇴 요청
	@RequestMapping(value="", method=RequestMethod.GET)
	public String memberWithdraw(HttpSession session
								) {
		User loginUser = (User)session.getAttribute("loginUser");
//		loginUser.getUserNo()
		
		return "";
	}
	

}

