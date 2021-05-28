package com.donzzul.spring.shop.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.shop.domain.MainMenu;
import com.donzzul.spring.shop.domain.MenuPhoto;
import com.donzzul.spring.shop.domain.Shop;

public interface ShopService {
	
	public ArrayList<Shop> selectShopMap(PageInfo pi, HashMap<String, String> selectedLocation); // 지역별 지도 검색(리스트 출력용)
	public ArrayList<Shop> selectShopMap(HashMap<String, String> selectedLocation); // 지역별 지도 검색(마커용)
	public ArrayList<Shop> searchMapKeyword(PageInfo pi, String searchKeyword); // 지도 키워드 검색
	public ArrayList<Shop> searchMapKeyword(String searchKeyword); // 지도 키워드 검색
	public ArrayList<Shop> selectShopTheme(PageInfo pi, String themeWord); // 가게 테마 리스트 출력
	public ArrayList<Shop> searchShop(String searchKeyword); // 가게 키워드 검색 리스트 출력
	public Shop selectShopOne(int shopNo); // 가게 상세 정보 출력
	public ArrayList<MainMenu> selectMainMenu(int shopNo); // 가게 메인 메뉴 출력
	public ArrayList<MenuPhoto> selectMenuPhoto(int shopNo); // 가게 메뉴 사진 출력
	public int selectListCount(HashMap<String, String> selectedLocation); // 지역별 지도 전체 게시글 갯수
	public int selectKeyListCount(String searchKeyword); // 지도 검색시 전체 게시글 갯수
	public int selectShopThemeCount(String themeWord); // 가게 검색시 전체 게시글 갯수 (테마, 키워드 검색 포함)
    public ArrayList<Shop> selectAllShopListDESC();
    public ArrayList<Shop> selectAllShopListASC();	
}
