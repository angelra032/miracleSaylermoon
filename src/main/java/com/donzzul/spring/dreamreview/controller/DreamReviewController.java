package com.donzzul.spring.dreamreview.controller;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.donzzul.spring.dreamreview.domain.DreamReview;
import com.donzzul.spring.dreamreview.service.DreamReviewService;

@Controller
public class DreamReviewController {
	
	@Autowired
	private DreamReviewService drService;
	
	// 감사후기 주소로 들어옴 (리스트출력할곳) selectAll
	@RequestMapping(value="dReviewMain.dz", method=RequestMethod.GET)
	public String dReviewMainView() {
		return "";
	}
	
	// 디테일 selectOne
	@RequestMapping(value="dReviewDetail.dz", method=RequestMethod.GET)
	public String dReviewDetailView(@RequestParam("dreamReviewNo") int dreamReviewNo) {
		return "";
	}
	// 감사후기 글쓰기버튼으로 들어옴 
	@RequestMapping(value="dReviewWriteView.dz", method=RequestMethod.GET)
	public String dReviewWriteView() {
		return "";
	}
	
	
	// 글쓰기 올림 (사진파일추가) insert
	@RequestMapping(value="dReviewInsertForm.dz", method=RequestMethod.POST)
	public String dReviewRegister(@ModelAttribute DreamReview dreamReview, @RequestParam(value="uploadFile", required=false)MultipartFile uploadFile, HttpServletRequest request) {
		return "";
	}
	
	// 삭제 delete
	// @ResponseBody // 스프링에서 ajax를 사용하는데, 그 값을 받아서 쓰고싶을때 반드시 필요함
	@RequestMapping(value="dReviewDelete.dz", method=RequestMethod.GET)
	public String dReviewDelete(@RequestParam int dreamReviewNo) {
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
