package com.donzzul.spring.mzrecommendation.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.donzzul.spring.dreamreview.domain.DreamReview;
import com.donzzul.spring.mzrecommendation.domain.MzRecommendation;
import com.donzzul.spring.mzrecommendation.service.MzRecommendationService;

@Controller
public class MzRecommendationController {
	
	@Autowired
	private MzRecommendationService mzService;
	
	// 주소로 들어옴 (리스트출력할곳) selectAll
	@RequestMapping(value="recommendMain.kh", method=RequestMethod.GET)
	public String recommendMainView() {
		return "";
	}
	
	
	// 디테일 selectOne
	@RequestMapping(value="recommendDetail.kh", method=RequestMethod.GET)
	public String recommendDetailView(@RequestParam("recommendationNo") int recommendationNo) {
		return "";
	}
	
	// 감사후기 글쓰기버튼으로 들어옴 
	@RequestMapping(value="recommendWriteView.kh", method=RequestMethod.GET)
	public String recommendWriteView() {
		return "";
	}
	
	// 글쓰기 올림 (사진파일추가) insert
	@RequestMapping(value="recommendInsertForm.kh", method=RequestMethod.POST)
	public String recommendRegister(@ModelAttribute MzRecommendation mzRecommendation, @RequestParam(value="uploadFile", required=false)MultipartFile uploadFile, HttpServletRequest request) {
		return "";
	}
	
	// 파일
//	public String saveFile(MultipartFile file, HttpServletRequest request) {
//		// 파일 저장 경로 설정
//		String root = request.getSession().getServletContext().getRealPath("resources");
//		String savePath = root + "\\nuploadFiles";
//		// 저장 폴더 선택
//		File folder = new File(savePath);
//		// 폴더가 없을 경우 자동 생성
//		if(!folder.exists()) {
//			folder.mkdir();
//		}
//		String filePath = folder + "\\" + file.getOriginalFilename();
//		// 파일저장
//		try {
//			file.transferTo(new File(filePath));
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// 파일경로 리턴
//		return filePath;
//	}

	// 삭제 delete
	// @ResponseBody // 스프링에서 ajax를 사용하는데, 그 값을 받아서 쓰고싶을때 반드시 필요함
	@RequestMapping(value="recommendDelete.kh", method=RequestMethod.GET)
	public String recommendDelete(@RequestParam int recommendationNo) {
		return "";
	}
	
	
	// 수정버튼누름
	@RequestMapping(value="recommendUpdateForm.kh", method=RequestMethod.GET)
	public String recommendUpdateView() {
		return "";
	}
	
	// 수정함 update
	@RequestMapping(value="recommendModify.kh", method=RequestMethod.GET)
	public String recommendUpdate(@ModelAttribute MzRecommendation mzRecommendation) {
		return "";
	}
}
