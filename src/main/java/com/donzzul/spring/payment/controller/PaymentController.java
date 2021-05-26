package com.donzzul.spring.payment.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
import org.springframework.web.servlet.ModelAndView;

import com.donzzul.spring.payment.domain.Don;
import com.donzzul.spring.payment.service.PaymentService;
import com.donzzul.spring.shop.domain.MainMenu;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.user.domain.User;

@Controller
public class PaymentController {

	@Autowired
	private PaymentService pService;

	// 돈쭐 결제 폼
	@RequestMapping(value = "paymentFormView.dz", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView paymentFormView(ModelAndView mv, HttpSession session) { //, @RequestParam("shopNo") int shopNo
		/*
		User loginUser = (User)session.getAttribute("loginUser");
		if(loginUser == null) {
			//<script >alert("로그인 이후 사용 가능합니다!");
			
		}
		*/
		Shop shop = new Shop();
		shop.setShopNo(87); //임시 데이터
		
		shop = pService.selectShop(shop); // 샵도 뿌려줘야 don 폼에 담음..
//		int shopNo = 87;
//		MainMenu menu = new MainMenu();
		ArrayList<MainMenu> mList = pService.selectShopMenu(shop.getShopNo());
		System.out.println(shop.toString());
		System.out.println(mList.toString());
		if (mList != null) {
//			mv.addObject("menu", menu).addObject("shop", shop).setViewName("payment/paymentForm");
			mv.addObject("shop", shop).setViewName("payment/paymentForm");
			mv.addObject("mList", mList).setViewName("payment/paymentForm");
		} else {
			System.out.println("돈쭐 결제 폼 출력 오류 - 아마도 메뉴(가게넘버) 받아오는 게 잘못 됐음");
		}
//		mv.addObject("shop", shop).setViewName("payment/paymentForm");
		return mv;
	}

	 
	/// 돈쭐 내역 저장 - 내역 저장(카카오페이가 완료되면)
	@ResponseBody
	@RequestMapping(value = "insertDonList.dz", method=RequestMethod.POST)
	public HashMap<String, Object> insertDonList(HttpServletResponse response, 
								@ModelAttribute Don don,
								HttpSession session ) throws Exception {
								// 카카오페이 ajax로 넘겨준 값 / 가게이름,날짜,가격
										
		User loginUser = (User) session.getAttribute("loginUser");
		don.setUserNo(loginUser.getUserNo());
		
		// 최종 데이터(돈쭐form - ) 가져오기
		// 돈쭐 내역 저장하기 - // 성공하면 포인트 업데이트하기(포인트는 don에 없으니까 param으로 따로 받아오기)
		
		int result = pService.insertDonList(don);
		if(result > 0) {
			// 카카오페이 - 돈쭐 내역 저장 - 포인트 업데이트 - 룰렛 돌리기
			System.out.println("돈쭐내역 저장 성공!");
			
			
			// 포인트 업데이트
			HashMap<String, Object> donPoint = new HashMap<String, Object>();
			donPoint.put("donNo", don.getDonNo());
			donPoint.put("userNo", loginUser.getUserNo());
			
			int upPoint = pService.usePoint(donPoint);
			if(upPoint > 0) {
				System.out.println("포인트 업데이트 성공!");
			}else {
				System.out.println("포인트 업데이트 실패!");
			}
			return donPoint;

		}else {
			System.out.println("돈줄 내역 저장(insert) 실패..");
			return null;
		}
	}

	// 룰렛 페이지
	@RequestMapping(value = "rouletteView.dz", method = RequestMethod.GET)
	public String rouletteView(@RequestParam("donNo") int donNo, HttpSession session, Model model) {
		System.out.println(donNo);
		model.addAttribute("donNo", donNo);
		return "payment/pointRoulette";
	}

	// 룰렛 포인트 정립
	@RequestMapping(value = "saveRoulettePoint.dz", method = RequestMethod.POST)
	public String saveRoulettePoint(HttpSession session, @RequestParam("winning-point") int winPoint, @ModelAttribute User user, Model model) {
		
		// 포인트 디비에서 계산하려면
		// 포인트 컬럼 추가(일단 vo에)
		// don의 포인트랑 user의 포인트 가져가서 계산
		
		// modelattrivbute로 user 가져갈 수 ㅣ잇나?(앞단에서 오는 건가)
		User loginUser = (User)session.getAttribute("loginUser");
		
		
		System.out.println("당첨포인트-뒷단: "+winPoint); // 앞단에서 당첨된 포인트
		Don don = new Don();
		don.setSavePoint(winPoint); // 포인트 담아주기
		
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("loginUser", loginUser);
		hash.put("don", don);
		
		int result = pService.saveRoulettePoint(hash);
		if(result > 0) {
			System.out.println("룰렛 포인트 업데이트 성공!");
			return "payment/snsPhoto";
		}else {
			System.out.println("룰렛 포인트 업데이트 실패");
			return "common/errorPage";
		}

	}

	// 인증샷 페이지
	@RequestMapping(value = "snsPhotoView.dz", method = RequestMethod.GET)
	public String snsPhotoView() {
		return "payment/snsPhoto";
	}

	// 리뷰 포인트 정립(MZ 마이페이지에서)
	@RequestMapping(value = "saveReviewPoint.dz", method = RequestMethod.POST)
	public String saveReviewPoint(HttpServletRequest request, @ModelAttribute User user, Model model) {
		String winningPoint = request.getParameter("winningPoint"); // 리뷰작성 포인트

		return "";
	}

	// 환급 신청 /////////어디서? 사업자
//	@RequestMapping(value="refundsPoint.dz", method=RequestMethod.GET)
//	public String refundsPoint(HttpServletRequest request, @ModelAttribute User user, Model model) {
//		return "";
//	}
//	public int updateRefundsPoint(User user);

	
	

	
}
