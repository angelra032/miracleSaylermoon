package com.donzzul.spring.shop.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.donzzul.spring.dreamreview.domain.DreamReview;
import com.donzzul.spring.dreamreview.service.DreamReviewService;
import com.donzzul.spring.mzreview.domain.MzReview;
//import com.donzzul.spring.mzreview.domain.ReviewDreamMzAll; // 주석을 풀어주세요....
import com.donzzul.spring.mzreview.service.MzReviewService;
import com.donzzul.spring.shop.domain.MainMenu;
import com.donzzul.spring.shop.domain.MenuPhoto;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.shop.service.ShopService;

@Controller
public class ShopController {

	@Autowired
	private ShopService sService;
	private MzReviewService mzService;
	private DreamReviewService drService;
	
	//D 지도 - 지역별 가게 검색
	@RequestMapping(value="mapSearchShop.dz", method=RequestMethod.GET)
	public String searchShopMap(@RequestParam("mapNo") int mapNo, Model model) {
		// 파라미터 - 메뉴 클릭시 넘버
		// 넘버별로 스트링값 설정해서 서비스로 넘기기
		String mapVal = null;
		if(mapNo == 1) {
			mapVal = "전체";
		}
		
		ArrayList<Shop> mapList = sService.selectShopMap(mapVal);
		return "";
	}
	
	//D 가게 키워드 검색
	@RequestMapping(value="searchShop.dz", method=RequestMethod.GET)
	public String searchShop(@RequestParam("searchKeyword") String searchKeyword, Model model) {
		// 파라미터 - 유저 입력값
		ArrayList<Shop> sList = sService.searchShop(searchKeyword);
		return "";
	}
	
	//D 가게 테마 검색
	@RequestMapping(value="searchTheme.dz", method=RequestMethod.GET)
	public String searchTheme(@RequestParam("themeNo") int themeNo, Model model) {
		// 파라미터 - 메뉴 클릭시 넘버
		// 1번
		ArrayList<Shop> sList = sService.searchShopTheme(themeNo);
		return "";
	}
	
	//D 가게 상세 페이지 출력
	@RequestMapping(value="shopDetail.dz", method=RequestMethod.GET)
	public String shopDetail(@RequestParam("shopNo") int shopNo, @ModelAttribute Shop shop, Model model) {
		// 파라미터 - 가게 번호 (쿼리스트링)
		
		// 이하 전부 해시맵?
		// 가게 상세정보 가져오기
		Shop sInfo = sService.selectShopOne(shopNo);
		// 가게 메인메뉴 가져오기
		ArrayList<MainMenu> menu = sService.selectMainMenu(shopNo);
		// 메뉴 사진 가져오기 
		ArrayList<MenuPhoto> mPhoto = sService.selectMenuPhoto(shopNo);
		// 전체 후기 가져오기
		// 주석을 풀어주세요....
//		ArrayList<ReviewDreamMzAll> rList = mzService.selectDmReviewAll(shopNo);
		return "";
	}
	
	//D 전체후기 가져오기
	@RequestMapping(value="mdReviewShop.dz", method=RequestMethod.GET)
	public String selectDmReview(@RequestParam("shopNo") String shopNo, Model model) {
		// 주석을 풀어주세요....
//		ArrayList<ReviewDreamMzAll> rList = mzService.selectDmReviewAll(shopNo);
		return "";
	}
	
//	// 각각 후기 패키지에 포함
//	//D 감사후기 가져오기
//	@RequestMapping(value="drReviewShop.dz", method=RequestMethod.GET)
//	public String selectDrReview(@RequestParam("shopNo") String shopNo, Model model) {
//		ArrayList<DreamReview> dReview = drService.selectAllDreamReview(shopNo);
//		return "";
//	}
//	
//	//D 맛집후기 가져오기
//	@RequestMapping(value="mzReviewShop.dz", method=RequestMethod.GET)
//	public String selectMzReview(@RequestParam("shopNo") String shopNo, Model model) {
//		ArrayList<MzReview> mReview = mzService.selectAllReview(shopNo);
//		return "";
//	}
}
