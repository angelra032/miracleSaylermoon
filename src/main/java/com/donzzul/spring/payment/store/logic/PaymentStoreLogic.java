package com.donzzul.spring.payment.store.logic;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.payment.domain.Don;
import com.donzzul.spring.payment.domain.DonCount;
import com.donzzul.spring.payment.store.PaymentStore;
import com.donzzul.spring.shop.domain.MainMenu;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.user.domain.User;

@Repository
public class PaymentStoreLogic implements PaymentStore{

	@Autowired
	private SqlSessionTemplate sqlSession;

	
	//// 결제 ////
	@Override
	public int insertDonList(Don don) { // 돈쭐내역 업데이트
		return sqlSession.insert("paymentMapper.insertDonList", don);
	}
	@Override
	public int updatePoint(HashMap<String, Object> donPoint) { // 포인트 사용
		return sqlSession.update("paymentMapper.updateUserPoint", donPoint);
	}

	
	//// 돈쭐내역 출력 ////
	@Override
	public ArrayList<Don> selectDonListThree(int userNo) { // 돈쭐내역 출력(상위3개)
		return (ArrayList)sqlSession.selectList("paymentMapper.selectDonListThree", userNo);
	}
	@Override
	public ArrayList<Don> selectDonList(int userNo, PageInfo pi) { // 돈쭐내역 출력
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("paymentMapper.selectDonAllList", userNo, rowBounds);
	}
	@Override
	public int getListCount(int userNo) {
		return sqlSession.selectOne("paymentMapper.selectListCount", userNo);
	}

	

	//// 포인트 적립(룰렛,리뷰) ////
	@Override
	public int updateRoulettePoint(HashMap<String, Object> hash) { // 룰렛포인트 적립
		return sqlSession.update("paymentMapper.updateRoulettePoint", hash);
	}
	@Override
	public int updateReviewPoint(User user) { // 리뷰포인트 적립
		// TODO Auto-generated method stub
		return 0;
	}

	// 포인트 업데이트 시 사용
	@Override
	public User selectUserPoint(int userNo) {
		return sqlSession.selectOne("paymentMapper.selectUserPoint", userNo);
	}
	@Override
	public Don selectDonPrice(int donNo) {
		return sqlSession.selectOne("paymentMapper.selectDonPrice", donNo);
	}
	@Override
	public int updateDonSavePoint(Don don) {
		return sqlSession.update("paymentMapper.updateDonSavePoint", don);
	}
	// 포인트 업데이트 시 사용_2(%계산)
	@Override
	public int updateDonSavePoint(int donNo) {
		return sqlSession.update("paymentMapper.updateRealSavePoint", donNo);
	}
	// 포인트 업데이트 시 사용_3(룰렛 여부 체크 - select / update)
	@Override
	public Don selectRouletteYN(int donNo) {
		return sqlSession.selectOne("paymentMapper.selectRouletteYN", donNo);
	}
	@Override
	public int updateRouletteYN(int donNo) {
		return sqlSession.update("paymentMapper.updateRouletteYN", donNo);
	}
	
	
	
	//// 기타 ////
	@Override
	public Shop selectShop(Shop shop) { // 가게 출력(shopNo)
		return sqlSession.selectOne("paymentMapper.selectShopShopNo", shop);
	}
	@Override
	public Shop selectMyShop(int userNo) { // 사업자 내 가게(마이페이지) 가져오기(userNo)
		System.out.println("서비스"+userNo);
		return sqlSession.selectOne("paymentMapper.selectShopUserNo", userNo);
	}
	@Override
	public ArrayList<MainMenu> selectShopMenu(int shopNo) { // 가게 메뉴 출력
		ArrayList<MainMenu> mainmenu = (ArrayList)sqlSession.selectList("paymentMapper.selectShopMenu", shopNo);
		return mainmenu;
	}



	//// 사업자 포인트 환급신청 ////
	@Override
	public int updateShopPointYN(int shopNo) {
		return sqlSession.update("paymentMapper.updateShopPointYN", shopNo);
	}
	@Override
	public int updateBeforePoint(User user) {
		return sqlSession.update("paymentMapper.updateShopChangeBeforePoint", user);
	}
	
	// 관리자의 사업자 포인트 환급
	@Override
	public int updateAdminPointViewNo(int shopNo) {
		return sqlSession.update("paymentMapper.updateAdminPointChange", shopNo);
	}
	@Override
	public int updateAfterPointZero(int userNo) {
		return sqlSession.update("paymentMapper.updateAdminChangeAfterPoint", userNo);
	}
	
	
	//// 어드민 ////
	@Override
	public ArrayList<Don> selectAllDonList(HashMap<String, String> dateMap) {
		return (ArrayList) sqlSession.selectList("paymentMapper.selectDonListView", dateMap);
	}
	 @Override
	 public ArrayList<DonCount> selectAllDonListSum(HashMap<String, String> dateMap) {
	      return (ArrayList)sqlSession.selectList("paymentMapper.selectDonListSum", dateMap);
	 }
	 
	// 메인에 뿌려줄 돈쭐 총액
	@Override
	public Don selectMoneyTotal() {
		return sqlSession.selectOne("paymentMapper.selectMoneyTotal");
	}

	
	

	
	
}
