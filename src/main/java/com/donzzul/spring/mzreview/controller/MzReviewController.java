package com.donzzul.spring.mzreview.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.mzreview.domain.MzReviewPage;
import com.donzzul.spring.mzreview.service.MzReviewService;

@Controller
public class MzReviewController {

	@Autowired
	private MzReviewService mService;
	
	// 맛집후기 주소 selectAll
	@RequestMapping(value="mReviewMain.dz", method=RequestMethod.GET)
	public ModelAndView mReviewMainView(ModelAndView mv,  @RequestParam(value="page", required=false) Integer page) {
		int currentPage = (page != null) ? page : 1;
		int listCount = mService.getListCount();
		MzReviewPage pi = getPageInfo(currentPage, listCount);
		ArrayList<MzReview> mList = mService.selectAllReview(pi);
		System.out.println(mList.toString());
		if(!mList.isEmpty()) {
			mv.addObject("mList", mList).addObject("pi", pi);
		} else {
			mv.addObject("msg", "게시글이 없습니다");
		}
		mv.setViewName("board/mzReview/mReviewListView");
		return mv;
	}
	
	// 페이지 객체를 리턴해주는 메소드
	// 한번만 생성하여 정보를 저장해서 가지고 있을수 있도록 하기위해 static 메소드로 만듬
	public static MzReviewPage getPageInfo(int currentPage, int listCount) {
		MzReviewPage pi = null;
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
		
		pi = new MzReviewPage(currentPage, boardLimit, pageLimit, startPage, endPage, listCount, maxPage);
		return pi;
	}
	/// 리스트 끝
	
	// 디테일 selectOne
	@RequestMapping(value="mReviewDetail.dz", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView mReviewDetailView(ModelAndView mv, @RequestParam("mzReviewNo") int mzReviewNo) {
		MzReview mReview = mService.selectOneReview(mzReviewNo);
		if(mReview != null) {
			mv.addObject("mReview", mReview).setViewName("board/mzReview/mReviewDetailView");
		} else {
			mv.addObject("msg", "맛집후기 게시글 상세 조회 실패").setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 감사후기 글쓰기버튼으로 들어옴 
	@RequestMapping(value="mReviewWriteView.dz", method=RequestMethod.GET)
	public String mReviewWriteView() {
		return "board/mzReview/mReviewInsertForm";
	}
	
	// 글쓰기 올림 (사진파일추가) insert
	@RequestMapping(value="mReviewInsertForm.dz", method=RequestMethod.POST)
	public ModelAndView mReviewRegister(ModelAndView mv, @ModelAttribute MzReview mzReview, HttpServletRequest request) {
//		@RequestParam(value="uploadFile", required=false)MultipartFile uploadFile, 
		System.out.println(mzReview.toString());
		int result = 0;
		result = mService.insertMzReview(mzReview);
		if(result > 0) {
			mv.setViewName("home");
		} else {
			mv.addObject("msg", "맛집후기 글쓰기 실패").setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	// 파일
//		public String saveFile(MultipartFile file, HttpServletRequest request) {
//			// 파일 저장 경로 설정
//			String root = request.getSession().getServletContext().getRealPath("resources");
//			String savePath = root + "\\nuploadFiles";
//			// 저장 폴더 선택
//			File folder = new File(savePath);
//			// 폴더가 없을 경우 자동 생성
//			if(!folder.exists()) {
//				folder.mkdir();
//			}
//			String filePath = folder + "\\" + file.getOriginalFilename();
//			// 파일저장
//			try {
//				file.transferTo(new File(filePath));
//			} catch (IllegalStateException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			// 파일경로 리턴
//			return filePath;
//		}
	
	// 삭제 delete
	// @ResponseBody // 스프링에서 ajax를 사용하는데, 그 값을 받아서 쓰고싶을때 반드시 필요함
	@RequestMapping(value="mReviewDelete.dz", method=RequestMethod.GET)
	public String mReviewDelete(@RequestParam int mzReviewNo) {
		return "";
	}
	
	// 수정버튼누름 (페이지)
	@RequestMapping(value="mReviewUpdateForm.dz", method=RequestMethod.GET)
	public String mReviewUpdateView() {
		return "";
	}
	
	// 수정함 update
	@RequestMapping(value="mReviewModify.dz", method=RequestMethod.POST)
	public String mReviewUpdate(@ModelAttribute MzReview mzReview) {
		return "";
	}
	
	//D 맛집후기 가져오기
	@RequestMapping(value="mzReviewShop.dz", method=RequestMethod.GET)
	public String selectMzReview(@RequestParam("shopNo") int shopNo, Model model) {
		ArrayList<MzReview> mReview = mService.selectAllReview(shopNo);
		return "";
	}
}
