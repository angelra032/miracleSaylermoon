package com.donzzul.spring.partnermypage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.common.Pagination;
import com.donzzul.spring.notiqna.domain.Qna;
import com.donzzul.spring.notiqna.service.QnaService;
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
	
	@Autowired
	private QnaService qService;
	
	// 사업자 마이페이지 출력
	@RequestMapping(value="partnerMyPage.dz", method=RequestMethod.GET)
	public String partnerMyPageView(HttpSession session, Model model) { //@RequestParam("userNo") int userNo, Model model, requestParam("userNo") int userNo
		
		User loginUser = (User)session.getAttribute("loginUser"); // 로그인세션(사업자)
		int userNo = loginUser.getUserNo();
	
		Shop myShop = pService.selectMyShop(userNo);
		model.addAttribute("shop", myShop);
			
			// 예약 목록 3개
			ArrayList<Reservation> rList = rService.listByShopToThree(myShop.getShopNo());
			// 문의글 3개
			ArrayList<Qna> qList = qService.shopQnaUpToThree(myShop.getShopNo());	
			
				model.addAttribute("rList", rList);
				model.addAttribute("qList", qList);
				model.addAttribute("Qmsg","문의 데이터가 없습니다.");
				return "partnerMyPage/partnerMyPage";

	}
	
	// 예약 상태(업데이트 - 대기, 승인, 거부)
	@RequestMapping(value="updateReservation.dz", method=RequestMethod.POST)
	public String updateReservationState(@RequestParam("reservationNo") int reservationNo,
										@RequestParam("rState") String rState,
										@RequestParam("shopNo") int shopNo,
										Model model) {
		Reservation reservation = rService.selectOne(reservationNo);
		String rStateResulut = reservation.getrState();
//		예약기본상태 O(default)
//		예약승인 Y(comfirm)
//		예약취소 X(cancle)
//		예약완료 C(complete)
		if(rStateResulut != null) {
			reservation.setrState(rState);
			int result = rService.updateRstate(reservation);
			if(result > 0 && rState.equals("C")) {
				//rService.updateShopPoint(reservation);
				//방문 완료했을때 포인트가 업데이트 되게 바꾸기
				}
			}
		model.addAttribute("msg", "예약 상태 변경에 실패했습니다.");
		return "redirect:partnerMyPage.dz";
		}
	
	// 방문완료
	@RequestMapping(value="completeReservation.dz", method=RequestMethod.GET)
	public String completeReservation(@RequestParam("reservationNo") int reservationNo,
									@RequestParam("rState") String rState,
									Model model) {
		if(rState.equals("Y")) {
			Reservation reservation = new Reservation();
			reservation.setrState("C");
			reservation.setReservationNo(reservationNo);
			int result = rService.updateRstate(reservation); // rState 변경
		}else {
			model.addAttribute("msg", "예약 상태 변경에 실패했습니다.");
			return "redirect:partnerMyPage.dz";
		}
		model.addAttribute("msg", "예약 상태 변경에 실패했습니다.");
		return "redirect:partnerMyPage.dz";
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
		int listCount = rService.selectShopListCount(myShop.getShopNo());
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		
		// 예약 전체 리스트 가져오기
		ArrayList<Reservation> rList = rService.reservaionListByShop(myShop.getShopNo(), pi);
		if(!rList.isEmpty()) {
			mv.addObject("rList", rList);
			mv.addObject("pi", pi);
			mv.setViewName("partnerMyPage/partnerReservationDetail"); // 전체 예약페이지로 가기!!!(바꾸기)
		}else {
			mv.addObject("msg", "사업자 예약 더보기 조회 실패");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 사업자 qna 전체 불러오기
	@RequestMapping(value="allQnaListByPartner.dz", method = RequestMethod.GET)
	public ModelAndView allQnaListByPartner(HttpSession session,
											ModelAndView mv,
											@RequestParam(value="page", required= false) Integer page) {
		User loginUser = (User)session.getAttribute("loginUser"); // 로그인세션(사업자)
		int userNo = loginUser.getUserNo();
	
		Shop myShop = pService.selectMyShop(userNo);
		mv.addObject("shop",myShop);	
		
		int currentPage = (page != null) ? page : 1;
		int listCount = qService.dreamListCount(myShop.getShopNo());
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		
		ArrayList<Qna> qList = qService.qnaListByPartner(myShop.getShopNo(), pi);
		
		if(!qList.isEmpty()) {
			mv.addObject("qList",qList);
			mv.addObject("pi",pi);
			mv.setViewName("partnerMyPage/partnerQnaDetail");
		}else {
			mv.addObject("msg","불로올 문의 데이터가 없습니다.");
			mv.setViewName("partnerMyPage/partnerQnaDetail");
		}
		return mv;
	}

	
	// 사업자 포인트 환급신청
	@RequestMapping(value="refundsPartnerPoint.dz", method=RequestMethod.GET)
	public void refundsPoint(HttpServletResponse response, HttpSession session, @ModelAttribute User user, Model model) throws Exception {
		// 환급신청(전체)
		// userNo랑 shopNo 통해서 shop의 shopPoint랑 shopPointYN 변경(update)
		
		// 내 가게 조회
		User loginUser = (User)session.getAttribute("loginUser");
		Shop myShop = pService.selectMyShop(loginUser.getUserNo());
		
		// 포인트 환급신청
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		if(myShop != null) {
			// 환급할 포인트가 0보다 크면
			if(myShop.getShopPoint() > 0) {
				System.out.println("가게 포인트:"+myShop.toString());
				int shopPointandYN = pService.applyRefundsShopPoint(myShop.getShopNo());
				if(shopPointandYN > 0) {
					System.out.println("환급신청 YN 업데이트");
					// alert창으로 2-3일 내에 포인트가 환급됩니다 띄우기 - model
					out.println("<script>alert('환급신청이 완료되었습니다. \\n2-3일 내에 포인트가 환급됩니다.');location.href='partnerMyPage.dz';</script>");
					out.flush();
				}else {
					out.println("<script>alert('포인트 환급신청에 실패하였습니다.');location.href='/index.jsp';</script>");
					out.flush();
				}
			}else {
				// 환급할 포인트가 0보다 작거나 같으면
				System.out.println("가게 포인트:"+myShop.toString());
				out.println("<script>alert('신청할 포인트가 존재하지 않습니다.'); location.href='partnerMyPage.dz';</script>");
				out.flush();
			}
		}else {
			out.println("<script>alert('내 가게 조회에 실패하였습니다.');location.href='/index.jsp';</script>");
			out.flush();
		}
	}

	
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
	
	
	// 버튼별 예약 현황 다르게 보여주기
	@ResponseBody
	@RequestMapping(value="reservationStatue.dz", method=RequestMethod.POST)
	public ArrayList<HashMap<String, String>> reservationStatue(@RequestParam("shopNo") int shopNo,
																@RequestParam("rState") String rState){
		System.out.println(shopNo);
		System.out.println(rState);
		Reservation reservation = new Reservation();
		reservation.setrState(rState);
		reservation.setShopNo(shopNo);
		ArrayList<Reservation> rList = rService.reservationState(reservation);
		
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		
		for(int i = 0; i< rList.size(); i++) {
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("title", rList.get(i).getUserNick());
			map.put("start", rList.get(i).getReserveDate());
			map.put("end", rList.get(i).getReserveDate());
			list.add(map);
		}
		return list;
	}

}

