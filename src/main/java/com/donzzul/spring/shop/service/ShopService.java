package com.donzzul.spring.shop.service;

import java.util.ArrayList;

import com.donzzul.spring.dreamreview.domain.DreamReview;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.shop.domain.MainMenu;
import com.donzzul.spring.shop.domain.MenuPhoto;
import com.donzzul.spring.shop.domain.Shop;

public interface ShopService {
	
	public ArrayList<Shop> selectShopMap(String mapVal); // 지역별 지도 검색
	public ArrayList<Shop> searchShop(String searchKeyword); // 가게 키워드 검색 리스트 출력
	public ArrayList<Shop> searchShopTheme(int themeNo); // 가게 테마 리스트 출력
	public Shop selectShopOne(int shopNo); // 가게 상세 정보 출력
	public ArrayList<MainMenu> selectMainMenu(int shopNo); // 가게 메인 메뉴 출력
	public ArrayList<MenuPhoto> selectMenuPhoto(int shopNo); // 가게 메뉴 사진 출력
	
	// 주석 풀어주세요
//	public ArrayList<ReviewDreamMzAll> selectDmReviewAll(int shopNo); // 가게 전체 후기 가져오기 (MZ 패키지)
	
	
	
	// 후기 관리
//	public ArrayList<DreamReview> selectAllDreamReview(int shopNo); // dreamReview 오버로딩
//	public ArrayList<MzReview> selectAllReview(int shopNo); // selectAllReview 오버로딩 (사진 포함)
}
