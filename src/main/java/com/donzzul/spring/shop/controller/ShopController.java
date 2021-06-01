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
import com.donzzul.spring.mzreview.domain.MzReview;
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
	private MzReviewService mService;
	
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
		System.out.println("로케이션 값" + selectedLocation); // 확인용
		
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
	public void searchShopMapKey(@RequestParam("searchKeyword") String searchKeyword, HttpServletResponse response, @RequestParam(value="page", required=false) Integer page) throws Exception {
		
		int currentPage = (page != null) ? page : 1; 
		int listCount = sService.selectKeyListCount(searchKeyword); 
		PageInfo pi = MapPagination.getMapPageInfo(currentPage, listCount); 
		
		ArrayList<Shop> mapList = sService.searchMapKeyword(pi, searchKeyword);
		ArrayList<Shop> mapMarkers = sService.searchMapKeyword(searchKeyword);
		
		System.out.println("테스트 확인 :" + mapList);
		System.out.println("로케이션 테스트 확인 :" + mapList.get(0).getShopAddr());

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
	public ModelAndView searchTheme(ModelAndView mv, @RequestParam("themeNo") int themeNo, @RequestParam(value="page", required=false) Integer page) {
		// 파라미터 - 메뉴 클릭시 넘버
		System.out.println(themeNo); // 확인용
		
		String themeWord = "";	
		
		if(themeNo == 1) {
			ArrayList<Integer> sRank = drService.selectReviewRanking(); // 리뷰 랭킹 가져오기 
//			System.out.println("sRank 확인 : " + sRank.toString());
			
			ArrayList<Shop> rankList = sService.selectShopRank(sRank); // 가게번호 이용하여 가게 정보 가져오기 
//			System.out.println("rankList 확인 : " + rankList.toString());
			
			mv.addObject("rankList", rankList);
			mv.addObject("themeNo", themeNo);
			mv.setViewName("shop/ShopSearchResult");
		} else if(themeNo == 3) {
			ArrayList<Shop> newSList = sService.selectNewShop();
			System.out.println("신규가게 : " + newSList); // 확인용
			
			mv.addObject("newSList", newSList);
			mv.addObject("themeNo", themeNo);
			mv.setViewName("shop/ShopSearchResult");
		} else {
			if(themeNo == 2) {
				themeWord = "천안";
			}else if (themeNo == 4) {
				themeWord = "파스타";
			}else if (themeNo == 5) {
				themeWord = "중식";
			}else if (themeNo == 6) {
				themeWord = "명동";
			}else if (themeNo == 7) {
				themeWord = "버거";
			}else if (themeNo == 8) {
				themeWord = "제주";
			}else if (themeNo == 9) {
				themeWord = "카페";
			}else if (themeNo == 10) {
				themeWord = "피자";
			}else if (themeNo == 11) {
				themeWord = "떡볶이";
			}else if (themeNo == 12) {
				themeWord = "초밥";
			}
				System.out.println(themeWord); // 확인용
				
				HashMap<String, String> selectedtheme = new HashMap<String, String>();
				selectedtheme.put("themeWord", themeWord);
				
				int currentPage = (page != null) ? page : 1;
				int listCount = sService.selectShopThemeCount(selectedtheme); 
				PageInfo pi = Pagination.getPageInfo(currentPage, listCount); 
				ArrayList<Shop> themeList = sService.selectShopTheme(pi, selectedtheme);
				System.out.println("테마리스트 : " + themeList);
				
				mv.addObject("pi", pi);
				mv.addObject("themeNo", themeNo);
				mv.addObject("themeList", themeList);
				mv.setViewName("shop/ShopSearchResult");
		}
		
		return mv;
	}
	
	
	//D 가게검색 - 키워드 
	@RequestMapping(value="searchShop.dz", method=RequestMethod.GET)
	public ModelAndView searchShop(ModelAndView mv, @RequestParam("searchKeyword") String searchKeyword, @RequestParam(value="page", required=false)Integer page) {
		// 파라미터 - 유저 입력값
		
		HashMap<String, String> searchedKey = new HashMap<String, String>();
		searchedKey.put("searchKeyword", searchKeyword);
		
		int currentPage = (page != null) ? page : 1; 
		int listCount = sService.searchShopCount(searchedKey); 
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount); 
		ArrayList<Shop> sList = sService.searchShop(pi, searchedKey);
		
		mv.addObject("pi", pi);
		mv.addObject("sList", sList);
		mv.setViewName("shop/ShopSearchResult");
		
		return mv;
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
		ArrayList<DreamReview> rList = drService.selectDMReviewAll(shopNo);
		System.out.println("전체 후기 : " + rList.toString());
		// 감사 후기 가져오기
		ArrayList<DreamReview> drList = drService.selectAllDreamReview(shopNo);
		System.out.println("감사후기 : " + drList.toString());
		// 맛집 후기 가져오기
		ArrayList<MzReview> mzList = mService.selectAllMzReview(shopNo);
		System.out.println("맛집후기 : " + mzList.toString());
		
		
		mv.addObject("shop", shop);
		mv.addObject("mainMenu", mainMenu);
		mv.addObject("mPhoto", mPhoto);
		mv.addObject("rList", rList);
		mv.addObject("drList", drList); // 확인용
		mv.setViewName("shop/ShopDetail");
		
		return mv;
	}
	
}