package com.donzzul.spring.qna.controller;

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

import com.donzzul.spring.qna.domain.Qna;
import com.donzzul.spring.qna.service.QnaService;

@Controller
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	// qna 보기(리스트) selectAll
	@RequestMapping(value="notiQnaMain.dz", method=RequestMethod.GET)
	public String qnaMainView() {
		return "board/noticeQna/notiQnaListView";
	}
	
	// 디테일 selectOne
	@RequestMapping(value="qaDetail.dz", method=RequestMethod.GET)
	public String qaDetailView(@RequestParam("qnaNo") int qaNo) {
		return "";
	}
	// 감사후기 글쓰기버튼으로 들어옴 
	@RequestMapping(value="qaWriteView.dz", method=RequestMethod.GET)
	public String qaWriteView() {
		return "";
	}
	
	
	// 글쓰기 올림 (사진파일추가) insert
	@RequestMapping(value="qaInsertForm.dz", method=RequestMethod.POST)
	public String qaRegister(@ModelAttribute Qna qna, @RequestParam(value="uploadFile", required=false)MultipartFile uploadFile, HttpServletRequest request) {
		return "";
	}
	
	// 삭제 delete
	// @ResponseBody // 스프링에서 ajax를 사용하는데, 그 값을 받아서 쓰고싶을때 반드시 필요함
	@RequestMapping(value="qaDelete.dz", method=RequestMethod.GET)
	public String qaDelete(@RequestParam int qaNo) {
		return "";
	}
	
	
	// 수정버튼누름
	@RequestMapping(value="qaUpdateForm.dz", method=RequestMethod.GET)
	public String qaUpdateView() {
		return "";
	}
	
	// 수정함 update
	@RequestMapping(value="qaModify.dz", method=RequestMethod.POST)
	public String qaUpdate(@ModelAttribute Qna qna) {
		return "";
	}
	
}
