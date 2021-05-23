package com.donzzul.spring.dreamreview.controller;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
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
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.shop.service.logic.ShopServiceImpl;
import com.donzzul.spring.user.domain.User;

@Controller
public class DreamReviewController {
	
	@Autowired
	private DreamReviewService drService;
	
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
	public ModelAndView dReviewDetailView(ModelAndView mv,@RequestParam("drmReviewNo") int drmReviewNo) {
		
		DreamReview drmReview = drService.selectOneDreamReview(drmReviewNo);
//		int shopNo = drmReview.getShopNo();
//		Shop shop = new ShopServiceImpl().selectShopOne(shopNo);
//		String shopName = shop.getShopName();
//		System.out.println(shopName);
//		System.out.println(shopNo);
//		String shopName = new ShopServiceImpl().selectShopOne(shopNo).getShopName();
//		System.out.println(shopName);
		if(drmReview != null) {
			mv.addObject("drmReview", drmReview).setViewName("board/drmReview/dReviewDetailView");
		} else {
			mv.addObject("msg", "게시글 상세 조회 실패").setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	// 감사후기 글쓰기버튼으로 들어옴 
	@RequestMapping(value="dReviewWriteView.dz", method=RequestMethod.GET)
	public String dReviewWriteView(@RequestParam("shopNo") int shopNo,Model model) {
		model.addAttribute("shopNo", shopNo);
		return "board/drmReview/dReviewInsertForm";
	}
	
	
	// 글쓰기 올림  insert
	@ResponseBody
	@RequestMapping(value="dReviewWriterForm.dz", method=RequestMethod.POST)
	public String dReviewRegister(@ModelAttribute DreamReview dreamReview, @RequestParam("drmReviewPublicYN") String drmReviewPublicYN, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		
		dreamReview.setDrmReviewPublicYn(drmReviewPublicYN); // radio - 공개비공개 선택결과 넣어줌
		dreamReview.setDrmReviewWriter(user.getUserNick());
		dreamReview.setUserType(user.getUserType());
		dreamReview.setUserNo(user.getUserNo());
		System.out.println(dreamReview.toString());
		
		//db
		int result = 0;
		result = drService.insertDreamReview(dreamReview);
		if(result > 0) {
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
	public String dReviewUpdateView() {
		return "";
	}
	
	// 수정함 update
	@RequestMapping(value="dReviewModify.dz", method=RequestMethod.POST)
	public String dReviewUpdate(@ModelAttribute DreamReview dreamReview) {
		return "";
	}
	
	//D 감사후기 가져오기
	@RequestMapping(value="drnReviewShop.dz", method=RequestMethod.GET)
	public String selectDrReview(@RequestParam("shopNo") int shopNo, Model model) {
		ArrayList<DreamReview> dReview = drService.selectAllDreamReview(shopNo);
		return "";
	}
}
