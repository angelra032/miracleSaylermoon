package com.donzzul.spring.shop.store.logic;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.shop.domain.MainMenu;
import com.donzzul.spring.shop.domain.MenuPhoto;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.shop.store.ShopStore;

@Repository
public class ShopStoreLogic implements ShopStore {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public ArrayList<Shop> selectShopMap(PageInfo pi, HashMap<String, String> selectedLocation) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("shopMapper.selectAllList", selectedLocation, rowBounds);
	}
	
	@Override
	public ArrayList<Shop> selectShopMap(HashMap<String, String> selectedLocation) {
		return (ArrayList)sqlSession.selectList("shopMapper.selectAllList", selectedLocation);
	}

	@Override
	public ArrayList<Shop> searchMapKeyword(PageInfo pi, String searchKeyword) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("shopMapper.selectMapKeyword", searchKeyword, rowBounds);
	}
	
	@Override
	public ArrayList<Shop> searchMapKeyword(String searchKeyword) {
		return (ArrayList)sqlSession.selectList("shopMapper.selectMapKeyword", searchKeyword);
	}
	
	@Override
	public ArrayList<Shop> selectShopTheme(PageInfo pi, HashMap<String, String> selectedtheme) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("shopMapper.selectShopTheme", selectedtheme, rowBounds);
	}

	@Override
	public Shop selectShopRank(int shopNo) {
		return sqlSession.selectOne("shopMapper.selectShopRank", shopNo);
	}
	
	@Override
	public ArrayList<Shop> selectNewShop() {
		return (ArrayList)sqlSession.selectList("shopMapper.selectNewShop");
	}

	@Override
	public ArrayList<Shop> searchShop(PageInfo pi, HashMap<String, String> searchedKey) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return (ArrayList)sqlSession.selectList("shopMapper.selectShopKey", searchedKey, rowBounds);
	}

	@Override
	public Shop selectShopOne(int shopNo) {
		return sqlSession.selectOne("shopMapper.selectOne", shopNo);
	}

	@Override
	public ArrayList<MainMenu> selectMainMenu(int shopNo) {
		 return (ArrayList)sqlSession.selectList("shopMapper.selectShopMenu", shopNo);
	}

	@Override
	public ArrayList<MenuPhoto> selectMenuPhoto(int shopNo) {
		return (ArrayList)sqlSession.selectList("shopMapper.selectMenuPhoto", shopNo);
	}

	@Override
	public int selectListCount(HashMap<String, String> selectedLocation) {
		return sqlSession.selectOne("shopMapper.selectListCount", selectedLocation);
	}
	
	@Override
	public int selectKeyListCount(String searchKeyword) {
		return sqlSession.selectOne("shopMapper.selectMapCountKey", searchKeyword);
	}
	
	@Override
	public int selectShopThemeCount(HashMap<String, String> selectedtheme) {
		return sqlSession.selectOne("shopMapper.selectThemeCount", selectedtheme);
	}
	
	@Override
	public int searchShopCount(HashMap<String, String> searchedKey) {
		return sqlSession.selectOne("shopMapper.selectShopCountKey", searchedKey);
	}
	
    @Override
    public ArrayList<Shop> selectAllShopListDESC() {
       return (ArrayList)sqlSession.selectList("shopMapper.selectAllShopDESC");
    }

    @Override
    public ArrayList<Shop> selectAllShopListASC() {
       return (ArrayList)sqlSession.selectList("shopMapper.selectAllShopASC");
    }
    
    @Override
    public int getListCount() {
       return sqlSession.selectOne("shopMapper.selectAdminPageList");
    }

    @Override
    public ArrayList<Shop> selectAllShopList(PageInfo pi) {
       int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
       RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
       return (ArrayList)sqlSession.selectList("shopMapper.selectAllShopDESC", null, rowBounds);
    }

    @Override
    public ArrayList<Shop> selectAllShopListThree() {
       return (ArrayList)sqlSession.selectList("shopMapper.selectThreeShop");
    }
    
    // 사업자파트너
	@Override
	public int insertPartnerShop(Shop shop) {
		return sqlSession.insert("shopMapper.insertPartnerShop", shop);
	}

	@Override
	public int updatePartnerShop(Shop shop) {
		return sqlSession.update("shopMapper.updatePartnerShop", shop);
	}

	@Override
	public Shop selectShopOneUserNo(int userNo) {
		return sqlSession.selectOne("shopMapper.selectOneUserNo", userNo);
	}
	
	// 관리자 - 사업자
	@Override
	public int updatePartnerShopShow(int shopNo) {
		return sqlSession.update("shopMapper.partnerUpdateShow", shopNo);
	}

	// 파트너 - 가게 수정(다중파일 업로드)
	@Override
	public int insertMenuPhoto(MenuPhoto menuPhoto) {
		return sqlSession.insert("shopMapper.insertMenuPhoto", menuPhoto);
	}

	// 파트너 - 메인 메뉴 등록
	@Override
	public int insertMainMenu(MainMenu mainMenu) {
		return sqlSession.insert("shopMapper.insertMainMenu", mainMenu);
	}

	// 파트너 - 메뉴사진 삭제
	@Override
	public int deleteMenuPhoto(int shopNo) {
		return sqlSession.delete("shopMapper.deleteMenuPhoto", shopNo);
	}

	// 파트너 - 메인메뉴 삭제
	@Override
	public int deleteMainMenu(int shopNo) {
		return sqlSession.delete("shopMapper.deleteShopMenu", shopNo);
	}
	

}