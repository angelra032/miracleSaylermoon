package com.donzzul.spring.shop.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.donzzul.spring.review.domain.ReviewDreamMzAll;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.shop.service.ShopService;

@Controller
public class ShopController {

	@Autowired
	private ShopService service;
	
	//D 가게 키워드 검색
	@RequestMapping(value="searchShop.do", method=RequestMethod.GET)
	public String searchShop(@RequestParam("searchKeyword") String searchKeyword, @ModelAttribute Shop shop, Model model) {
		// 유저 입력값 받기
		ArrayList<Shop> sList = service.searchShop(searchKeyword);
		return "";
	}
	
	//D 가게 테마 검색
	@RequestMapping(value="searchTheme.do", method=RequestMethod.GET)
	public String searchTheme(@RequestParam("themeNo") int themeNo, @ModelAttribute Shop shop, Model model) {
		// 메뉴 클릭값 받기
		// 1번
		ArrayList<Shop> sList = service.searchShopTheme(themeNo);
		return "";
	}
	
	//D 가게 상세 페이지 출력
	@RequestMapping(value="shopDetail.do", method=RequestMethod.GET)
	public String shopDetail(@RequestParam("shopNo") int shopNo, @ModelAttribute Shop shop, Model model) {
		// 가게 번호 받아 출력
		Shop sInfo = service.selectShopOne(shopNo);
		// 후기 가져오기
		ArrayList<ReviewDreamMzAll> rList = service.selectReviewAll(shopNo);
		return "";
	}
}
