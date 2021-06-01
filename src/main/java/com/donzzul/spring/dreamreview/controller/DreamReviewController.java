package com.donzzul.spring.dreamreview.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
	public ModelAndView dReviewDetailView(ModelAndView mv,@RequestParam("drmReviewNo") int drmReviewNo, HttpServletRequest request) {
		
		DreamReview drmReview = drService.selectOneDreamReview(drmReviewNo);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		int shopNo = sService.selectShopOne(drmReview.getShopNo()).getShopNo();
//		int shopNo = drmReview.getShopNo();
//		Shop shop = new ShopServiceImpl().selectShopOne(shopNo);
//		String shopName = shop.getShopName();
//		System.out.println(shopName);
//		System.out.println(shopNo);
//		String shopName = new ShopServiceImpl().selectShopOne(shopNo).getShopName();
//		System.out.println(shopName);
		if(drmReview != null) {
			if(drmReview.getDrmReviewPublicYN().equals("Y") || drmReview.getDrmReviewPublicYN().equals("y")) { // 게시글이 있고 공개상태
				mv.addObject("drmReview", drmReview).setViewName("board/drmReview/dReviewDetailView");
			} else {
				if(user == null) { // 게시글 비공개 - 로그인 안함
					mv.setViewName("redirect:/loginView.dz");
				} else if(drmReview.getUserNo() == user.getUserNo() || user.getUserType().equals("4") || drmReview.getShopName() == user.getPartnerName()) { // 게시글 비공개 - 세션결과가 글쓴이와 같은사람
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
	public String dReviewDelete(@RequestParam("drmRviewNo") int drmRviewNo, Model model) {
		int result = drService.deleteDreamReview(drmRviewNo);
		if(result > 0) {
			return "redirect:dReviewMain.dz";
		} else {
			model.addAttribute("msg", "게시글 삭제에 실패했습니다.");
			return "common/errorPage";
			
		}
	}
	
	
	// 수정버튼누름
	@RequestMapping(value="dReviewUpdateForm.dz", method=RequestMethod.GET)
	public ModelAndView dReviewUpdateView(@RequestParam("drmRviewNo") int drmRviewNo, ModelAndView mv) {
		DreamReview drmReview = drService.selectOneDreamReview(drmRviewNo);
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
	
	
	
	// 드림 마이페이지에서 삭제 후 다시 그리기
	@ResponseBody
	@RequestMapping(value = "drmMyPageDelete.dz", method = RequestMethod.GET)
	public ArrayList<DreamReview> drmMyPageDelete(@RequestParam("drmRviewNo") int drmRviewNo, HttpSession session) {
		System.out.println(drmRviewNo);
		int result = drService.deleteDreamReview(drmRviewNo);

		User user = (User) session.getAttribute("loginUser");
		ArrayList<DreamReview> list = drService.drmRwUptoThree(user.getUserNo());

		return list;
	}
	
}
