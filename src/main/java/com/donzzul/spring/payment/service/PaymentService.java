package com.donzzul.spring.payment.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.payment.domain.Don;
import com.donzzul.spring.payment.domain.DonCount;
import com.donzzul.spring.shop.domain.MainMenu;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.user.domain.User;

public interface PaymentService {

	// 결제, 돈쭐내역 저장, 출력
	// 룰렛포인트 적립, 리뷰포인트 적립, 포인트 사용,
	
	
	//// 결제 ////
	public int insertDonList(Don don); // 돈쭐내역 업데이트
	public int usePoint(HashMap<String, Object> donPoint); // 돈쭐 포인트 차감

	
	//// 돈쭐내역 출력 - mz ////
	public ArrayList<Don> selectDonListThree(int userNo); // 돈쭐내역 출력(상위3개)
	public ArrayList<Don> selectDonList(int userNo, PageInfo pi); // 돈쭐내역 출력
	//리스트카운트
	public int getListCount(int userNo);
	
	//// 포인트 적립(룰렛,리뷰) ////
	public User selectUserPoint(int userNo); // 포인트 조회(마이페이지)
	public int saveRoulettePoint(HashMap<String, Object> hash); // 룰렛포인트 적립
	public int saveReviewPoint(User user);// 리뷰포인트 적립

	// 포인트 업데이트 시 사용
	public Don selectDonPrice(int donNo);
	public int updateDonSavePoint(Don don);
	// 포인트 업데이트 시 사용_2(%계산)
	public int updateDonSavePoint(int donNo);
	// 포인트 업데이트 시 사용_3(룰렛 여부 체크 - select / update)
	public Don selectRouletteYN(int donNo);
	public int updateRouletteYN(int donNo);

	
	//// 기타 ////
	public Shop selectShop(Shop shop); // 가게 출력(shopNo)
	public Shop selectMyShop(int userNo); // 사업자 내 가게 가져오기(userNo)
	public ArrayList<MainMenu> selectShopMenu(int shopNo); // 가게 메뉴 출력
	

	//// 사업자 포인트 환급신청 ////
	public int applyRefundsShopPoint(int shopNo, User user);
	public int updateBeforePoint(User user); // shop 포인트를 user 포인트로 옮겨 환급할때 관리자가 금액확인
	
	// 관리자의 사업자 포인트 환급
	public int updateAdminPointViewNo(int shopNo, int userNo); // 환급버튼
	public int updateAfterPointZero(int userNo); // 클릭 후 확인되었던 금액 0으로 바꿈

	
	//// 어드민 ////
	public ArrayList<Don> selectAllDonList(HashMap<String, String> dateMap); // 어드민
	public ArrayList<DonCount> selectAllDonListSum(HashMap<String, String> dateMap);
	public Don selectMoneyTotal();
	
	

	
	
}
