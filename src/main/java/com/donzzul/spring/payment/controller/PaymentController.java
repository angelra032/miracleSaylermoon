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
	public ModelAndView paymentFormView(@ModelAttribute Shop shop, ModelAndView mv, HttpSession session) { //, @RequestParam("shopNo") int shopNo
		/*
		User loginUser = (User)session.getAttribute("loginUser");
		if(loginUser == null) {
			//<script >alert("로그인 이후 사용 가능합니다!");
			
		}
		*/
		ArrayList<MainMenu> mList = pService.selectShopMenu(shop.getShopNo());
		System.out.println(shop.toString());
		System.out.println(mList.toString());
		if (mList != null) {
//			mv.addObject("menu", menu).addObject("shop", shop).setViewName("payment/paymentForm");
			mv.addObject("shop", shop).setViewName("payment/paymentForm");
			mv.addObject("mList", mList).setViewName("payment/paymentForm");
		} else {
			mv.addObject("msg", "돈쭐 가능한 메뉴가 없습니다.").setViewName("payment/paymentForm");
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
			donPoint.put("donPrice", don.getDonPrice());
			donPoint.put("shopName", don.getShopName());
			
			int upPoint = pService.usePoint(donPoint);
			if(upPoint > 0) {
				System.out.println("포인트 업데이트 성공!");
			}else {
				System.out.println("포인트 업데이트 실패!");
			}
			System.out.println("돈쭐 date(룰렛으로 보낼) : "+donPoint.toString());
			return donPoint;

		}else {
			System.out.println("돈줄 내역 저장(insert) 실패..");
			return null;
		}
	}

	// 룰렛 페이지
	@RequestMapping(value = "rouletteView.dz", method = RequestMethod.GET)
	public String rouletteView(@RequestParam("donNo") int donNo, 
								@RequestParam("donPrice") int donPrice, 
								@RequestParam("shopName") String shopName, HttpSession session, Model model) {
		
		//System.out.println(userNo); 
		System.out.println("룰렛페이지로 보내는 donNo"+donNo);
		System.out.println(donPrice);
		System.out.println(shopName); 
		
		// ROULETTE YN(사용가능 여부) 체크해서 화면단에서 c:if로 처리
		// Y면 돌리고 N이면 못 돌리게
		// 돌리고 N으로 바꿔주기
		Don rouletteYN = pService.selectRouletteYN(donNo);
		if(rouletteYN != null) {
			System.out.println("룰렛 사용가능 여부 뿌려줌 : "+rouletteYN.toString());
			model.addAttribute("donNo", rouletteYN.getDonNo());
			model.addAttribute("donPrice", rouletteYN.getDonPrice());
			model.addAttribute("shopName", rouletteYN.getShopName());
			model.addAttribute("rouletteYN", rouletteYN.getRouletteYN());
			System.out.println("여기까지 됨(위랑 값 똑같나 맞춰봐)" + rouletteYN.toString());
			return "payment/pointRoulette";
		}else {
			System.out.println("룰렛 사용가능 여부 조회 실패!");
			model.addAttribute("msg", "룰렛 사용가능 여부 조회 실패!");
			return "common/errorPage";
		}
	}

	// 룰렛 포인트 정립
	@RequestMapping(value = "saveRoulettePoint.dz", method=RequestMethod.POST)
	public String saveRoulettePoint(HttpSession session, Model model,
									@ModelAttribute Don don
									/*@RequestParam("shopName") String shopName, 
									@RequestParam("donPrice") int donPrice*/
										) {
		// 포인트 디비에서 계산하려면
		// 포인트 컬럼 추가(일단 vo에)
		// don의 포인트랑 user의 포인트 가져가서 계산
		
		User loginUser = (User)session.getAttribute("loginUser");
		System.out.println("적립컨트롤"+don.getDonNo());
		System.out.println("당첨포인트-뒷단:"+don.getSavePoint()); // 앞단에서 당첨된 포인트
		System.out.println("샵네임"+don.getShopName());
		System.out.println("가격"+don.getDonPrice());
		System.out.println("담아준 돈 객체" + don.toString());
		
		// savePoint 널일 때
		if(don.getSavePoint() == 0 ) {
			System.out.println("savePoint null");
			model.addAttribute("msg", "당첨내역이 없습니다.");
			//
			return "common/errorPage";
		}
		
		double savePoint = (don.getSavePoint() * 0.01);
		don.setSavePoint(savePoint);
		System.out.println("double로 계산" + don.toString());
		
		// 돈쭐 % update
		int updateDon = pService.updateDonSavePoint(don);
		if(updateDon > 0) {
			System.out.println("돈쭐 savePoint update"+don.toString());
			
			// % 포인트 계산 update 
			int updateSavePoint = pService.updateDonSavePoint(don.getDonNo());
			
			if(updateSavePoint > 0) {
				// user point 업데이트..
				HashMap<String, Object> roulettePoint = new HashMap<String, Object>();
				roulettePoint.put("userNo", loginUser.getUserNo());
				roulettePoint.put("donNo", don.getDonNo());
				
				int result = pService.saveRoulettePoint(roulettePoint);
				if(result > 0) {
					System.out.println("룰렛 포인트 업데이트 성공!");
					// 룰렛 사용가능 여부 N으로 update
					int updateRouletteYN = pService.updateRouletteYN(don.getDonNo());
					if(updateRouletteYN > 0) {
						System.out.println("룰렛 사용가능 여부 업데이트!");
						model.addAttribute("donNo", don.getDonNo());
						model.addAttribute("shopName", don.getShopName());
						System.out.println(don.toString());
						return "redirect:snsPhotoView.dz";
					}else {
						System.out.println("룰렛 사용가능 여부 실패");
						return "common/errorPage";
					}
					
				}else {
					System.out.println("룰렛 포인트 업데이트 실패");
					return "common/errorPage";
				}
			}else {
				System.out.println("% Real 포인트 계산 update 실패");
				return "common/errorPage";
			}
		}else {
			System.out.println("% 포인트 update 실패");
			return "common/errorPage";
		}
		
	}

	// 인증샷 페이지
	@RequestMapping(value = "snsPhotoView.dz", method = RequestMethod.GET)
	public String snsPhotoView(HttpSession session, Model model, @ModelAttribute Don don) {
		User loginUser = (User)session.getAttribute("loginUser");
		// shopName도 출력해서 인증샷 바뀌도록
		System.out.println(don.toString());
		model.addAttribute("shopName", don.getShopName());
		
		return "payment/snsPhoto";
	}

	// 리뷰 포인트 정립(MZ 마이페이지에서)
	@RequestMapping(value = "saveReviewPoint.dz", method = RequestMethod.POST)
	public String saveReviewPoint(HttpServletRequest request, @ModelAttribute User user, Model model) {
		String winningPoint = request.getParameter("winningPoint"); // 리뷰작성 포인트

		return "";
	}

	

	
}
