package com.donzzul.spring;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.donzzul.spring.dreamreview.domain.DreamReview;
import com.donzzul.spring.dreamreview.service.DreamReviewService;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.mzreview.service.MzReviewService;
import com.donzzul.spring.payment.domain.Don;
import com.donzzul.spring.payment.service.PaymentService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private MzReviewService mService;
	
	@Autowired
	private DreamReviewService drService;
	
	@Autowired
	private PaymentService pService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model, ModelAndView mv) {
		// 맛집후기 세개
		ArrayList<MzReview> mList = mService.selectThreeReview();
		if(!mList.isEmpty()) {
			mv.addObject("mList", mList);
		} else {
			mv.addObject("msg", "게시글이 없습니다");
		}
		
		//감사후기 한개
		DreamReview drmReview = drService.selectOneDreamReview();
		if (drmReview != null) {
			mv.addObject("drmReview", drmReview);
		}
		
		//돈쭐총액
		Don don = pService.selectMoneyTotal();
		if (don != null) {
			mv.addObject("don", don);
		}
		mv.setViewName("/home");
		return mv;
	}
	
}
