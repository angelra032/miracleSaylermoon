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
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentStore pStore;

	@Override
	public int usePoint(HashMap<String, Object> donPoint) { // 포인트 사용
		int result = pStore.updatePoint(donPoint);
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

	
	
	@Override
	public ArrayList<Don> selectAllDonList(HashMap<String, String> dateMap) {
		return pStore.selectAllDonList(dateMap);
	}


//   @Override
//   public ArrayList<DonCount> selectAllDonListSum(HashMap<String, String> dateMap) {
//	      return pStore.selectAllDonListSum(dateMap);
//   }

	
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
	
	
	
	// 사업자 포인트 환급신청
	@Override
	public int applyRefundsShopPoint(int shopNo) {
		return pStore.updateShopPointYN(shopNo);
	}

	

}
