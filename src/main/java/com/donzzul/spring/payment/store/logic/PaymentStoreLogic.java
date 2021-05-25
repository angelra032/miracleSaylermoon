package com.donzzul.spring.payment.store.logic;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.payment.domain.Don;
import com.donzzul.spring.payment.store.PaymentStore;
import com.donzzul.spring.shop.domain.MainMenu;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.user.domain.User;

@Repository
public class PaymentStoreLogic implements PaymentStore{

	@Autowired
	private SqlSessionTemplate sqlSession;

	
	@Override
	public int updatePoint(User user) { // 포인트 사용
		return sqlSession.update("paymentMapper.updateUserPoint", user);
	}

	@Override
	public String selectMyPoint(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateRoulettePoint(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReviewPoint(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertDonList(Don don) {
		return sqlSession.insert("paymentMapper.insertDonList", don);
	}

	@Override
	public String selectDonList(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	//////////// +
	// 가게 메뉴 가져오기

	@Override
	public ArrayList<MainMenu> selectShopMenu(int shopNo) {
		ArrayList<MainMenu> mainmenu = (ArrayList)sqlSession.selectList("paymentMapper.selectShopMenu", shopNo);
		return mainmenu;
	}

	@Override
	public Shop selectShop(Shop shop) {
		return sqlSession.selectOne("paymentMapper.selectShopShopNo", shop);
	}

	// 사업자 가게 불러오기
	@Override
	public Shop selectMyShop(int userNo) {
		System.out.println("서비스"+userNo);
		return sqlSession.selectOne("paymentMapper.selectShopUserNo", userNo);
	}

	@Override
	public ArrayList<Don> selectDonListThree(int userNo) {
		return (ArrayList)sqlSession.selectList("paymentMapper.selectDonListThree", userNo);
	}

}
