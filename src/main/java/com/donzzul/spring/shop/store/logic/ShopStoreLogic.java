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
	public ArrayList<Shop> selectShopMap(PageInfo pi, String location) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("shopMapper.selectAllList", location, rowBounds);
	}
	
	@Override
	public ArrayList<Shop> selectShopMap(String location) {
		return (ArrayList)sqlSession.selectList("shopMapper.selectAllList", location);
	}

	@Override
	public ArrayList<Shop> searchMapKeyword(String searchKeyword) {
		return (ArrayList)sqlSession.selectList("shopMapper.selectMapKeyword", searchKeyword);
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
	public int selectListCount(String location) {
		return sqlSession.selectOne("shopMapper.selectListCount", location);
	}
	
	public int selectKeyListCount(String searchKeyword) {
		return sqlSession.selectOne("shopMapper.selectListCountKey", searchKeyword);
	}

}