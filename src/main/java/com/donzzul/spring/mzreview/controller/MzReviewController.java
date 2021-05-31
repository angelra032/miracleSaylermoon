package com.donzzul.spring.mzreview.controller;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.common.Pagination;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.mzreview.service.MzReviewService;
import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.shop.service.ShopService;
import com.donzzul.spring.user.domain.User;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

@Controller
public class MzReviewController {

	@Autowired
	private MzReviewService mService;
	@Autowired
	private ShopService sService;
	
	// 맛집후기 주소 selectAll
	@RequestMapping(value="mReviewMain.dz", method=RequestMethod.GET)
	public ModelAndView mReviewMainView(ModelAndView mv,  @RequestParam(value="page", required=false) Integer page) {
		int currentPage = (page != null) ? page : 1;
		int listCount = mService.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		ArrayList<MzReview> mList = mService.selectAllReview(pi);
		System.out.println(mList.toString());
		if(!mList.isEmpty()) {
			mv.addObject("mList", mList).addObject("pi", pi);
		} else {
			mv.addObject("msg", "게시글이 없습니다");
		}
		mv.setViewName("board/mzReview/mReviewListView");
		return mv;
	}
	
	// 디테일 selectOne
	@RequestMapping(value="mReviewDetail.dz", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView mReviewDetailView(ModelAndView mv, @RequestParam("mzReviewNo") int mzReviewNo) {
		MzReview mReview = mService.selectOneReview(mzReviewNo);
		if(mReview != null) {
			mv.addObject("mReview", mReview).setViewName("board/mzReview/mReviewDetailView");
		} else {
			mv.addObject("msg", "맛집후기 게시글 상세 조회 실패").setViewName("common/errorPage");
		}
		return mv;
	}
	
	// 맛집후기 작성페이지 - 글쓰기버튼으로 들어옴 *****
	@RequestMapping(value="mReviewWriteView.dz", method=RequestMethod.GET)
	public String mReviewWriteView(@RequestParam("shopNo") int shopNo, @RequestParam("reservationNo") int reservationNo, Model model ) {
		Shop shop = sService.selectShopOne(shopNo);
		model.addAttribute("shopNo", shopNo);
		model.addAttribute("reservationNo", reservationNo);
		model.addAttribute("shop", shop);
		return "board/mzReview/mReviewInsertForm";
	}
	
	// 글쓰기 올림 (사진파일추가) insert
	@RequestMapping(value="mReviewInsertForm.dz", method=RequestMethod.POST)
	public ModelAndView mReviewRegister(ModelAndView mv, 
										@ModelAttribute MzReview mzReview, 
										@RequestParam("reservationNo") int reservationNo, 
										HttpServletRequest request) {
//		@RequestParam(value="uploadFile", required=false)MultipartFile uploadFile, 
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		mzReview.setmReviewWriter(user.getUserNick());
		mzReview.setUserType(user.getUserType());
		mzReview.setUserNo(user.getUserNo());
		System.out.println(mzReview.toString());
		
		// rState update 예약상태변경
		Reservation reservation = new Reservation();
		reservation.setReservationNo(reservationNo);
		reservation.setrState("H");
		
		int result = 0;
		result = mService.insertMzReview(mzReview, reservation);
		if(result > 0) {
			mv.setViewName("home");
		} else {
			mv.addObject("msg", "맛집후기 글쓰기 실패").setViewName("common/errorPage");
		}
		
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
	public String mReviewDelete(@RequestParam("mReviewNo") int mReviewNo, Model model) {
		int result = mService.deleteMzReview(mReviewNo);
		if(result > 0) {
			return "redirect:mReviewMain.dz";
		} else {
			model.addAttribute("msg", "게시글 삭제에 실패했습니다.");
			return "common/errorPage";
		}
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
	public void selectMzReview(@RequestParam("shopNo") int shopNo, HttpServletResponse response) throws Exception {
		ArrayList<MzReview> mzList = mService.selectAllMzReview(shopNo);
	
		response.setContentType("application/json"); // json 객체로 전달시 파라미터 값 다름("text/html;charset=utf-8")
		response.setCharacterEncoding("utf-8"); // 데이터 한글 변환 위해 필수 작성!!
		
		Gson gson = new Gson();
		gson.toJson(mzList, response.getWriter());
	}
}
