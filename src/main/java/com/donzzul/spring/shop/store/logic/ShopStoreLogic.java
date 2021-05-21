package com.donzzul.spring.shop.store.logic;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.shop.domain.MainMenu;
import com.donzzul.spring.shop.domain.MenuPhoto;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.shop.store.ShopStore;

@Repository
public class ShopStoreLogic implements ShopStore {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public ArrayList<Shop> selectShopMap(PageInfo pi, int mapNo) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("shopMapper.selectAllList", mapNo, rowBounds);
	}
	
	@Override
	public ArrayList<Shop> selectShopMap(int mapNo) {
//		System.out.println("store");
		return (ArrayList)sqlSession.selectList("shopMapper.selectAllList", mapNo);
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
		return sqlSession.selectOne("shopMapper.selectOne", shopNo);
	}

	@Override
	public ArrayList<MainMenu> selectMainMenu(int shopNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MenuPhoto> selectMenuPhoto(int shopNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectListCount(int mapNo) {
		return sqlSession.selectOne("shopMapper.selectListCount", mapNo);
	}
	
	public int selectKeyListCount(String searchKeyword) {
		return sqlSession.selectOne("shopMapper.selectListCountKey", searchKeyword);
	}

	@Override
	public ArrayList<Shop> searchMapKeyword(String searchKeyword) {
		return (ArrayList)sqlSession.selectList("shopMapper.selectMapKeyword", searchKeyword);
	}

}
