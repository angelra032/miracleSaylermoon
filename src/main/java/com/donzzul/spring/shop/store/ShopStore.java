package com.donzzul.spring.shop.store;

import java.util.ArrayList;

import com.donzzul.spring.shop.domain.Shop;

public interface ShopStore {

	public ArrayList<Shop> selectShopAll(); // 가게 전체 리스트 출력
	public ArrayList<Shop> selectShopTheme(int themeNo); // 가게 테마 리스트 출력
	public Shop selectShopOne(String userId, String userType); // 가게 상세 정보 출력
	
//	public ArrayList<Review> selectReviewAll(int shopNo); // 가게 전체 후기 가져오기
	public Shop findReviewPhoto(int mzReviewNo); // 가게 후기 사진 가져오기
}
