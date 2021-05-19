package com.donzzul.spring.payment.controller;

import java.io.IOException;

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
	private PaymentService service;

	// 돈쭐 결제 폼
	@RequestMapping(value = "paymentFormView.dz", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView paymentFormView(ModelAndView mv) { //, @RequestParam("shopNo") int shopNo
		
//		Shop shop = new Shop();
//		shop.setShopNo(87); //임시 데이터
//		
//		shop = service.selectShop(shop); // 샵도 뿌려줘야 don 폼에 담음..
		int shopNo = 87;
		MainMenu menu = new MainMenu();
		menu = service.selectShopMenu(shopNo);
		//System.out.println(shop.toString());
		System.out.println(menu.toString());
		if (menu != null) {
//			mv.addObject("menu", menu).addObject("shop", shop).setViewName("payment/paymentForm");
//			mv.addObject("shop", shop).setViewName("payment/paymentForm");
		} else {
			System.out.println("돈쭐 결제 폼 출력 오류 - 아마도 메뉴(가게넘버) 받아오는 게 잘못 됐음");
		}
//		mv.addObject("shop", shop).setViewName("payment/paymentForm");
		return mv;
	}
	
	// 결제하기(API로 넘어감)
	@RequestMapping(value = "kakaoPay.dz", method = RequestMethod.POST)
	public String kakaoPay() {
		
		return "payment/kakaoView";
	}

	/// 돈쭐 내역 저장 - 내역 저장(카카오페이가 완료되면)
	@RequestMapping(value = "insertDonList.dz", method = RequestMethod.GET)
	public ModelAndView insertDonList(ModelAndView mv, 
											@ModelAttribute Don don	) {
		
		// 최종 데이터(돈쭐form) 가져오기
		// 돈쭐 내역 저장하기 - // 성공하면 포인트 업데이트하기(포인트는 don에 없으니까 param으로 따로 받아오기)
		int result = service.insertDonList(don);
		if(result > 0) {
			// 카카오페이 - 돈쭐 내역 저장 - 포인트 업데이트 - 룰렛 돌리기
			mv.setViewName("");
		}else {
			System.out.println("돈줄 내역 저장(insert) 실패..");
		}
		return mv;
	}

	public String insertDonList(HttpServletRequest request, @ModelAttribute User user, Model model) {
		// 결제 후 반환정보 파라미터로
		return "";
	}

	// 돈쭐 내역 출력
	@RequestMapping(value = "printDonList.dz", method = RequestMethod.GET)
	public String printDonList(HttpServletRequest request, @ModelAttribute User user, Model model) {
		// 결제 후 반환정보 파라미터로
		return "";
	}

	/// 필요한가? 어차피 세션 출력처리까지만. 디비는 안해도 될 듯
	// 포인트 조회(MZ 마이페이지 + 포인트사용(가용포인트))
	@RequestMapping(value = "printMyPoint.dz", method = RequestMethod.GET) // post?
	public String printMyPoint(HttpServletRequest request, @ModelAttribute User user, Model model) {
		return "";
	}

	// 룰렛 페이지
	@RequestMapping(value = "rouletteView.dz", method = RequestMethod.GET)
	public String rouletteView() {
		return "payment/pointRoulette";
	}

	// 룰렛 포인트 정립
	@RequestMapping(value = "saveRoulettePoint.dz", method = RequestMethod.POST)
	public String saveRoulettePoint(HttpServletRequest request, @ModelAttribute User user, Model model) {
		String winningPoint = request.getParameter("winningPoint"); // 앞단에서 당첨된 포인트

		return "";
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

	
	
	///////////////////////////////////
	@RequestMapping(value="partnerMyPage.dz", method=RequestMethod.GET)
	public String partnerMyPage() {
		
		return "partner/partnerMyPage";
	}
	
	
}
