package com.donzzul.spring.adminpage.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	// @SuppressWarnings("null")
	@RequestMapping(value="adminPage.dz")
	public ModelAndView adminPageView(ModelAndView mv) {
		DecimalFormat formatter = new DecimalFormat("###,###.##");
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+2; // 이번달주소
		int lastMonth = cal.get(Calendar.MONTH); // 지난달주소
		
		// *** 달별 포인트 출력
		ArrayList<DonCount> monthSumList = null;
		HashMap<String, String> monthSum = new HashMap<String, String>();
		for(int i = 2; i <= month; i++) {
			// 이전달
			String lastMonthString = Integer.toString(year);
			if(lastMonth < 10) {
				lastMonthString += "0" + Integer.toString(i-1) + "01"; 
			} else {
				lastMonthString += Integer.toString(i-1) + "01";
			}
			// 현재달
			String thisMonth = Integer.toString(year);
			if(month < 10) {
				thisMonth += "0" + Integer.toString(i) + "01";
			} else {
				thisMonth += 	Integer.toString(i) + "01";
			}
			HashMap<String, String> dateMap = new HashMap<String, String>();
			dateMap.put("date1", lastMonthString);
			dateMap.put("date2", thisMonth);
			System.out.println("테스트 ******* : " + lastMonth);
			monthSumList = pService.selectAllDonListSum(dateMap);
			int monthPriceSum = monthSumList.get(0).getDonPriceSum();
			monthSum.put(Integer.toString(i-1), Integer.toString(monthPriceSum));
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
			ArrayList<User> userList = uService.selectUserListThree(); // *** 회원출력
			ArrayList<Shop> shopList = sService.selectAllShopListThree(); // *** 사업자출력
			ArrayList<Notice> nList = nService.selectAdminNoticeThree();
			mv.addObject("YearDon", YearDon);
			mv.addObject("monthSum", monthSum);
			mv.addObject("userList", userList);
			mv.addObject("shopList", shopList);
			mv.addObject("nList", nList);
			
			mv.addObject("Smsg", "사업자 데이터가 없습니다.");
			mv.addObject("Umsg", "유저 데이터가 없습니다.");
			mv.addObject("Nmsg", "게시판 데이터가 없습니다.");
			
			mv.setViewName("adminPage/adminPage");
		} catch (Exception e) {
			mv.addObject("Smsg", "사업자 데이터가 없습니다.");
			mv.addObject("Umsg", "유저 데이터가 없습니다.");
			mv.addObject("Nmsg", "게시판 데이터가 없습니다.");
			
			mv.setViewName("adminPage/adminPage");
		}
		
		
		return mv;
	}
	
	// 포인트 출력
	@RequestMapping(value="adminPointList.dz")
	public ModelAndView allPoint(ModelAndView mv) {
		DecimalFormat formatter = new DecimalFormat("###,###.##");
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+2; // 이번달주소
		int lastMonth = cal.get(Calendar.MONTH); // 지난달주소
		
		// *** 달별 포인트 출력
		ArrayList<DonCount> monthSumList = null;
		HashMap<String, String> monthSum = new HashMap<String, String>();
		for(int i = 2; i <= month; i++) {
			// 이전달
			String lastMonthString = Integer.toString(year);
			if(lastMonth < 10) {
				lastMonthString += "0" + Integer.toString(i-1) + "01"; 
			} else {
				lastMonthString += Integer.toString(i-1) + "01";
			}
			// 현재달
			String thisMonth = Integer.toString(year);
			if(month < 10) {
				thisMonth += "0" + Integer.toString(i) + "01";
			} else {
				thisMonth += 	Integer.toString(i) + "01";
			}
			HashMap<String, String> dateMap = new HashMap<String, String>();
			dateMap.put("date1", lastMonthString);
			dateMap.put("date2", thisMonth);
			System.out.println("테스트 ******* : " + lastMonth);
			monthSumList = pService.selectAllDonListSum(dateMap);
			int monthPriceSum = monthSumList.get(0).getDonPriceSum();
			monthSum.put(Integer.toString(i-1), monthSumList.get(0).toString());
		}
//		for(int i = 2; i <= month; i++) {
//			String thisMonth = Integer.toString(year);
//			thisMonth += "0" + Integer.toString(i) + "01";
//			HashMap<String, String> dateMap = new HashMap<String, String>();
//			dateMap.put("date1", "20210101");
//			dateMap.put("date2", thisMonth);
//			System.out.println("테스트 *** : " + thisMonth);
//			monthSumList = pService.selectAllDonListSum(dateMap);
//			int monthPriceSum = monthSumList.get(0).getDonPriceSum();
//			System.out.println("확인가능한가 *************** : " + monthSumList.get(0).toString());
//			monthSum.put(Integer.toString(i-1), monthSumList.get(0).toString());
//		}
		
		// *** 총 포인트 출력
		String monthDate = Integer.toString(year);
		monthDate = monthDate + "0" + Integer.toString(month) +"01";
		
		HashMap<String, String> yearSumDonMap = new HashMap<String, String>();
		yearSumDonMap.put("date1", "20210101");
		yearSumDonMap.put("date2", monthDate);
		ArrayList<DonCount> yearCountList = pService.selectAllDonListSum(yearSumDonMap);  // *** 포인트총합
		int priceSum = yearCountList.get(0).getDonPriceSum();
		int userPoint = yearCountList.get(0).getDonUserPoint();
		int don = yearCountList.get(0).getDonJjul();
		int partnerPoint = yearCountList.get(0).getDonPartnerPoint();
		String yearDonPriceSum = formatter.format(priceSum);
		String yearDonUserPoint = formatter.format(userPoint);
		String yearDonJjul =  formatter.format(don);
		String yearDonPartnerPoint =  formatter.format(partnerPoint);
		
		try {
			mv.addObject("monthSum", monthSum);
			mv.addObject("yearDonPriceSum", yearDonPriceSum).addObject("yearDonUserPoint", yearDonUserPoint).addObject("yearDonJjul", yearDonJjul).addObject("yearDonPartnerPoint", yearDonPartnerPoint);
			mv.addObject("month", month);
			mv.setViewName("adminPage/adminPointList");
		} catch (Exception e) {
			mv.addObject("msg", "내역을 출력하는데 실패했습니다.");
			mv.setViewName("common/errorPage");
		}
		return mv;
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
	
	// 회원관리 디테일결과
	@RequestMapping(value="adminShopInfo.dz")
	public ModelAndView InfoInfoView (ModelAndView mv, @RequestParam("userNo") int userNo) {
		User user = uService.selectOneUserByNo(userNo);
		if(user != null) {
			mv.addObject("user", user).setViewName("adminPage/adminUserInfo");
			
		} else {
			mv.addObject("msg", "유저정보 못불러왔음").setViewName("common/errorPage");
		}
		return mv;
	}

	
	// 회원관리 더보기
	@RequestMapping(value="adminUserList.dz")
	public ModelAndView userListPrint(ModelAndView mv, @RequestParam(value="page", required=false) Integer page, @RequestParam("type") String type) {
		int currentPage = (page != null) ? page : 1;
		HashMap<String, String> userType = new HashMap<String, String>();
		HashMap<String, String> pageType = new HashMap<String, String>();
		userType.put("userType", type);
		pageType.put("pageType", type);
		int listCount = uService.getListCount(pageType);
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<User> userList = uService.selectAllUserList(pi, userType);
		
//		ArrayList<User> userList = uService.selectAllUserList();
		if(!userList.isEmpty()) {
			mv.addObject("userList", userList).addObject("pi", pi).addObject("type", type);
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
	
	// 유저리스트 회원탈퇴
	@ResponseBody
	@RequestMapping(value = "adminUserDelete.dz", method = RequestMethod.GET)
	public String userDelete(@RequestParam("userNo") int userNo, Model model) {
		int result = uService.deleteUser(userNo);
		if (result>0) {
			return "success";
		}else {
//			model.addAttribute("msg", "회원탈퇴 실패");
			return "fail";
		}
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
		int listCount = qService.getAdminListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<Qna> qnaList = qService.adminQnaList(pi);
		for (int i = 0; i < qnaList.size(); i++) {
			String date = qnaList.get(i).getQanCreateDate().split(" ")[0];
			qnaList.get(i).setQanCreateDate(date); 
		}
		
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

	// 사업자 승인등록 shop 업데이트
	@ResponseBody
	@RequestMapping(value="adminShopShow.dz",  method = RequestMethod.GET)
	public String adminShopShow(@RequestParam("shopNo") int shopNo) {
		int result = sService.updatePartnerShopShow(shopNo);
		if(result > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	// 사업자리스트 사업자환급금액조회
	@ResponseBody
	@RequestMapping(value="adminShopPointSelect.dz",  method = {RequestMethod.GET, RequestMethod.POST})
	public Map<String, String> adminShopPointSelect(@RequestParam("shopNo") int shopNo, Model model) {
		Map<String, String> map = new HashMap<String, String>();
		Shop shop = sService.selectShopOne(shopNo);
		int userNo = sService.selectShopOne(shopNo).getUserNo();
		int point = uService.selectOneUserByNo(userNo).getUserPoint();
		if(shop != null) {
			map.put("shopName", shop.getShopName());
			map.put("shopAddr", shop.getShopShortAddr());
			map.put("shopPhone", shop.getShopPhone());
			map.put("point", Integer.toString(point));
			map.put("result", "success");
			return map;
		} else {
			map.put("result", "fail");
			return map;
		}
	}
	
	// 사업자리스트 환급버튼
	@ResponseBody
	@RequestMapping(value="adminShopChangePoint.dz",  method = {RequestMethod.GET, RequestMethod.POST})
	public Map<String, String> adminShopChangePoint(@RequestParam("shopNo") int shopNo, Model model) {
		Map<String, String> map = new HashMap<String, String>();
		int userNo = sService.selectShopOne(shopNo).getUserNo();
		int result = pService.updateAdminPointViewNo(shopNo, userNo);
		if(result > 0) {
			map.put("result", "success");
			return map;
		} else {
			map.put("result", "fail");
			return map;
		}
	}
	
}
