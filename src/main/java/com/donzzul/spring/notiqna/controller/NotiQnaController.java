package com.donzzul.spring.notiqna.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.common.Pagination;
import com.donzzul.spring.notiqna.domain.Notice;
import com.donzzul.spring.notiqna.domain.Qna;
import com.donzzul.spring.notiqna.service.NoticeService;
import com.donzzul.spring.notiqna.service.QnaService;
import com.donzzul.spring.user.domain.User;


@Controller
public class NotiQnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@Autowired
	private NoticeService nService;
	
	// qna 보기(리스트) selectAll
	@RequestMapping(value="notiQnaMain.dz", method=RequestMethod.GET)
	public ModelAndView qnaMainView(ModelAndView mv, @RequestParam(value="page", required=false) Integer page) {
		int currentPage = (page != null) ? page : 1;
		int listCount = qnaService.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<Qna> qList = qnaService.selectAllQna(pi);
		ArrayList<Notice> nList = nService.selectAllNotice();
		System.out.println(qList.toString());
		if(!qList.isEmpty()) {
			mv.addObject("qList", qList).addObject("pi", pi).addObject("nList", nList);
		} else {
			mv.addObject("msg", "게시글이 없습니다");
		}
		mv.setViewName("board/noticeQna/notiQnaListView");
		return mv;
	}
	
	
	// 디테일 selectOne
	@RequestMapping(value="qaDetail.dz", method=RequestMethod.GET)
	public ModelAndView qaDetailView(ModelAndView mv, @RequestParam("qnaNo") int qaNo, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Qna qna = qnaService.selectOneQna(qaNo); // qna 조회해옴
		if(qna != null) {
			if(qna.getBoardPublicYN().equals("Y")) { // qna 퍼블릭(없는경우임)
				mv.addObject("qna", qna).setViewName("board/noticeQna/qna/qnaDetailView");
//				mv.addObject("msg", "자신이 쓴 글만 확인할 수 있습니다.").setViewName("common/errorPage");
			} else {
				HttpSession session = request.getSession();
				User user = (User)session.getAttribute("loginUser"); // 세션에서 유저를 불러옴
				if(user == null) { // 로그인 안함
//					mv.addObject("msg", "로그인필요").setViewName("common/errorPage");
					mv.setViewName("redirect:/loginView.dz");
				} else if(qna.getUserNo() == user.getUserNo()) { // 글쓴이와 유저(세션)값이 같음
					mv.addObject("qna", qna).setViewName("board/noticeQna/qna/qnaDetailView");
				} else {
//					mv.addObject("msg", "다른사람글 확인불가").setViewName("common/errorPage");
					mv.setViewName("redirect:/notiQnaMain.dz");
				}
			}
			
		}
		return mv; 
	}
	
	// 글쓰기버튼으로 들어옴 
	@RequestMapping(value="qaWriteView.dz", method=RequestMethod.GET)
	public String qaWriteView() {
		return "board/noticeQna/qna/qnaInsertForm";
	}
	
	
	// 글쓰기 올림  insert
	@ResponseBody
	@RequestMapping(value="qaInsertForm.dz", method=RequestMethod.POST)
	public String qaRegister(@ModelAttribute Qna qna, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		qna.setQnaWriter(user.getUserNick());
		qna.setUserType(user.getUserType());
		qna.setUserNo(user.getUserNo());
		
		int result = qnaService.insertQna(qna);
		if(result > 0) {
			int updateGroup = qnaService.updateGroup(qna);
//			if(update > 0) {
				return "success";
//			}
//			return "fail";
		} else {
			return "fail";
		}
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
	
//	************************************** 공지사항 영역
	
	
	// 디테일 selectOne
		@RequestMapping(value="noticeDetail.dz", method=RequestMethod.GET)
		public ModelAndView noticeDetailView(ModelAndView mv, @RequestParam("noticeNo") int noticeNo) {
			
			Notice notice = nService.selectOneNotice(noticeNo);
			if(notice != null) {
				mv.addObject("notice", notice).setViewName("board/noticeQna/notice/noticeDetailView");
			} else {
				mv.addObject("msg", "게시글 상세 조회 실패").setViewName("common/errorPage");
			}
			
			return mv;
		}
		
		
		// 감사후기 글쓰기버튼으로 들어옴 
		@RequestMapping(value="noticeWriteView.dz", method=RequestMethod.GET)
		public String noticeWriteView() {
			return "board/noticeQna/notice/noticeInsertForm";
		}
		
		
		// 글쓰기 올림 insert
		@ResponseBody
		@RequestMapping(value="noticeInsertForm.dz", method=RequestMethod.POST)
		public String noticeRegister(@ModelAttribute Notice notice, HttpServletRequest request) {
			System.out.println(notice.toString());
			
			int result = 0;
			result = nService.insertNotice(notice);
			
			if(result > 0) {
				return "success";
			} else {
				return "fail";
			}
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
