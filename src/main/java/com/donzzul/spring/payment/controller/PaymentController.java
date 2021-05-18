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

import com.donzzul.spring.payment.service.PaymentService;
import com.donzzul.spring.user.domain.User;

@Controller
public class PaymentController {

	@Autowired
	private PaymentService service;

	// 돈쭐 결제 폼
	@RequestMapping(value = "paymentFormView.dz", method = { RequestMethod.GET, RequestMethod.POST })
	public String paymentFormView() {
		return "payment/paymentForm";
	}

	// 포인트 사용(ajax)
	@ResponseBody
	@RequestMapping(value = "usePoint.dz", method = RequestMethod.POST) // get?
	public String usePoint(@ModelAttribute User user, HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
		// 세션의 user 포인트 가져오기 || 뒤에 필요?
//		User loginUser = (User) session.getAttribute("loginUser"); // 로그인세션. 화면단에 뿌려줌?
		// 세션의 유저 포인트에 뷰에서 가져온 값을 차감(디비)하고 결과 뿌려주기(가용포인트 - 남아있는 포인트(select이냐
		// session이냐(session이면 바로 로드되는 건가? 리로드 필요없음?)
		int useP = Integer.parseInt(request.getParameter("usePoint")); // 사용할 포인트
		System.out.println(useP); // modelandview로 안하고 request로 가져와도 되나? 됨요
		User loginUser = new User();
		System.out.println(loginUser.toString());
		loginUser.setUserPoint(loginUser.getUserPoint() - useP);
		System.out.println(loginUser.toString());
		
		request.setAttribute("loginUser", loginUser);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/payment/paymentForm.jsp");
		try {
			view.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
		/*
		int result = service.usePoint(loginUser);
		if (result > 0) {
			return "success";
		} else {
			return "fail";
		}
		*/
		// 사용하고 결제폼으로 다시
	}

			/// 필요한가? 어차피 세션 출력처리까지만. 디비는 안해도 될 듯
	// 포인트 조회(MZ 마이페이지 + 포인트사용(가용포인트))
	@RequestMapping(value = "printMyPoint.dz", method = RequestMethod.GET) // post?
	public String printMyPoint(HttpServletRequest request, @ModelAttribute User user, Model model) {
		return "";
	}

	// 결제하기(API로 넘어감)
	@RequestMapping(value = "kakaoPay.dz", method = RequestMethod.POST)
	public String kakaoPay() {
		return "";
	}

	// 돈쭐 내역 저장
	@RequestMapping(value = "insertDonList.dz", method = RequestMethod.GET)
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

}
