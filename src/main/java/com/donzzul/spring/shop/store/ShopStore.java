package com.donzzul.spring.shop.store;

import java.util.ArrayList;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.shop.domain.MainMenu;
import com.donzzul.spring.shop.domain.MenuPhoto;
import com.donzzul.spring.shop.domain.Shop;

public interface ShopStore {

	public ArrayList<Shop> selectShopMap(PageInfo pi, int mapNo); // 지역별 지도 검색
	public ArrayList<Shop> selectShopMap(int mapNo); // 지역별 지도 검색
	public ArrayList<Shop> searchShop(String searchKeyWord); // 가게 전체 리스트 출력
	public ArrayList<Shop> searchShopTheme(int themeNo); // 가게 테마 리스트 출력
	public Shop selectShopOne(int shopNo); // 가게 상세 정보 출력
	public ArrayList<MainMenu> selectMainMenu(int shopNo); // 가게 메인 메뉴 출력
	public ArrayList<MenuPhoto> selectMenuPhoto(int shopNo); // 가게 메뉴 사진 출력
	public int selectListCount(int mapNo);
	public ArrayList<Shop> searchMapKeyword(String searchKeyword);

}
