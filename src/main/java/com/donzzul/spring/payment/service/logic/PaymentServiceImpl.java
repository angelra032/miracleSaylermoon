package com.donzzul.spring.payment.service.logic;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.payment.domain.Don;
import com.donzzul.spring.payment.service.PaymentService;
import com.donzzul.spring.payment.store.PaymentStore;
import com.donzzul.spring.shop.domain.MainMenu;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.user.domain.User;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentStore pStore;

	
	@Override
	public int usePoint(User user) { // 포인트 사용
		int result = pStore.updatePoint(user);
		return result;
	}

	@Override
	public String selectMyPoint(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveRoulettePoint(HashMap<String, Object> hash) {
		return pStore.updateRoulettePoint(hash);
	}

	@Override
	public int saveReviewPoint(User user) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	@Override
	public ArrayList<Don> selectDonList(int userNo, PageInfo pi) {
		return pStore.selectDonList(userNo, pi);
	}

	///////////////////////////////////
	

	@Override
	public int insertDonList(Don don) {
		return pStore.insertDonList(don);
	}

	@Override
	public ArrayList<MainMenu> selectShopMenu(int shopNo) {
		ArrayList<MainMenu> mainmenu = pStore.selectShopMenu(shopNo);
		return mainmenu;
	}

	@Override
	public Shop selectShop(Shop shop) {
		return pStore.selectShop(shop);
	}

	// 사업자 마이페이지 가게 불러오기
	@Override
	public Shop selectMyShop(int userNo) {
		return pStore.selectMyShop(userNo);
	}

	@Override
	public ArrayList<Don> selectDonListThree(int userNo) {
		return pStore.selectDonListThree(userNo);
	}


}
