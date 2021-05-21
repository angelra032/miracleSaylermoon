package com.donzzul.spring.notiqna.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.donzzul.spring.notiqna.domain.Notice;
import com.donzzul.spring.notiqna.domain.NoticePage;
import com.donzzul.spring.notiqna.domain.Qna;
import com.donzzul.spring.notiqna.domain.QnaPage;
import com.donzzul.spring.notiqna.service.NoticeService;
import com.donzzul.spring.notiqna.service.QnaService;


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
		QnaPage pi = getQnaPageInfo(currentPage, listCount);
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
	
	// 페이징
	public QnaPage getQnaPageInfo(int currentPage, int listCount) {
		QnaPage pi = null;
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
		
		pi = new QnaPage(currentPage, boardLimit, pageLimit, startPage, endPage, listCount, maxPage);
		return pi;
	}
	//페이징 끝
	
	// 디테일 selectOne
	@RequestMapping(value="qaDetail.dz", method=RequestMethod.GET)
	public ModelAndView qaDetailView(ModelAndView mv, @RequestParam("qnaNo") int qaNo) {
		Qna qna = qnaService.selectOneQna(qaNo);
		if(qna != null) {
			mv.addObject("qna", qna).setViewName("board/noticeQna/qnaDetailView");
		} else {
			mv.addObject("msg", "게시글 상세 조회 실패").setViewName("common/errorPage");
		}
		return mv; 
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
	
//	************************************** 공지사항 영역
	
	
	// 디테일 selectOne
		@RequestMapping(value="noticeDetail.dz", method=RequestMethod.GET)
		public ModelAndView noticeDetailView(ModelAndView mv, @RequestParam("noticeNo") int noticeNo) {
			
			Notice notice = nService.selectOneNotice(noticeNo);
			if(notice != null) {
				mv.addObject("notice", notice).setViewName("board/noticeQna/noticeDetailView");
			} else {
				mv.addObject("msg", "게시글 상세 조회 실패").setViewName("common/errorPage");
			}
			
			return mv;
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
