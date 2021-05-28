package com.donzzul.spring.payment.service.logic;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.payment.domain.Don;
import com.donzzul.spring.payment.domain.DonCount;
import com.donzzul.spring.payment.service.PaymentService;
import com.donzzul.spring.payment.store.PaymentStore;
import com.donzzul.spring.shop.domain.MainMenu;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.user.domain.User;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentStore pStore;

	
	//// 결제 ////
	@Override
	public int insertDonList(Don don) { // 돈쭐내역 업데이트
		return pStore.insertDonList(don);
	}
	@Override
	public int usePoint(HashMap<String, Object> donPoint) { // 포인트 차감
		int result = pStore.updatePoint(donPoint);
		return result;
	}

	
	//// 돈쭐내역 출력 ////
	@Override
	public ArrayList<Don> selectDonListThree(int userNo) { // 돈쭐내역 출력(상위3개)
		return pStore.selectDonListThree(userNo);
	}
	@Override
	public ArrayList<Don> selectDonList(int userNo, PageInfo pi) { // 돈쭐내역 출력
		return pStore.selectDonList(userNo, pi);
	}

	

	//// 포인트 적립(룰렛,리뷰) ////
	@Override
	public int saveRoulettePoint(HashMap<String, Object> hash) { // 룰렛포인트 적립
		return pStore.updateRoulettePoint(hash);
	}
	@Override
	public int saveReviewPoint(User user) { // 리뷰포인트 적립
		// TODO Auto-generated method stub
		return 0;
	}

	// 포인트 업데이트 시 사용
	@Override
	public Don selectDonPrice(int donNo) {
		return pStore.selectDonPrice(donNo);
	}
	@Override
	public int updateDonSavePoint(Don don) {
		return pStore.updateDonSavePoint(don);
	}
	// 포인트 업데이트 시 사용_2(%계산)
	@Override
	public int updateDonSavePoint(int donNo) {
		return pStore.updateDonSavePoint(donNo);
	}
	// 포인트 업데이트 시 사용_3(룰렛 여부 체크 - select / update)
	@Override
	public Don selectRouletteYN(int donNo) {
		return pStore.selectRouletteYN(donNo);
	}
	@Override
	public int updateRouletteYN(int donNo) {
		return pStore.updateRouletteYN(donNo);
	}
	
	
	
	//// 기타 ////
	@Override
	public Shop selectShop(Shop shop) { // 가게 출력(shopNo)
		return pStore.selectShop(shop);
	}
	@Override
	public Shop selectMyShop(int userNo) { // 사업자 내 가게(마이페이지) 가져오기(userNo)
		return pStore.selectMyShop(userNo);
	}
	@Override
	public ArrayList<MainMenu> selectShopMenu(int shopNo) { // 가게 메뉴 출력
		ArrayList<MainMenu> mainmenu = pStore.selectShopMenu(shopNo);
		return mainmenu;
	}



	//// 사업자 포인트 환급신청 ////
	@Override
	public int applyRefundsShopPoint(int shopNo) {
		return pStore.updateShopPointYN(shopNo);
	}	
	
	
	
	//// 어드민 ////
	@Override
	public ArrayList<Don> selectAllDonList(HashMap<String, String> dateMap) { // 어드민
		return pStore.selectAllDonList(dateMap);
	}
	public ArrayList<DonCount> selectAllDonListSum(HashMap<String, String> dateMap) {
		return null;
	}


	
}
