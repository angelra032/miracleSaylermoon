package com.donzzul.spring.dreammypage.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.common.Pagination;
import com.donzzul.spring.dreamreview.domain.DreamReview;
import com.donzzul.spring.dreamreview.service.DreamReviewService;
import com.donzzul.spring.notiqna.domain.Qna;
import com.donzzul.spring.notiqna.service.QnaService;
import com.donzzul.spring.pick.domain.Pick;
import com.donzzul.spring.pick.service.PickService;
import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.reservation.service.ReservationService;
import com.donzzul.spring.user.domain.User;

@Controller
public class DreamMyPageController {

	@Autowired
	private ReservationService rService;

	@Autowired
	private DreamReviewService drService;
	
	@Autowired
	private QnaService qService;
	
	@Autowired
	private PickService pService;

	@RequestMapping(value = "dreamMyPage.dz")
	public String DreamMyPageView(HttpSession session, Model model) {
		User user = (User) session.getAttribute("loginUser");
		int userNo = user.getUserNo();
		try {
			ArrayList<Reservation> rList = rService.listByDreamUpToThree(userNo);
			ArrayList<DreamReview> drList = drService.drmRwUptoThree(userNo);
			ArrayList<Qna> qList = qService.dreamQnaUpToThree(userNo);
			ArrayList<Pick> pList = pService.dreamPickUpToThree(userNo);
			model.addAttribute("rList", rList);
			model.addAttribute("drList", drList);
			model.addAttribute("qList",qList);
			model.addAttribute("pList",pList);
			model.addAttribute("Rmsg","예약 데이터가 없습니다.");
			model.addAttribute("DRmsg","후기 데이터가 없습니다.");
			model.addAttribute("Qmsg","문의 데이터가 없습니다.");
			model.addAttribute("Pmsg","찜 데이터가 없습니다.");
			return "dreamMyPage/dreamMyPage";
		}catch(Exception e) {
			model.addAttribute("msg", "내역을 출력하는데 실패했습니다.");
			return "common/errorPage";
		}
	}

	// 예약 취소
	@RequestMapping(value = "cancelReservation.dz")
	public String cancleReservation(@RequestParam("reservationNo") int reservationNo, 
									@RequestParam("mainPage") String mainPage,
									Model model,
									HttpSession session) {
		User user = (User) session.getAttribute("loginUser");
		int userNo = user.getUserNo();
		Reservation reservation = rService.selectOne(reservationNo);
		String rStateResult = reservation.getrState();
		if (rStateResult.equals("O")) {
			reservation.setrState("X");
			int result = rService.updateRstate(reservation);

			reservation.setUserNo(userNo);
			reservation.setReservationNo(reservationNo);
			int cancleResult = rService.cancleReservation(reservation);
			
			if (result > 0 && cancleResult > 0) {
				if(mainPage.equals("N")) {
					return "redirect:dreamMyPage.dz";
				}else {
					return "redirect:allRListDetailByDream.dz";
				}
			} else {
				model.addAttribute("msg", "예약취소에 실패했습니다.");
				return "redirect:dreamMyPage.dz";
			}
		} else {
			model.addAttribute("msg", "예약취소에 실패했습니다.");
			return "common/errorPage";
		}
	}

	// 꿈나무회원 마이페이지 예약 전체 불러오기
	@RequestMapping(value = "allRListDetailByDream.dz", method = RequestMethod.GET)
	public ModelAndView allReservaionListByDream(HttpSession session, Model model, ModelAndView mv,
			@RequestParam(value = "page", required = false) Integer page) {

		User user = (User) session.getAttribute("loginUser");
		int userNo = user.getUserNo();

		int currentPage = (page != null) ? page : 1;
		int listCount = rService.getListCount(userNo);
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);

		ArrayList<Reservation> rList = rService.reservationListByDream(userNo, pi);

		if (!rList.isEmpty()) {
			mv.addObject("rList", rList);
			mv.addObject("pi", pi);
			mv.setViewName("dreamMyPage/dreamReservationDetail");
		} else {
			mv.addObject("msg", "불러올 예약 데이터가 없습니다.");
			mv.setViewName("dreamMyPage/dreamReservationDetail");
		}
		return mv;
	}

	// 꿈나무 회원 리뷰 페이지 들어가기
	@RequestMapping(value = "dreamReviewDetail.dz", method = RequestMethod.GET)
	public String dreamReviewDetail() {
		return "dreamMyPage/dreamReviewDetail";
	}

	
	// 꿈나무 회원 리뷰 전체 불러오기
	@RequestMapping(value = "allReviewListByDream.dz", method = RequestMethod.GET)
	public ModelAndView allReviewListByDream(HttpSession session, Model model, ModelAndView mv,
			@RequestParam(value = "page", required = false) Integer page) {
		User user = (User) session.getAttribute("loginUser");
		int userNo = user.getUserNo();

		int currentPage = (page != null) ? page : 1;
		int listCount = drService.dreamGetListCount(userNo);
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<DreamReview> drReviewList = drService.reviewListByDream(userNo, pi);

		if (!drReviewList.isEmpty()) {
			mv.addObject("drReviewList", drReviewList);
			mv.addObject("pi", pi);
			mv.setViewName("dreamMyPage/dreamReviewDetail");
		} else {
			mv.addObject("msg", "불러올 후기 데이터가 없습니다.");
			mv.setViewName("dreamMyPage/dreamReviewDetail");
		} 
		return mv;
	}
	
	// 꿈나무회원 qna 전체 불러오기
	@RequestMapping(value="allQnaListByDream.dz", method = RequestMethod.GET)
	public ModelAndView allQnaListByDream(HttpSession session, 
									ModelAndView mv,
									@RequestParam(value = "page", required = false) Integer page) {
		User user = (User) session.getAttribute("loginUser");
		int userNo = user.getUserNo();
		
		int currentPage = (page != null) ? page : 1;
		int listCount = qService.dreamListCount(userNo);
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		
		ArrayList<Qna> qList = qService.qnaListBydream(userNo, pi);
		
		if(!qList.isEmpty()) {
			mv.addObject("qList",qList);
			mv.addObject("pi",pi);
			mv.setViewName("dreamMyPage/dreamQnaDetail");
		}else {
			mv.addObject("msg","불러올 문의 데이터가 없습니다.");
			mv.setViewName("dreamMyPage/dreamQnaDetail");
		}
		return mv;
	}
	
	// 꿈나무 회원 찜목록 전체 불러오기
	@RequestMapping(value="pickListByDream.dz", method = RequestMethod.GET)
	public ModelAndView pickListByDream(HttpSession session,
									ModelAndView mv,
									@RequestParam(value= "page", required = false) Integer page) {
		User user = (User) session.getAttribute("loginUser");
		int userNo = user.getUserNo();
		
		int currentPage = (page != null ) ? page : 1;
		int listCount = pService.pickListCount(userNo);
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		
		ArrayList<Pick> pList = pService.pickListByDream(userNo, pi);
		
		if(!pList.isEmpty()) {
			mv.addObject("pList",pList);
			mv.addObject("pi",pi);
			mv.setViewName("dreamMyPage/dreamPickDetail");
		}else {
			mv.addObject("msg","불러올 찜 데이터가 없습니다.");
			mv.setViewName("dreamMyPage/dreamPickDetail");
		}
		return mv;
	}

}
