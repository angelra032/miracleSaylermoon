package com.donzzul.spring.mzmypage.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.common.Pagination;

import com.donzzul.spring.payment.domain.Don;
import com.donzzul.spring.payment.service.PaymentService;

import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.reservation.service.ReservationService;
import com.donzzul.spring.user.domain.User;

@Controller
public class MZMyPageController {

	@Autowired
	private PaymentService pService;
	
  @Autowired
	private ReservationService rService;
	
	// (민애) mz마이페이지 메인뷰
  	@RequestMapping(value = "mzMyPage.dz")
	public String MZMyPageView(HttpSession session, Model model) {
		
		User loginUser = (User)session.getAttribute("loginUser");
		int userNo = loginUser.getUserNo();
		
		// 예약 목록
		ArrayList<Reservation> rList = rService.listByMZToThree(userNo);
		// 돈쭐 목록
		ArrayList<Don> dList = pService.selectDonListThree(userNo);
		
		if(!rList.isEmpty() && !dList.isEmpty()) {
			model.addAttribute("rList", rList);
			model.addAttribute("dList", dList);
			return "mzMyPage/MZMyPage";
		}else if(rList.isEmpty()){
			model.addAttribute("msg", "데이터가 없습니다.");
			return "mzMyPage/MZMyPage";
		}else if(dList.isEmpty()) {
			model.addAttribute("msg", "데이터가 없습니다.");
			return "mzMyPage/MZMyPage";
		}else {
			model.addAttribute("msg", "내역을 출력하는데 실패했습니다");
			return "common/errorPage";
		}
  	}
  	
 // (민애) 구글구글 mz마이페이지 메인뷰
   	@RequestMapping(value = "GoogleMyPage.dz")
 	public String GoogleMZMyPageView(HttpSession session, Model model) {
 		
 		User loginUser = (User)session.getAttribute("loginUser");
 		
 		
 		// 예약 목록
 		//ArrayList<Reservation> rList = rService.rListByMZUpToThree(userNo);
 		// 돈쭐 목록
 		//ArrayList<Don> dList = pService.selectDonListThree(userNo);
 		
 		//if(!rList.isEmpty() && !dList.isEmpty()) {
 			//model.addAttribute("rList", rList);
 			//model.addAttribute("dList", dList);
 			return "mzMyPage/MZMyPage";
 		//}else if(rList.isEmpty()){
 			//model.addAttribute("msg", "데이터가 없습니다.");
 			//return "mzMyPage/MZMyPage";
 		//}else if(dList.isEmpty()) {
 			//model.addAttribute("msg", "데이터가 없습니다.");
 			//return "mzMyPage/MZMyPage";
 		//}else {
 			//model.addAttribute("msg", "내역을 출력하는데 실패했습니다");
 			//return "common/errorPage";
 		//}
   	}
   	
 // (민애) 카카오 mz마이페이지 메인뷰
   	@RequestMapping(value = "KakaoMyPage.dz")
 	public String KakaoMZMyPageView(HttpSession session, Model model) {
 		
 		User loginUser = (User)session.getAttribute("loginUser");
 		
 		
 		// 예약 목록
 		//ArrayList<Reservation> rList = rService.rListByMZUpToThree(userNo);
 		// 돈쭐 목록
 		//ArrayList<Don> dList = pService.selectDonListThree(userNo);
 		
 		//if(!rList.isEmpty() && !dList.isEmpty()) {
 			//model.addAttribute("rList", rList);
 			//model.addAttribute("dList", dList);
 			return "mzMyPage/MZMyPage";
 		//}else if(rList.isEmpty()){
 			//model.addAttribute("msg", "데이터가 없습니다.");
 			//return "mzMyPage/MZMyPage";
 		//}else if(dList.isEmpty()) {
 			//model.addAttribute("msg", "데이터가 없습니다.");
 			//return "mzMyPage/MZMyPage";
 		//}else {
 			//model.addAttribute("msg", "내역을 출력하는데 실패했습니다");
 			//return "common/errorPage";
 		//}
   	}
	
	// 돈쭐 내역 출력
	@RequestMapping(value ="printDonAllList.dz", method = RequestMethod.GET)
	public ModelAndView printDonAllList(HttpSession session, ModelAndView mv, Model model, @RequestParam(value="page", required = false) Integer page) {
		// 결제 후 반환정보 파라미터로
		
		// 돈쭐 (3개 / 페이징) 클릭하면 로그인세션(userNo)로 검색
		User loginUser = (User)session.getAttribute("loginUser");
		int userNo = loginUser.getUserNo();
		
		// 페이징
		int currentPage = (page != null) ? page : 1;
		int listCount = rService.getListCount(userNo);
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		
		ArrayList<Don> dList = pService.selectDonList(userNo, pi);
		if(!dList.isEmpty()) {
			mv.addObject("dList", dList);
			mv.addObject("pi", pi);
			mv.setViewName("mzMyPage/mzDonAllListDetail"); // 마이페이지
		}else {
			mv.addObject("msg", "돈쭐 전체 내역을 출력하는데 실패하였습니다!");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	
	
	
	// 예약 전체 페이지보기
	@RequestMapping(value = "mzReservationList.dz", method = RequestMethod.GET)
	public ModelAndView MZReservationListView(HttpSession session,
										Model model,
										ModelAndView mv,
										@RequestParam(value="page", required=false) Integer page) {
		User user = (User)session.getAttribute("loginUser");
		int userNo = user.getUserNo();
		
		int currentPage = (page != null) ? page : 1;
		int listCount = rService.getListCount(userNo);
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		
		ArrayList<Reservation> rList = rService.reservationListByDream(userNo, pi);
		
		if(!rList.isEmpty()) {
			mv.addObject("rList",rList);
			mv.addObject("pi",pi);
			mv.setViewName("dreamMyPage/DreamRListDetail");
		}else {
			mv.addObject("msg","예약목록 전체 불러오는데 실패하였습니다.");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	


	
}
