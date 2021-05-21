package com.donzzul.spring.payment.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.payment.domain.Don;
import com.donzzul.spring.payment.service.PaymentService;
import com.donzzul.spring.payment.store.PaymentStore;
import com.donzzul.spring.shop.domain.MainMenu;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.user.domain.User;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentStore store;

	
	@Override
	public int usePoint(User user) { // 포인트 사용
		int result = store.updatePoint(user);
		return result;
	}

	@Override
	public String selectMyPoint(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveRoulettePoint(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int saveReviewPoint(User user) {
		// TODO Auto-generated method stub
		return 0;
	}
	/*
	 * @Override public int insertDonList(User user) { // TODO Auto-generated method
	 * stub return 0; }
	 */

	@Override
	public String selectDonList(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	///////////////////////////////////
	

	@Override
	public int insertDonList(Don don) {
		System.out.println("서비스"+don);
		return store.insertDonList(don);
	}

	@Override
	public ArrayList<MainMenu> selectShopMenu(int shopNo) {
		ArrayList<MainMenu> mainmenu = store.selectShopMenu(shopNo);
		return mainmenu;
	}

	@Override
	public Shop selectShop(Shop shop) {
		return store.selectShop(shop);
	}

	// 사업자 마이페이지 가게 불러오기
	@Override
	public Shop selectMyShop(int userNo) {
		System.out.println("=================");
		return store.selectMyShop(userNo);
	}


}
