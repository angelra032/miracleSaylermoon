package com.donzzul.spring.shop.service;

import java.util.ArrayList;

import com.donzzul.spring.review.domain.ReviewDreamMzAll;
import com.donzzul.spring.shop.domain.Shop;

public interface ShopService {
	
	public ArrayList<Shop> searchShop(String searchKeyword); // 가게 키워드 검색 리스트 출력
	public ArrayList<Shop> searchShopTheme(int themeNo); // 가게 테마 리스트 출력
	public Shop selectShopOne(int shopNo); // 가게 상세 정보 출력
	public ArrayList<ReviewDreamMzAll> selectReviewAll(int shopNo); // 가게 전체 후기 가져오기
	public Shop findReviewPhoto(int mzReviewNo); // 가게 후기 사진 가져오기
}
