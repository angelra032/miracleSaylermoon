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

import com.donzzul.spring.dreamreview.domain.DreamReview;
import com.donzzul.spring.dreamreview.domain.DreamReviewPage;
import com.donzzul.spring.dreamreview.service.DreamReviewService;
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
		DreamReviewPage pi = getPageInfo(currentPage,listCount);
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
	
		// 페이지 객체를 리턴해주는 메소드
		// 한번만 생성하여 정보를 저장해서 가지고 있을수 있도록 하기위해 static 메소드로 만듬
		public static DreamReviewPage getPageInfo(int currentPage, int listCount) {
			DreamReviewPage pi = null;
			int pageLimit = 5; // 한페이지당 보여줄 네비게이션 갯수
			int boardLimit = 10;	// 한 페이지에서 보여줄 게시글의 갯수
			
			int maxPage;		// 전체페이지 중 가장 마지막 페이지
			int startPage;		// 현재페이지에서 시작하는 첫번째 페이지
			int endPage;		// 현재 페이지에서 끝나는 마지막 페이지
			
			// 일반적인 페이지 계산법 // 0.9의 이유 : 0.1로 나왔을 때 int변환하면 0이 되어버리기 때문에 이를 방지하기 위해서다.
			maxPage = (int)((double) listCount/boardLimit + 0.9);
			startPage = (((int)((double)currentPage/pageLimit + 0.9)) - 1) * pageLimit + 1;
			endPage = startPage + pageLimit - 1;
			
			// 오류방지용
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			
			pi = new DreamReviewPage(currentPage, boardLimit, pageLimit, startPage, endPage, listCount, maxPage);
			return pi;
		}
	
	/// 리스트 끝
	
	// 디테일 selectOne
	@RequestMapping(value="dReviewDetail.dz", method=RequestMethod.GET)
	public ModelAndView dReviewDetailView(ModelAndView mv,@RequestParam("drmRviewNo") int drmRviewNo) {
//		HttpSession session
//		User loginUser = (User)session.getAttribute("loginUser");
//		System.out.println(loginUser.toString());
		DreamReview drmReview = drService.selectOneDreamReview(drmRviewNo);
		if(drmReview != null) {
			mv.addObject("drmReview", drmReview).setViewName("board/drmReview/dReviewDetailView");
		} else {
			mv.addObject("msg", "게시글 상세 조회 실패").setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	// 감사후기 글쓰기버튼으로 들어옴 
	@RequestMapping(value="dReviewWriteView.dz", method=RequestMethod.GET)
	public String dReviewWriteView() {
		return "board/drmReview/dReviewInsertForm";
	}
	
	
	// 글쓰기 올림 (사진파일추가) insert
	@RequestMapping(value="dReviewWriterForm.dz", method=RequestMethod.POST)
	public ModelAndView dReviewRegister(ModelAndView mv, @ModelAttribute DreamReview dreamReview, @RequestParam("drmReviewPublicYN") String drmReviewPublicYN) {
//		@RequestParam(value="uploadFile", required=false)MultipartFile uploadFile, 
		dreamReview.setShopNo('3'); // 임시 데이터(shopNo = 3)
		dreamReview.setDrmReviewPublicYn(drmReviewPublicYN); // radio - 공개비공개 선택결과 넣어줌
		System.out.println(dreamReview.toString());
		
		//db
		int result = 0;
		result = drService.insertDreamReview(dreamReview);
		if(result > 0) {
			mv.setViewName("redirect:dReviewMain.dz");
		} else {
			mv.addObject("msg", "감사후기 게시글 등록 실패").setViewName("common/errorPage");;
		}
		
		return mv;
	}
	
	// 삭제 delete
	// @ResponseBody // 스프링에서 ajax를 사용하는데, 그 값을 받아서 쓰고싶을때 반드시 필요함
	@RequestMapping(value="dReviewDelete.dz", method=RequestMethod.GET)
	public String dReviewDelete(@RequestParam int drmRviewNo) {
		return "";
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
