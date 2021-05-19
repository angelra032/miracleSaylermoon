package com.donzzul.spring.shop.domain;

public class ShopSearch {

	private int mapNo;
	private String searchKeyword;
	
	public ShopSearch() {}

	public ShopSearch(int mapNo, String searchKeyword) {
		super();
		this.mapNo = mapNo;
		this.searchKeyword = searchKeyword;
	}

	public int getMapNo() {
		return mapNo;
	}

	public void setMapNo(int mapNo) {
		this.mapNo = mapNo;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	@Override
	public String toString() {
		return "ShopSearch [mapNo=" + mapNo + ", searchKeyword=" + searchKeyword + "]";
	}
	
	
	
}
