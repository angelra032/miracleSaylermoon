package com.donzzul.spring.shop.service.logic;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.shop.domain.MainMenu;
import com.donzzul.spring.shop.domain.MenuPhoto;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.shop.service.ShopService;
import com.donzzul.spring.shop.store.ShopStore;

@Service
public class ShopServiceImpl implements ShopService {
	
	@Autowired
	private ShopStore sStore;

	@Override
	public ArrayList<Shop> selectShopMap(PageInfo pi, HashMap<String, String> selectedLocation) {
		return sStore.selectShopMap(pi, selectedLocation);
	}
	
	public ArrayList<Shop> selectShopMap(HashMap<String, String> selectedLocation) {
		return sStore.selectShopMap(selectedLocation);
	}
	
	@Override
	public ArrayList<Shop> searchMapKeyword(PageInfo pi, String searchKeyword) {
		return sStore.searchMapKeyword(pi, searchKeyword);
	}
	
	@Override
	public ArrayList<Shop> searchMapKeyword(String searchKeyword) {
		return sStore.searchMapKeyword(searchKeyword);
	}
	
	@Override
	public ArrayList<Shop> selectShopTheme(PageInfo pi, HashMap<String, String> selectedtheme) {
		return sStore.selectShopTheme(pi, selectedtheme);
	}

	@Override
	public ArrayList<Shop> selectShopRank(ArrayList<Integer> sRank) {
		ArrayList<Shop> rankingList = new ArrayList<Shop>();
		for(int i = 0; i < sRank.size(); i++) {
			Shop shop = new Shop();
			System.out.println("shop Service sRank.get(i) : " + sRank.get(i));
			int shopNo = sRank.get(i);
			System.out.println("shop Service : " + shopNo);
			shop = sStore.selectShopRank(shopNo);
			rankingList.add(shop);
		}
		return rankingList;
	}

	@Override
	public ArrayList<Shop> selectNewShop() {
		return sStore.selectNewShop();
	}

	@Override
	public ArrayList<Shop> searchShop(PageInfo pi, HashMap<String, String> searchedKey) {
		return sStore.searchShop(pi, searchedKey);
	}
	
	@Override
	   public Shop selectShopOne(int shopNo) {
	      return sStore.selectShopOne(shopNo);
	   }


	@Override
	public ArrayList<MainMenu> selectMainMenu(int shopNo) {
		return sStore.selectMainMenu(shopNo);
	}

	@Override
	public ArrayList<MenuPhoto> selectMenuPhoto(int shopNo) {
		return sStore.selectMenuPhoto(shopNo);
	}

	@Override
	public int selectListCount(HashMap<String, String> selectedLocation) {
		return sStore.selectListCount(selectedLocation);
	}
	
	public int selectKeyListCount(String searchKeyword) {
		return sStore.selectKeyListCount(searchKeyword);
	}

    @Override
    public ArrayList<Shop> selectAllShopListDESC() {
       return sStore.selectAllShopListDESC();
    }
 
    @Override
    public ArrayList<Shop> selectAllShopListASC() {
       return sStore.selectAllShopListASC();
    }

	@Override
	public int selectShopThemeCount(HashMap<String, String> selectedtheme) {
		return sStore.selectShopThemeCount(selectedtheme);
	}
	
	@Override
	public int searchShopCount(HashMap<String, String> searchedKey) {
		return sStore.searchShopCount(searchedKey);
	}
	
	@Override
    public int getListCount() {
      return sStore.getListCount();
    }

    @Override
    public ArrayList<Shop> selectAllShopList(PageInfo pi) {
       return sStore.selectAllShopList(pi);
    }

    @Override
    public ArrayList<Shop> selectAllShopListThree() {
       return sStore.selectAllShopListThree();
    }
    
    // 파트너사업자
	@Override
	public int insertPartnerShop(Shop shop) {
		return sStore.insertPartnerShop(shop);
	}

	@Override
	public int updatePartnerShop(Shop shop) {
		return sStore.updatePartnerShop(shop);
	}

	@Override
	public Shop selectShopOneUserNo(int userNo) {
		return sStore.selectShopOneUserNo(userNo);
	}
	
	// 관리자-파트너 공개승인
	@Override
	public int updatePartnerShopShow(int shopNo) {
		return sStore.updatePartnerShopShow(shopNo);
	}

	// 파트너 - 가게 수정(다중파일 업로드)
	@Override
	public int insertMenuPhoto(MenuPhoto menuPhoto) {
		return sStore.insertMenuPhoto(menuPhoto);
	}

	 // 파트너 - 메인 메뉴 등록
	@Override
	public int insertMainMenu(MainMenu mainMenu) {
		return sStore.insertMainMenu(mainMenu);
	}

	// 파트너 - 메뉴 사진 삭제 (ajax로)
	@Override
	public int deleteMenuPhoto(String deletePhotoName) {
		return sStore.deleteMenuPhoto(deletePhotoName);
	}

	// 파트너 - 메인 메뉴 삭제
	@Override
	public int deleteMainMenu(int shopNo) {
		return sStore.deleteMainMenu(shopNo);
	}

	// 파트너 - 메뉴 사진 서버에서 삭제하기 위해 사진이름으로 select(path)
	@Override
	public ArrayList<MenuPhoto> selectMenuPhotoPath(String deletePhotoName) {
		return sStore.selectMenuPhotoPath(deletePhotoName);
	}

	



}
