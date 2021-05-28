package com.donzzul.spring.shop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

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
import com.donzzul.spring.mzreview.domain.ReviewDreamMzAll;
import com.donzzul.spring.mzreview.service.MzReviewService;
import com.donzzul.spring.shop.domain.MainMenu;
import com.donzzul.spring.shop.domain.MenuPhoto;
import com.donzzul.spring.shop.domain.MapPagination;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.shop.service.ShopService;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

@Controller
public class ShopController {

	@Autowired
	private ShopService sService;
	
	@Autowired
	private MzReviewService mzService;
	
	@Autowired
	private DreamReviewService drService;
	
	//D 지도 - 지역별 화면단 출력 +++
	@RequestMapping(value="mapSearchList.dz", method=RequestMethod.GET)
	public String mapSearchList() {
		return "map/MapList";
	}
	
	//D 지도 - 지역별 가게 검색
	@RequestMapping(value="mapSearchShop.dz", method=RequestMethod.GET)
	public ModelAndView searchShopMap(ModelAndView mv, @RequestParam("location") String location, @RequestParam(value="page", required=false) Integer page) {
		String qLocation = "";	
		switch(location) {
			case "All" : 
				qLocation = "전체";
				break;
			case "Seoul" : 
				qLocation = "서울";
				break;
			case "Busan" : 
				qLocation = "부산";
				break;
			case "Gwangju" : 
				qLocation = "광주";
				break;
			case "Daegu" : 
				qLocation = "대구";
				break;
			case "Daejeon" : 
				qLocation = "대전";
				break;
			case "Sejong" : 
				qLocation = "세종";
				break;
			case "Ulsan" : 
				qLocation = "울산";
				break;
			case "Incheon" : 
				qLocation = "인천";
				break;
			case "Jeju" : 
				qLocation = "제주";
				break;
			case "Gangwon" : 
				qLocation = "강원";
				break;
			case "Gyeonggi" : 
				qLocation = "경기";
				break;
			case "SouthGyeongsang" : 
				qLocation = "경남";
				break;
			case "NorthGyeongsang" : 
				qLocation = "경북";
				break;
			case "SouthJeolla" : 
				qLocation = "전남";
				break;
			case "NorthJeolla" : 
				qLocation = "전북";
				break;
			case "SouthChungCheong" : 
				qLocation = "충남";
				break;
			case "NorthChungCheong" : 
				qLocation = "충북";
				break;
			default:
				qLocation = "서울";
				break;
		}
		System.out.println(qLocation);
		
		HashMap<String, String> selectedLocation = new HashMap<String, String>();
		selectedLocation.put("location", qLocation);
		System.out.println("로케이션 값" + selectedLocation);
		
		// PageInfo 만들기 위해 필요한 데이터
		int currentPage = (page != null) ? page : 1; // 삼항연산자
		int listCount = sService.selectListCount(selectedLocation); // 전체 게시글 갯수
		PageInfo pi = MapPagination.getMapPageInfo(currentPage, listCount); // 페이징에 필요한 값을 구하기 위한 메소드

		ArrayList<Shop> mapList = sService.selectShopMap(pi, selectedLocation);
		ArrayList<Shop> mapMarkers = sService.selectShopMap(selectedLocation);
		
		mv.addObject("pi",	pi);
		mv.addObject("mList", mapList);
		mv.addObject("mapMarkers", mapMarkers);
		mv.addObject("location", location);
		mv.addObject("center", qLocation);
		mv.setViewName("map/MapDetail");
		System.out.println(location);
		return mv;
	}
	
	//D 지도 - 지역별 가게 키워드 검색
	@RequestMapping(value="mapSearchKey.dz", method=RequestMethod.GET)
	public void searchShopMapKey(@RequestParam("searchKeyword") String searchKeyword,  HttpServletResponse response, @RequestParam(value="page", required=false) Integer page) throws Exception {
		
		int currentPage = (page != null) ? page : 1; 
		int listCount = sService.selectKeyListCount(searchKeyword); 
		PageInfo pi = MapPagination.getMapPageInfo(currentPage, listCount); 
		
		ArrayList<Shop> mapList = sService.searchMapKeyword(pi, searchKeyword);
		ArrayList<Shop> mapMarkers = sService.searchMapKeyword(searchKeyword);
		System.out.println("테스트 확인 :" + mapList);
		
		response.setContentType("application/json"); // json 객체로 전달시 파라미터 값 다름("text/html;charset=utf-8")
		response.setCharacterEncoding("utf-8"); // 데이터 한글 변환 위해 필수 작성!!
		
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("pi", pi);
		hashMap.put("mList", mapList);
		hashMap.put("mapMarkers", mapMarkers);
		hashMap.put("center", mapList.get(0).getShopAddr());
		hashMap.put("searchKeyword", searchKeyword);
		Gson gson = new Gson();
		gson.toJson(hashMap, response.getWriter());
	}
	
	//D 가게검색 - 화면 출력 +++
	@RequestMapping(value="searchShopView.dz", method=RequestMethod.GET)
	public String searchShopView() {
		return "shop/ShopSearchList";
	}
	
	//D 가게검색 - 테마
	@RequestMapping(value="searchTheme.dz", method=RequestMethod.GET)
	public ModelAndView searchTheme(ModelAndView mv, @RequestParam("themeNo") int themeNo, HttpServletResponse response, @RequestParam(value="page", required=false) Integer page) {
		// 파라미터 - 메뉴 클릭시 넘버
		System.out.println(themeNo); // 확인용
		
		String themeWord = "";	
		
		int currentPage = (page != null) ? page : 1; 
		int listCount = sService.selectShopThemeCount(themeWord); 
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount); 
		
		switch(themeNo) {
			case 1 : 
				ArrayList<ReviewDreamMzAll> sRank = mzService.selectReviewRanking();
				System.out.println("테스트 shop ArrayList " + sRank);
				System.out.println("shopNo" + sRank.toString());
				break;
			case 2 : 
				themeWord = "천안";
				
				ArrayList<Shop> sList = sService.selectShopTheme(pi, themeWord);
				
				mv.addObject("pi", pi);
				mv.addObject("sList", sList);
				
				break;
			case 3 : 
				themeWord = "신규";
				break;
			case 4 : 
				themeWord = "파스타";
				break;
			case 5 : 
				themeWord = "중식";
				break;
			case 6 : 
				themeWord = "명동";
				break;
			case 7 : 
				themeWord = "버거";
				break;
			case 8 : 
				themeWord = "제주";
				break;
			case 9 : 
				themeWord = "카페";
				break;
			case 10 : 
				themeWord = "피자";
				break;
			case 11 : 
				themeWord = "떡볶이";
				break;
			case 12 : 
				themeWord = "초밥";
				break;
			default:
				themeWord = "서울";
				break;
		}
		// 
		System.out.println(themeWord); // 확인용
		
		return mv;
		
//		
//		response.setContentType("application/json");
//		response.setCharacterEncoding("utf-8");
//		
//		Gson gson = new Gson();
//		gson.toJson(sList, response.getWriter());
	}
	
	
	//D 가게검색 - 키워드 
	@RequestMapping(value="searchShop.dz", method=RequestMethod.GET)
	public String searchShop(@RequestParam("searchKeyword") String searchKeyword, Model model) {
		// 파라미터 - 유저 입력값
		ArrayList<Shop> sList = sService.searchShop(searchKeyword);
		return "";
	}
	
	//D 가게 상세 페이지 출력
	@ResponseBody
	@RequestMapping(value="shopDetail.dz", method=RequestMethod.GET)
	public ModelAndView shopDetail(ModelAndView mv, @RequestParam("shopNo") int shopNo) {
		// 파라미터 - 가게 번호 (쿼리스트링)
		// 가게 상세정보 가져오기
		Shop shop = sService.selectShopOne(shopNo);
		// 가게 메인메뉴 가져오기
		ArrayList<MainMenu> mainMenu = sService.selectMainMenu(shopNo);
		// 메뉴 사진 가져오기 
		ArrayList<MenuPhoto> mPhoto = sService.selectMenuPhoto(shopNo);
		
		// 전체 후기 가져오기
		ArrayList<ReviewDreamMzAll> rList = mzService.selectDmReviewAll(shopNo);
		
		// 감사 후기 가져오기
		ArrayList<DreamReview> drList = drService.selectAllDreamReview(shopNo);
		System.out.println(drList.toString());
		mv.addObject("shop", shop);
		mv.addObject("mainMenu", mainMenu);
		mv.addObject("mPhoto", mPhoto);
//		mv.addObject("rList", rList);
		mv.addObject("drList", drList); //
		mv.setViewName("shop/ShopDetail");
		
		return mv;
	}
	
	//D 전체후기 가져오기
	@RequestMapping(value="mdReviewShop.dz", method=RequestMethod.GET)
	public String selectDmReview(@RequestParam("shopNo") int shopNo) {
		// 주석을 풀어주세요....
		ArrayList<ReviewDreamMzAll> rList = mzService.selectDmReviewAll(shopNo);
		return "";
	}
	
	
	
	// Json
//	public void searchShopMapJson(@RequestParam("mapNo") int mapNo, @RequestParam(value="page", required=false) Integer page) {
//		int currentPage = (page != null) ? page : 1; // 삼항연산자
//		int listCount = sService.selectListCount(mapNo); // 전체 게시글 갯수
//		System.out.println(page);
//		PageInfo pi = Pagination.getPageInfo(currentPage, listCount); // 페이징에 필요한 값을 구하기 위한 메소드
//		ArrayList<Shop> mapList = sService.selectShopMap(pi, mapNo);
//		HashMap<String, Object> hashMap = new HashMap<String, Object>();
//		hashMap.put("pi", pi);
//		hashMap.put("mList", mapList);
//		Gson gson = new Gson();
//		gson.toJson(hashMap, response.getWriter());
//	}
	
//	// 각각 후기 패키지에 포함 xxxxxx
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