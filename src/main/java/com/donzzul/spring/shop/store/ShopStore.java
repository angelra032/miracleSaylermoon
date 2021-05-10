package com.donzzul.spring.shop.store;

import java.util.ArrayList;

import com.donzzul.spring.dreamreview.domain.DreamReview;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.shop.domain.Shop;

public interface ShopStore {

	public ArrayList<Shop> selectShopMap(String mapVal); // 지역별 지도 검색
	public ArrayList<Shop> searchShop(String searchKeyWord); // 가게 전체 리스트 출력
	public ArrayList<Shop> searchShopTheme(int themeNo); // 가게 테마 리스트 출력
	public Shop selectShopOne(int shopNo); // 가게 상세 정보 출력

	// 주석 풀어주세요
//	public ArrayList<ReviewDreamMzAll> selectReviewAll(int shopNo); // 가게 전체 후기 가져오기 (MZ 패키지)
	
	
	
	// 후기 관리
//	public ArrayList<DreamReview> selectAllDreamReview(int shopNo); // dreamReview 오버로딩
//	public ArrayList<MzReview> selectAllReview(int shopNo); // selectAllReview 오버로딩 (사진 포함)
}
