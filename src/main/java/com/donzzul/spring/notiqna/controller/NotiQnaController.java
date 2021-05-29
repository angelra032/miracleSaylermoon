package com.donzzul.spring.notiqna.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
		Qna qna = qnaService.selectOneQna(qaNo); // qna 조회
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser"); // 세션에서 유저를 불러옴
//		Qna reply = qnaService.selectOneReply(qaNo); // 답글가져와 보내줌
		
		if(qna != null) {
			if(qna.getBoardPublicYN().equals("Y")) { // qna 퍼블릭(없는경우임)
				mv.addObject("qna", qna).setViewName("board/noticeQna/qna/qnaDetailView");
				//mv.addObject("msg", "자신이 쓴 글만 확인할 수 있습니다.").setViewName("common/errorPage");
			} else {
				if(user == null) { // 로그인 안함
					//mv.addObject("msg", "로그인필요").setViewName("common/errorPage");
					mv.setViewName("redirect:/loginView.dz");
				} else if(qna.getUserNo() == user.getUserNo() || user.getUserType().equals("4")) { // 글쓴이와 유저(세션)값이 같음
					//qna.getGroupLayer() == 1 && qna.getUserNo() != user.getUserNo() && qna.getUserType().equals("4")
						Qna reply = qnaService.selectOneReply(qaNo); // 답글가져와 보내줌
						if(reply != null)
							mv.addObject("reply", reply);
					mv.addObject("qna", qna).setViewName("board/noticeQna/qna/qnaDetailView");
				} else if(user.getUserId() == qna.getQnaId() ) {
					Qna reply = qnaService.selectOneReply(qaNo); // 답글가져와 보내줌
					if(reply != null)
						mv.addObject("reply", reply);
					mv.addObject("qna", qna).setViewName("board/noticeQna/qna/qnaDetailView");
				}else {
					//mv.addObject("msg", "다른사람글 확인불가").setViewName("common/errorPage");
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
		qna.setUserType(user.getUserType());
		qna.setQnaId(user.getUserId());
		
		int result = qnaService.insertQna(qna); // 글쓰기
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	// 삭제 delete
	// @ResponseBody // 스프링에서 ajax를 사용하는데, 그 값을 받아서 쓰고싶을때 반드시 필요함
	@RequestMapping(value="qaDelete.dz", method=RequestMethod.GET)
	public String qaDelete(@RequestParam("qnaNo") int qaNo, Model model) {
		int result = qnaService.deleteQna(qaNo);
		if(result > 0) {
			return "redirect:notiQnaMain.dz";
		} else {
			model.addAttribute("msg", "게시글 삭제에 실패했습니다.");
			return "common/errorPage";
		}
	}
	
	
	// 수정버튼누름
	@RequestMapping(value="qaUpdateForm.dz", method=RequestMethod.GET)
	public ModelAndView qaUpdateView(ModelAndView mv, @RequestParam("qnaNo") int qaNo) {
		Qna qna = qnaService.selectOneQna(qaNo);
		if(qna != null) {
			mv.addObject("qna", qna).setViewName("board/noticeQna/qna/qnaUpdateForm");
		} else {
			mv.addObject("msg", "게시글 수정페이지 접속 실패").setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 수정함 update
	@ResponseBody
	@RequestMapping(value="qaModify.dz", method= {RequestMethod.POST, RequestMethod.GET})
	public String qaUpdate(@ModelAttribute Qna qna, Model model) {
		System.out.println("서버단에오나요..");
		int result = qnaService.updateQna(qna);
		System.out.println("리저트아래 " + result);
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
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
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("loginUser");
			System.out.println(notice.toString());
			
			if(user.getUserType().equals("4")) { // 관리자인 경우
				int result = 0;
				notice.setUserType("4");
				result = nService.insertNotice(notice);
				
				if(result > 0) {
					return "success";
				} else {
					return "fail";
				}
			} else {
				return "fail";
			}
		}
		
		// 삭제 delete
		// @ResponseBody // 스프링에서 ajax를 사용하는데, 그 값을 받아서 쓰고싶을때 반드시 필요함
		@RequestMapping(value="noticeDelete.dz", method=RequestMethod.GET)
		public String noticeDelete(@RequestParam("noticeNo") int noticeNo, Model model) {
			int result = nService.deleteNotice(noticeNo);
			if(result > 0) {
				return "redirect:notiQnaMain.dz";
			} else {
				model.addAttribute("msg", "게시글 삭제에 실패했습니다.");
				return "common/errorPage";
			}
		}
		
		
		// 수정버튼누름
		@RequestMapping(value="noticeUpdateForm.dz", method=RequestMethod.GET)
		public ModelAndView noticeUpdateView(ModelAndView mv,@RequestParam("noticeNo") int noticeNo) {
			Notice notice = nService.selectOneNotice(noticeNo);
			if(notice != null) {
				mv.addObject("notice", notice).setViewName("board/noticeQna/notice/noticeUpdateForm");
			} else {
				mv.addObject("msg", "게시글 수정페이지 접속 실패").setViewName("common/errorPage");
			}
			return mv;
		}
		
		// 수정함 update
		@ResponseBody
		@RequestMapping(value="noticeModify.dz", method= {RequestMethod.POST,RequestMethod.GET})
		public String noticeUpdate(@ModelAttribute Notice notice) {
			int result = nService.updateNotice(notice);
			if(result > 0) {
				return "success";
			} else {
				return "fail";
			}
		}
	
}
