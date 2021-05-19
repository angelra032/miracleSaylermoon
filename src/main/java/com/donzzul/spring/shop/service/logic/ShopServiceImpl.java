package com.donzzul.spring.shop.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.shop.domain.MainMenu;
import com.donzzul.spring.shop.domain.MenuPhoto;
import com.donzzul.spring.shop.domain.Shop;
import com.donzzul.spring.shop.domain.ShopSearch;
import com.donzzul.spring.shop.domain.PageInfo;
import com.donzzul.spring.shop.service.ShopService;
import com.donzzul.spring.shop.store.ShopStore;

@Service
public class ShopServiceImpl implements ShopService {
	
	@Autowired
	private ShopStore sStore;

	@Override
	public ArrayList<Shop> selectShopMap(PageInfo pi, ShopSearch mapNo) {
		return sStore.selectShopMap(pi, mapNo);
	}

	@Override
	public ArrayList<Shop> searchShop(String searchKeyword) {
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
	public int selectListCount(ShopSearch mapNo) {
		return sStore.selectListCount(mapNo);
	}

	@Override
	public ArrayList<Shop> searchMapKeyword(String searchKeyword) {
		return sStore.searchMapKeyword(searchKeyword);
	}

}
