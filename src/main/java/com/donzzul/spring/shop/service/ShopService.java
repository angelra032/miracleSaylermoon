package com.donzzul.spring.shop.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.shop.domain.MainMenu;
import com.donzzul.spring.shop.domain.MenuPhoto;
import com.donzzul.spring.shop.domain.Shop;

public interface ShopService {
	
	public ArrayList<Shop> selectShopMap(PageInfo pi, HashMap<String, String> selectedLocation); // 지역별 지도 검색(리스트 출력용)
	public ArrayList<Shop> selectShopMap(HashMap<String, String> selectedLocation); // 지역별 지도 검색(마커용)
	public ArrayList<Shop> searchMapKeyword(PageInfo pi, String searchKeyword); // 지도 키워드 검색(리스트 출력용)
	public ArrayList<Shop> searchMapKeyword(String searchKeyword); // 지도 키워드 검색(마커용)
	public ArrayList<Shop> selectShopTheme(PageInfo pi, HashMap<String, String> selectedtheme); // 가게 테마 리스트 출력
	public ArrayList<Shop> selectShopRank(ArrayList<Integer> sRank); // 가게 테마 후기 랭킹순 출력
	public ArrayList<Shop> selectNewShop(); // 새로 등록된 가게 리스트 출력
	public ArrayList<Shop> searchShop(PageInfo pi, HashMap<String, String> searchedKey); // 가게 키워드 검색 리스트 출력
	public Shop selectShopOne(int shopNo); // 가게 상세 정보 출력
	public ArrayList<MainMenu> selectMainMenu(int shopNo); // 가게 메인 메뉴 출력
	public ArrayList<MenuPhoto> selectMenuPhoto(int shopNo); // 가게 메뉴 사진 출력
	public int selectListCount(HashMap<String, String> selectedLocation); // 지역별 지도 전체 게시글 갯수
	public int selectKeyListCount(String searchKeyword); // 지도 검색시 전체 게시글 갯수
	public int selectShopThemeCount(HashMap<String, String> selectedtheme); // 가게 테마 전체 게시글 갯수
	public int searchShopCount(HashMap<String, String> searchedKey); // 가게 키워드 검색 전체 게시글 갯수
    public ArrayList<Shop> selectAllShopListDESC();
    public ArrayList<Shop> selectAllShopListASC();	
    public int getListCount();
    public ArrayList<Shop> selectAllShopList(PageInfo pi);
    public ArrayList<Shop> selectAllShopListThree();
	
}
