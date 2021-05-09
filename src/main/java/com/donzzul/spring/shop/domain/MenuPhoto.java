package com.donzzul.spring.shop.domain;

import java.sql.Timestamp;

public class MenuPhoto {

	private int shopNo;
	private int menuFileNo;
	private String menuFileName;
	private String menuFilePath;
	private long menuFileSize;
	private Timestamp menuUploadTime;
	
	public MenuPhoto() {}

	public MenuPhoto(int shopNo, int menuFileNo, String menuFileName, String menuFilePath, long menuFileSize,
			Timestamp menuUploadTime) {
		super();
		this.shopNo = shopNo;
		this.menuFileNo = menuFileNo;
		this.menuFileName = menuFileName;
		this.menuFilePath = menuFilePath;
		this.menuFileSize = menuFileSize;
		this.menuUploadTime = menuUploadTime;
	}

	public int getShopNo() {
		return shopNo;
	}

	public void setShopNo(int shopNo) {
		this.shopNo = shopNo;
	}

	public int getMenuFileNo() {
		return menuFileNo;
	}

	public void setMenuFileNo(int menuFileNo) {
		this.menuFileNo = menuFileNo;
	}

	public String getMenuFileName() {
		return menuFileName;
	}

	public void setMenuFileName(String menuFileName) {
		this.menuFileName = menuFileName;
	}

	public String getMenuFilePath() {
		return menuFilePath;
	}

	public void setMenuFilePath(String menuFilePath) {
		this.menuFilePath = menuFilePath;
	}

	public long getMenuFileSize() {
		return menuFileSize;
	}

	public void setMenuFileSize(long menuFileSize) {
		this.menuFileSize = menuFileSize;
	}

	public Timestamp getMenuUploadTime() {
		return menuUploadTime;
	}

	public void setMenuUploadTime(Timestamp menuUploadTime) {
		this.menuUploadTime = menuUploadTime;
	}

	@Override
	public String toString() {
		return "MenuPhoto [shopNo=" + shopNo + ", menuFileNo=" + menuFileNo + ", menuFileName=" + menuFileName
				+ ", menuFilePath=" + menuFilePath + ", menuFileSize=" + menuFileSize + ", menuUploadTime="
				+ menuUploadTime + "]";
	}
	
}
