package com.donzzul.spring.notice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.donzzul.spring.notice.service.NoticeService;
import com.donzzul.spring.notice.domain.Notice;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService nService;
	
	// notice 보기(리스트) selectAll
	@RequestMapping(value="noticeMain.dz", method=RequestMethod.GET)
	public String noticeMainView() {
		return "";
	}
	
	// 디테일 selectOne
	@RequestMapping(value="noticeDetail.dz", method=RequestMethod.GET)
	public String noticeDetailView(@RequestParam("noticeNo") int noticeNo) {
		return "";
	}
	
	
	// 감사후기 글쓰기버튼으로 들어옴 
	@RequestMapping(value="noticeWriteView.dz", method=RequestMethod.GET)
	public String noticeWriteView() {
		return "board/noticeQna/noticeInsertForm";
	}
	
	
	// 글쓰기 올림 (사진파일추가) insert
	@RequestMapping(value="noticeInsertForm.dz", method=RequestMethod.POST)
	public ModelAndView noticeRegister(ModelAndView mv, @ModelAttribute Notice notice, HttpServletRequest request) {
		System.out.println(notice.toString());
		int result = 0;
		String	path = "";
		result = nService.insertNotice(notice);
		
		if(result > 0) {
			path = "home";
		} else {
			mv.addObject("msg", "게시글 등록 실패");
			path = "common/errorPage";
		}
		mv.setViewName(path);
		return mv;
	}
	
	// 삭제 delete
	// @ResponseBody // 스프링에서 ajax를 사용하는데, 그 값을 받아서 쓰고싶을때 반드시 필요함
	@RequestMapping(value="noticeDelete.dz", method=RequestMethod.GET)
	public String noticeDelete(@RequestParam int noticeNo) {
		return "";
	}
	
	
	// 수정버튼누름
	@RequestMapping(value="noticeUpdateForm.dz", method=RequestMethod.GET)
	public String noticeUpdateView() {
		return "";
	}
	
	// 수정함 update
	@RequestMapping(value="noticeModify.dz", method=RequestMethod.POST)
	public String noticeUpdate(@ModelAttribute Notice notice) {
		return "";
	}
}
