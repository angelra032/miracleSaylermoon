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

import com.donzzul.spring.dreamreview.domain.DreamReview;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.mzreview.service.MzReviewService;

@Controller
public class MzReviewController {

	@Autowired
	private MzReviewService mService;
	
	// 맛집후기 주소 selectAll
	@RequestMapping(value="mReviewMain.dz", method=RequestMethod.GET)
	public String mReviewMainView() {
		return "";
	}
	
	// 디테일 selectOne
	@RequestMapping(value="mReviewDetail.dz", method=RequestMethod.GET)
	public String mReviewDetailView(@RequestParam("mzReviewNo") int mzReviewNo) {
		return "";
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
		String path = "";
		result = mService.insertMzReview(mzReview);
		if(result > 0) {
			path = "home";
		} else {
			mv.addObject("msg", "맛집후기 글쓰기 실패");
			path = "common/errorPage";
		}
		
		mv.setViewName(path);
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
