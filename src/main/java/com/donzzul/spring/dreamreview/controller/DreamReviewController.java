package com.donzzul.spring.dreamreview.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.common.Pagination;
import com.donzzul.spring.dreamreview.domain.DreamReview;
import com.donzzul.spring.dreamreview.service.DreamReviewService;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.reservation.service.ReservationService;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.shop.service.ShopService;
import com.donzzul.spring.shop.service.logic.ShopServiceImpl;
import com.donzzul.spring.user.domain.User;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

@Controller
public class DreamReviewController {
	
	@Autowired
	private DreamReviewService drService;
	
	@Autowired
	private ReservationService rService;
	
	@Autowired
	private ShopService sService;
	
	
	// 감사후기 주소로 들어옴 (리스트출력할곳) selectAll
	@RequestMapping(value="dReviewMain.dz", method=RequestMethod.GET)
	public ModelAndView dReviewMainView(ModelAndView mv, @RequestParam(value="page", required=false) Integer page) {
		int currentPage = (page != null) ? page : 1;
		int listCount = drService.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage,listCount);
		ArrayList<DreamReview> drList = drService.selectAllDreamReview(pi);
		System.out.println(drList.toString());
		if(!drList.isEmpty()) {
			mv.addObject("drList", drList);
			mv.addObject("pi", pi);
		} else {
			mv.addObject("msg", "게시글이 없습니다");
		}
		mv.setViewName("board/drmReview/dReviewListView");
		return mv;
		
	}
	
	
	/// 리스트 끝
	
	// 디테일 selectOne
	@RequestMapping(value="dReviewDetail.dz", method=RequestMethod.GET)
	public ModelAndView dReviewDetailView(ModelAndView mv,@RequestParam("drmReviewNo") int drmReviewNo, HttpServletRequest request, HttpServletResponse response) {
		
		DreamReview drmReview = drService.selectOneDreamReview(drmReviewNo);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		int shopNo = sService.selectShopOne(drmReview.getShopNo()).getShopNo();
		if(drmReview != null) {
			if(drmReview.getDrmReviewPublicYN().equals("Y") || drmReview.getDrmReviewPublicYN().equals("y")) { // 게시글이 있고 공개상태
				updateDrmHit(response, request, drmReviewNo);
				mv.addObject("drmReview", drmReview).setViewName("board/drmReview/dReviewDetailView");
			} else {
				if(user == null) { // 게시글 비공개 - 로그인 안함
					mv.setViewName("redirect:/loginView.dz");
				} else if(drmReview.getUserNo() == user.getUserNo() || user.getUserType().equals("4") || drmReview.getShopName() == user.getPartnerName()) { // 게시글 비공개 - 세션결과가 글쓴이와 같은사람
					updateDrmHit(response, request, drmReviewNo);
					mv.addObject("drmReview", drmReview).setViewName("board/drmReview/dReviewDetailView");
				} else {
					mv.addObject("msg", "비공개된 남의 글 확인 불가").setViewName("common/errorPage");
				}
			}
		} else {
			mv.addObject("msg", "게시글 상세 조회 실패").setViewName("common/errorPage");
		}
		return mv;
	}
	
	public void updateDrmHit(HttpServletResponse response, HttpServletRequest request, int drmReviewNo) {
		User user = (User)request.getAttribute("loginUser");
		Cookie[] reqCookie = request.getCookies(); // 기존존재 쿠키가져옴
		Cookie nullCookie = null; // null 비교쿠키
		
		if(reqCookie != null && reqCookie.length > 0  && user != null) { // 로그인 되어있는 경우
			for(int i = 0; i < reqCookie.length; i++) {
				if(reqCookie[i].getName().equals("dReview" + user.getUserNo() + drmReviewNo)) {
					nullCookie = reqCookie[i];
				}
			}
		}
		if(reqCookie != null && reqCookie.length > 0 && user == null) { // 비로그인
			for(int i = 0; i < reqCookie.length; i++) {
				if(reqCookie[i].getName().equals("dReview"+drmReviewNo)) {
					nullCookie = reqCookie[i];
				}
			}
		}
		if(user != null && nullCookie == null) { // 로그인되어있는데 쿠키가 비어있음
			Cookie cookie = new Cookie("dReview"+user.getUserNo() + drmReviewNo, "dReview"+user.getUserNo() + drmReviewNo);
			cookie.setMaxAge(60*60*24*365);
			response.addCookie(cookie);
			
			int result = drService.updateHit(drmReviewNo);
			
			if(result > 0 ) {
				System.out.println("조회수 증가 성공");
			} else if(result <= 0) {
				System.out.println("조회수 증가 실패");
			}
		}
		
		if(user == null && nullCookie == null) { // 로그인X
			Cookie cookie = new Cookie("dReview" + drmReviewNo, "dReview" + drmReviewNo);
			cookie.setMaxAge(60*60*24*365);
			response.addCookie(cookie);
			int result = drService.updateHit(drmReviewNo);
			
			if(result > 0 ) {
				System.out.println("조회수 증가 성공");
			} else if(result <= 0) {
				System.out.println("조회수 증가 실패");
			}
		}
	}
	
	
	// 감사후기 글쓰기버튼으로 들어옴 
	@RequestMapping(value="dReviewWriteView.dz", method=RequestMethod.GET)
	public String dReviewWriteView(@RequestParam("shopNo") int shopNo,
									@RequestParam("reservationNo") int reservationNo,
									Model model) {
		// rState update에 필요한 reservationNo 가져오기
		model.addAttribute("shopNo", shopNo);
		model.addAttribute("reservationNo", reservationNo);
		Reservation reservation = new Reservation();
		reservation.setReservationNo(reservationNo);
		reservation.setrState("H");
		int result = rService.updateRstate(reservation);
		if(result > 0) {
			return "board/drmReview/dReviewInsertForm";
		}else {
			return "fail";
		}
	}
	
	
	// 글쓰기 올림  insert
	@ResponseBody
	@RequestMapping(value="dReviewWriterForm.dz", method=RequestMethod.POST)
	public String dReviewRegister(@ModelAttribute DreamReview dreamReview,
								@RequestParam("reservationNo") int reservationNo,
								@RequestParam("drmReviewPublicYN") String drmReviewPublicYN, 
								HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		
		dreamReview.setDrmReviewPublicYN(drmReviewPublicYN); // radio - 공개비공개 선택결과 넣어줌
		dreamReview.setDrmReviewWriter(user.getUserNick());
		dreamReview.setUserType(user.getUserType());
		dreamReview.setUserNo(user.getUserNo());
		System.out.println(dreamReview.toString());
		
		// rState update
		Reservation reservation = new Reservation();
		reservation.setReservationNo(reservationNo);
		reservation.setrState("H");
		
		//db
		int result = 0;
		result = drService.insertDreamReview(dreamReview);
		// rState update
		int rStateResult = rService.updateRstate(reservation);
		if(result > 0 || rStateResult > 0) {
//			mv.setViewName("redirect:dReviewMain.dz");
			return "success";
		} else {
			return "fail";
//			mv.addObject("msg", "감사후기 게시글 등록 실패").setViewName("common/errorPage");
		}
		
//		return mv;
	}
	
	// 삭제 delete
	// @ResponseBody // 스프링에서 ajax를 사용하는데, 그 값을 받아서 쓰고싶을때 반드시 필요함
	@RequestMapping(value="dReviewDelete.dz", method=RequestMethod.GET)
	public String dReviewDelete(@RequestParam("drmReviewNo") int drmReviewNo, Model model) {
		int result = drService.deleteDreamReview(drmReviewNo);
		if(result > 0) {
			return "redirect:dReviewMain.dz";
		} else {
			model.addAttribute("msg", "게시글 삭제에 실패했습니다.");
			return "common/errorPage";
			
		}
	}
	
	
	// 수정버튼누름
	@RequestMapping(value="dReviewUpdateForm.dz", method=RequestMethod.GET)
	public ModelAndView dReviewUpdateView(@RequestParam("drmReviewNo") int drmReviewNo, ModelAndView mv) {
		DreamReview drmReview = drService.selectOneDreamReview(drmReviewNo);
		System.out.println(drmReview.toString());
		if(drmReview != null) {
			mv.addObject("drmReview", drmReview).setViewName("board/drmReview/dReviewUpdateForm");
		} else {
			mv.addObject("수정페이지 접속 실패").setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 수정함 update
	@ResponseBody
	@RequestMapping(value="dReviewModify.dz", method= {RequestMethod.POST, RequestMethod.GET})
	public String dReviewUpdate(@ModelAttribute DreamReview dreamReview, Model model) {
		System.out.println(dreamReview.toString());
		int result = drService.updateDreamReview(dreamReview);
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	//D 가게 상세 감사 + 맛집후기 가져오기
	@RequestMapping(value="mdReviewShop.dz", method=RequestMethod.GET)
	public void selectDmReview(@RequestParam("shopNo") int shopNo, HttpServletResponse response) throws Exception {
		ArrayList<DreamReview> rList = drService.selectDMReviewAll(shopNo);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		Gson gson = new Gson();
		gson.toJson(rList, response.getWriter());
	}
	
	//D 가게 상세 감사후기 가져오기
	@RequestMapping(value="drmReviewShop.dz", method=RequestMethod.GET)
	public void selectDrReview(@RequestParam("shopNo") int shopNo, HttpServletResponse response) throws Exception {
		ArrayList<DreamReview> dReview = drService.selectAllDreamReview(shopNo);

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		Gson gson = new Gson();
		gson.toJson(dReview, response.getWriter());
	}
	
	
	// 마이페이지에서 메인에서 삭제 후 다시 그리기
	@ResponseBody
	@RequestMapping(value = "myPageMainReviewDelete.dz", method = RequestMethod.GET)
	public ArrayList<DreamReview> drmMpMainReviewDelete(@RequestParam("drmReviewNo") int drmReviewNo, HttpSession session) {
		int result = drService.deleteDreamReview(drmReviewNo);

		User user = (User) session.getAttribute("loginUser");
		ArrayList<DreamReview> list = drService.drmRwUptoThree(user.getUserNo());
		System.out.println(list.toString());
		return list;
	}
	
	// 마이페이지 상세페이지에서 삭제 후 다시 그리기
	
	@ResponseBody
	@RequestMapping(value="removeMyPageDrmReview.dz", method = RequestMethod.GET)
	public HashMap<String, Object> removeMyPageDrmReview(@RequestParam("drmReviewNo") int drmReviewNo,
														@RequestParam(value="page", required = false) Integer page,
														HttpSession session){
		User user = (User)session.getAttribute("loginUser");
		int userNo = user.getUserNo();
		int currentPage = (page != null) ? page : 1;
		int listCount = drService.dreamGetListCount(userNo);
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		
		ArrayList<DreamReview> list = drService.deleteAndSelectPick(drmReviewNo, userNo, pi);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("pi",pi);
		hashMap.put("list", list);
		System.out.println(list.toString());
		
		return hashMap;
	}
	
}