package com.donzzul.spring.payment.store;

import com.donzzul.spring.payment.domain.Don;
import com.donzzul.spring.shop.domain.MainMenu;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.user.domain.User;

public interface PaymentStore {

	// 결제, 돈쭐내역 저장, 출력, 룰렛포인트 적립, 
	// 리뷰포인트 적립, 포인트 사용, 포인트 조회
	
	public int updatePoint(User user); // 포인트 사용
	public String selectMyPoint(User user); // 포인트 조회(string인가? user인가)
	public int updateRoulettePoint(User user); // 룰렛포인트 적립
	public int updateReviewPoint(User user);// 리뷰포인트 적립
	// 결제
	public int insertDonList(Don don); // 돈쭐내역 업데이트
	public String selectDonList(User user); // 돈쭐내역 출력
	
	//////// +
	public MainMenu selectShopMenu(int shop); // 가게 메뉴 가져오기
	public Shop selectShop(Shop shop); // 가게 출력
	
	
}
