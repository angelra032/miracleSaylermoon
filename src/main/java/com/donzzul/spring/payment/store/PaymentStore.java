package com.donzzul.spring.payment.store;

import java.util.ArrayList;
import java.util.HashMap;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.payment.domain.Don;
import com.donzzul.spring.shop.domain.MainMenu;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.user.domain.User;

public interface PaymentStore {

	// 결제, 돈쭐내역 저장, 출력, 룰렛포인트 적립, 
	// 리뷰포인트 적립, 포인트 사용, 포인트 조회
	
	public int updatePoint(HashMap<String, Object> donPoint); // 돈쭐 포인트 사용
	public String selectMyPoint(User user); // 포인트 조회(string인가? user인가)
	public int updateRoulettePoint(HashMap<String, Object> hash); // 룰렛포인트 적립
	public int updateReviewPoint(User user);// 리뷰포인트 적립
	// 결제
	public int insertDonList(Don don); // 돈쭐내역 업데이트
	public ArrayList<Don> selectDonList(int userNo, PageInfo pi); // 돈쭐내역 출력
	public ArrayList<Don> selectDonListThree(int userNo); // 돈쭐내역 3개
	
	//////// +
	public ArrayList<MainMenu> selectShopMenu(int shopNo); // 가게 메뉴 가져오기
	public Shop selectShop(Shop shop); // 가게 출력
	
	public Shop selectMyShop(int userNo); // 사업자 마이페이지 가게 불러오기
	
	
	public ArrayList<Don> selectAllDonList(HashMap<String, String> dateMap); // 어드민
	//public ArrayList<DonCount> selectAllDonListSum(HashMap<String, String> dateMap);

	
	// 포인트 업데이트 시 사용
	public Don selectDonPrice(int donNo);
	public int updateDonSavePoint(Don don);
	// 포인트 업데이트 시 사용_2(%계산)
	public int updateDonSavePoint(int donNo);
	
	// 사업자 포인트 환급신청
	public int updateShopPointYN(int shopNo);
	
	
}
