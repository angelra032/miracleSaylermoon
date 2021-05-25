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
import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.reservation.service.ReservationService;
import com.donzzul.spring.user.domain.User;

@Controller
public class MZMyPageController {

	@Autowired
	private ReservationService rService;
	
	// 마이페이지 메인
	@RequestMapping(value = "mzMyPage.dz")
	public String MZMyPageView(HttpSession session,
									Model model) {
		User user = (User) session.getAttribute("loginUser");
		
		int userNo = user.getUserNo();
		ArrayList<Reservation> rList = rService.rListByDreamUpToThree(userNo);
		if(!rList.isEmpty()) {
			model.addAttribute("rList",rList);
			return "dreamMyPage/DreamMyPage";
		}else {
			model.addAttribute("msg","예약목록 불러오는데 실패했지...!!");
			return "common/errorPage";
		}
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
