package com.donzzul.spring.shop.store.logic;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.mzreview.store.MzReviewStore;
import com.donzzul.spring.shop.store.ShopStore;

@Repository
public class ShopStoreLogic implements ShopStore {

//	@Autowired
//	private SqlSessionTemplate sqlSession;
	
	@Override
	public ArrayList<Shop> selectShopMap(String mapVal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Shop> searchShop(String searchKeyWord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Shop> searchShopTheme(int themeNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shop selectShopOne(int shopNo) {
		// TODO Auto-generated method stub
		return null;
	}

// 주석 풀어주세요
//	@Override
//	public ArrayList<ReviewDreamMzAll> selectReviewAll(int shopNo) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
