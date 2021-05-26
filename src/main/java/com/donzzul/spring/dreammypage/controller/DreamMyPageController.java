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
import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.reservation.service.ReservationService;
import com.donzzul.spring.user.domain.User;

@Controller
public class DreamMyPageController {

	@Autowired
	private ReservationService rService;

	@Autowired
	private DreamReviewService drService;

	@RequestMapping(value = "dreamMyPage.dz")
	public String DreamMyPageView(HttpSession session, Model model) {
		User user = (User) session.getAttribute("loginUser");

		int userNo = user.getUserNo();
		ArrayList<Reservation> rList = rService.rListByDreamUpToThree(userNo);

		ArrayList<DreamReview> drList = drService.drmRwUptoThree(userNo);

		if (!rList.isEmpty() && !drList.isEmpty()) {
			model.addAttribute("rList", rList);
			model.addAttribute("drList", drList);
			return "dreamMyPage/DreamMyPage";
		} else if (rList.isEmpty()) {
			model.addAttribute("msg", "불러올 데이터가 없습니다.");
			return "dreamMyPage/DreamMyPage";
		} else if (drList.isEmpty()) {
			model.addAttribute("msg", "불러올 데이터가 없습니다.");
			return "dreamMyPage/DreamMyPage";
		} else {
			model.addAttribute("msg", "내역을 출력하는데 실패했습니다.");
			return "common/errorPage";
		}
	}

	// 예약 취소
	@RequestMapping(value = "cancelReservation.dz")
	public String cancleReservation(@RequestParam("reservationNo") int reservationNo, Model model,
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
				return "redirect:dreamMyPage.dz";
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
			mv.setViewName("dreamMyPage/DreamReservationDetail");
		} else {
			mv.addObject("msg", "전체예약목록을 불러오는데 실패했습니다.");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	// 꿈나무 회원 리뷰 페이지 들어가기
	@RequestMapping(value = "dreamReviewDetail.dz", method = RequestMethod.GET)
	public String dreamReviewDetail() {
		return "dreamMyPage/DreamReviewDetail";
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
			mv.setViewName("dreamMyPage/DreamReviewDetail");
		} else if (drReviewList.isEmpty()) {
			mv.addObject("msg", "불러올 데이터가 없습니다.");
			mv.setViewName("dreamMyPage/DreamReviewDetail");
		} else {
			mv.addObject("msg", "전체리뷰를 불러오는데 실패했습니다.");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

}
