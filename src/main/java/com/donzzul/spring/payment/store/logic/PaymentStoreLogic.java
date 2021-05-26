package com.donzzul.spring.payment.store.logic;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.common.PageInfo;
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
	public int updatePoint(HashMap<String, Object> donPoint) { // 포인트 사용
		return sqlSession.update("paymentMapper.updateUserPoint", donPoint);
	}

	@Override
	public String selectMyPoint(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateRoulettePoint(HashMap<String, Object> hash) {
		return sqlSession.update("paymentMapper.updateRoulettePoint", hash);
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
	public ArrayList<Don> selectDonList(int userNo, PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("paymentMapper.selectDonAllList", userNo, rowBounds);
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

	// 돈쭐내역 출력 (3개, all)
	@Override
	public ArrayList<Don> selectDonListThree(int userNo) {
		return (ArrayList)sqlSession.selectList("paymentMapper.selectDonListThree", userNo);
	}

	
	
	@Override
	public ArrayList<Don> selectAllDonList(HashMap<String, String> dateMap) {
		return (ArrayList) sqlSession.selectList("paymentMapper.selectDonListView", dateMap);
	}


	// @Override
	// public ArrayList<DonCount> selectAllDonListSum(HashMap<String, String>
	// dateMap) {
//	      return (ArrayList)sqlSession.selectList("paymentMapper.selectDonListSum", dateMap);
	// }

	
	// 포인트 업데이트 시 사용
	@Override
	public Don selectDonPrice(int donNo) {
		return sqlSession.selectOne("paymentMapper.selectDonPrice", donNo);
	}

	@Override
	public int updateDonSavePoint(Don don) {
		return sqlSession.update("paymentMapper.updateDonSavePoint", don);
	}
	
	
}
