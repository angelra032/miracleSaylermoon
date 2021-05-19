package com.donzzul.spring.payment.store.logic;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String selectDonList(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	//////////// +
	// 가게 메뉴 가져오기

	@Override
	public MainMenu selectShopMenu(int shop) {
		MainMenu mainmenu = sqlSession.selectOne("paymentMapper.selectShopMenu", shop);
		System.out.println("스토어"+mainmenu);
		return mainmenu;
	}

	@Override
	public Shop selectShop(Shop shop) {
		return sqlSession.selectOne("paymentMapper.selectShop", shop);
	}

}
