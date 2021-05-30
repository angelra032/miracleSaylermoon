package com.donzzul.spring.adminpage.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.common.Pagination;
import com.donzzul.spring.dreamreview.domain.DreamReview;
import com.donzzul.spring.dreamreview.service.DreamReviewService;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.mzreview.service.MzReviewService;
import com.donzzul.spring.notiqna.domain.Notice;
import com.donzzul.spring.notiqna.domain.Qna;
import com.donzzul.spring.notiqna.service.NoticeService;
import com.donzzul.spring.notiqna.service.QnaService;
import com.donzzul.spring.payment.domain.Don;
import com.donzzul.spring.payment.domain.DonCount;
import com.donzzul.spring.payment.service.PaymentService;
import com.donzzul.spring.recommendboard.domain.RecommendBoard;
import com.donzzul.spring.recommendboard.service.RecommendBoardService;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.shop.service.ShopService;
import com.donzzul.spring.user.domain.User;
import com.donzzul.spring.user.service.UserService;

@Controller
public class AdminPageController {

	@Autowired
	private PaymentService pService;
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private ShopService sService;
	
	@Autowired
	private NoticeService nService;
	
	@Autowired
	private QnaService qService;
	
	@Autowired
	private MzReviewService mrService;
	
	@Autowired
	private DreamReviewService drReview;
	
	@Autowired
	private RecommendBoardService rService;
	
	// 관리자 페이지 출력
	@RequestMapping(value="adminPage.dz")
	public ModelAndView adminPageView(ModelAndView mv) {
		DecimalFormat formatter = new DecimalFormat("###,###.##");
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+2; // 이번달주소
		
		ArrayList<DonCount> monthSumList = null;
		HashMap<String, ArrayList<DonCount>> monthSum = new HashMap<String, ArrayList<DonCount>>();
		for(int i = 1; i <= month; i++) {
			String thisMonth = Integer.toString(year);
			thisMonth += "0" + Integer.toString(i) + "01";
			System.out.println("테스트 *** : " + thisMonth);
			HashMap<String, String> dateMap = new HashMap<String, String>();
			dateMap.put("date1", "20210101");
			dateMap.put("date1", thisMonth);
			monthSumList = pService.selectAllDonListSum(dateMap);
			monthSum.put(Integer.toString(i), monthSumList);
		}
		
		String monthDate = Integer.toString(year);
		monthDate = monthDate + "0" + Integer.toString(month) +"01";
		System.out.println("테스트 : " + monthDate);
		
		
		
		// *** 총합 포인트출력
		HashMap<String, String> yearSumDonMap = new HashMap<String, String>();
		yearSumDonMap.put("date1", "20210101");
		yearSumDonMap.put("date2", monthDate);
		ArrayList<DonCount> yearCountList = pService.selectAllDonListSum(yearSumDonMap);  // *** 포인트총합
		int priceSum = yearCountList.get(0).getDonPriceSum();
		String YearDon = formatter.format(priceSum);
		
		
		try {
//			ArrayList<Don> pList = pService.selectAllDonList(dateMap);  // *** 포인트월별
			ArrayList<User> userList = uService.selectUserListThree(); // *** 회원출력
			ArrayList<Shop> shopList = sService.selectAllShopListThree(); // *** 사업자출력
			mv.addObject("YearDon", YearDon);
			mv.addObject("monthSumList", monthSumList);
			mv.addObject("userList", userList);
			mv.addObject("shopList", shopList);
			
			mv.addObject("Smsg", "사업자 데이터가 없습니다.");
			mv.addObject("Umsg", "유저 데이터가 없습니다.");
			mv.addObject("Smsg", "사업자 데이터가 없습니다.");
			
			mv.setViewName("adminPage/adminPage");
		} catch (Exception e) {
			mv.addObject("msg", "내역을 출력하는데 실패했습니다.");
			mv.setViewName("common/errorPage");
		}
		
		
		return mv;
	}
	
	// 포인트 출력
	@RequestMapping(value="allPointAdmin.dz")
	public String allPoint() {
		
		return "";
	}
	
	public void test(String monthDate) {
//		HashMap<String, String> dateMap = new HashMap<String, String>();
//		dateMap.put("date1", "20210101");
//		dateMap.put("date2", "20210601");
//		ArrayList<Don> don = pService.selectAllDonList(dateMap);
//		ArrayList<DonCount> donCount = pService.selectAllDonListSum(dateMap);
//		System.out.println(don.toString());
//		System.out.println(donCount.toString());
	}
	
	// 사업자관리 더보기
	@RequestMapping(value="adminShopList.dz")
	public ModelAndView shopListPrint(ModelAndView mv, @RequestParam(value="page", required=false) Integer page) {
		int currentPage = (page != null) ? page : 1;
		int listCount = sService.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<Shop> shopList = sService.selectAllShopList(pi);
//		ArrayList<Shop> shopList = sService.selectAllShopListDESC();
		if(!shopList.isEmpty()) {
			mv.addObject("shopList", shopList).addObject("pi", pi);
		} else {
			mv.addObject("msg", "불러올 데이터가 없습니다");
		}
		mv.setViewName("adminPage/adminShopList");
		return mv;
	}
	
	// 회원관리 더보기
	@RequestMapping(value="adminUserList.dz")
	public ModelAndView userListPrint(ModelAndView mv, @RequestParam(value="page", required=false) Integer page) {
		int currentPage = (page != null) ? page : 1;
		int listCount = uService.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<User> userList = uService.selectAllUserList(pi);
		
//		ArrayList<User> userList = uService.selectAllUserList();
		if(!userList.isEmpty()) {
			mv.addObject("userList", userList).addObject("pi", pi);
		} else {
			mv.addObject("msg", "불러올 데이터가 없습니다");
		}
		mv.setViewName("adminPage/adminUserList");
		return mv;
	}
	
	// 회원관리 디테일결과
	@RequestMapping(value="adminUserInfo.dz")
	public ModelAndView userInfoView (ModelAndView mv, @RequestParam("userNo") int userNo) {
		User user = uService.selectOneUserByNo(userNo);
		if(user != null) {
			mv.addObject("user", user).setViewName("adminPage/adminUserInfo");
			
		} else {
			mv.addObject("msg", "유저정보 못불러왔음").setViewName("common/errorPage");
		}
		return mv;
	}
	
	/////////// 게시판관리 더보기
	// 맛집후기 리스트
	@RequestMapping(value="adminMReviewList.dz")
	public ModelAndView mReviewListPrint(ModelAndView mv, @RequestParam(value="page", required=false) Integer page) {
		int currentPage = (page != null) ? page : 1;
		int listCount = mrService.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		
		ArrayList<MzReview> mRList = mrService.selectAllReview(pi);
		if(!mRList.isEmpty()) {
			mv.addObject("mRList", mRList).addObject("pi", pi);
		} else {
			mv.addObject("msg", "불러올 데이터가 없습니다");
		}
		mv.setViewName("adminPage/adminMzReviewList");
		return mv;
	}
	
	// 감사후기 리스트
	@RequestMapping(value="adminDrmReviewList.dz")
	public ModelAndView drmReviewListPrint(ModelAndView mv, @RequestParam(value="page", required=false) Integer page) {
		int currentPage = (page != null) ? page : 1;
		int listCount = drReview.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<DreamReview> dRList = drReview.selectAllDreamReview(pi);
		
		if(!dRList.isEmpty()) {
			mv.addObject("dRList", dRList ).addObject("pi", pi);
			
		} else {
			mv.addObject("msg", "불러올 데이터가 없습니다");
		}
		mv.setViewName("adminPage/adminDrmReviewList");
		return mv;
	}
	
	// 가게추천 리스트
	@RequestMapping(value="adminRecommendList.dz")
	public ModelAndView RecommendListPrint(ModelAndView mv, @RequestParam(value="page", required=false) Integer page) {
		int currentPage = (page != null) ? page : 1;
		int listCount = rService.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<RecommendBoard> rRList = rService.selectAllRecommend(pi);
		
		if(!rRList.isEmpty()) {
			mv.addObject("rRList", rRList ).addObject("pi", pi);
			
		} else {
			mv.addObject("msg", "불러올 데이터가 없습니다");
		}
		mv.setViewName("adminPage/adminRecommendList");
		return mv;
	}
	
	// QnA 리스트
	@RequestMapping(value="adminQnaList.dz")
	public ModelAndView qnaListPrint(ModelAndView mv, @RequestParam(value="page", required=false) Integer page) {
		int currentPage = (page != null) ? page : 1;
		int listCount = qService.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<Qna> qnaList = qService.adminQnaList(pi);
		
		if(!qnaList.isEmpty()) {
			mv.addObject("qnaList", qnaList).addObject("pi", pi);
		} else {
			mv.addObject("msg", "불러올 데이터가 없습니다");
		}
		mv.setViewName("adminPage/adminQnaList");
		return mv;
	}
	
	
	// 공지사항 리스트
	@RequestMapping(value="adminNoticeList.dz")
	public ModelAndView noticeListPrint(ModelAndView mv, @RequestParam(value="page", required=false) Integer page) {
		int currentPage = (page != null) ? page : 1;
		int listCount = nService.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<Notice> noticeList = nService.selectAllNotice(pi);
		
		if(!noticeList.isEmpty()) {
			mv.addObject("noticeList", noticeList).addObject("pi", pi);
		} else {
			mv.addObject("msg", "불러올 데이터가 없습니다");
		}
		mv.setViewName("adminPage/adminNoticeList");
		return mv;
	}
	
	///////
	
	// QnA 리플답글 페이지로 들어간다.
	@RequestMapping(value="QnAReplyWrite.dz")
	public ModelAndView qnaReplyWriteView (ModelAndView mv, @RequestParam("qnaNo") int qnaNo, @RequestParam("userNo") int userNo, @RequestParam("qnaId") String qnaId) {		
		mv.addObject("originalQnaNo", qnaNo).addObject("originalUserNo", userNo).addObject("qnaId", qnaId).setViewName("adminPage/adminQnaReplyForm");
		return mv;
	}
	
	// QnA 답글달음
	@ResponseBody
	@RequestMapping(value="QnAReplyInsert.dz")
	public String qnaReplyInsert (@RequestParam("originalQnaNo") int originalQnaNo, @RequestParam("originalUserNo") int originalUserNo, @RequestParam("qnaId") String qnaId, @ModelAttribute Qna Replyqna, HttpServletRequest request) {
		Replyqna.setOriginNo(originalQnaNo);
		Replyqna.setUserNo(originalUserNo);
		Replyqna.setQnaId(qnaId);
		int ReplyResult = qService.insertReply(Replyqna); // 답글달림
		if(ReplyResult > 0) {
			int result = qService.updateQnaReply(originalQnaNo); // 원글에 답글 달림을 업데이트
			if(result > 0) {
				return "success";
			} else {
				return "fail";
			}
		} else {
			return "fail";
		}
	}

	
	
}
