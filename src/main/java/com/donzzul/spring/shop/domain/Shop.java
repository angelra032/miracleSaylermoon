package com.donzzul.spring.shop.domain;

import java.sql.Timestamp;

public class Shop {
	
	private int shopNo;
	private int reserveNo;
	private String shopName;
	private String shopShortAddr;
	private String shopAddr;
	private String shopTarget;
	private String shopProduct;
	private String shopType;
	private String shopFileName;
	private String shopFilePath;
	private long shopFileSize;
	private Timestamp shopUploadTime;
	private int shopPhone;
	private String shopParkingYn;
	private int shopMaxReservation;
	private String shopContent;
	private String shopLat;
	private String shopLng;
	private int shopPoint;
	private int partnerNo;
	private int donNo;
	private String startTime;
	private String endTime;
	private String dayOff;
	
	public Shop() {}

	public Shop(int shopNo, int reserveNo, String shopName, String shopShortAddr, String shopAddr, String shopTarget,
			String shopProduct, String shopType, String shopFileName, String shopFilePath, long shopFileSize,
			Timestamp shopUploadTime, int shopPhone, String shopParkingYn, int shopMaxReservation, String shopContent,
			String shopLat, String shopLng, int shopPoint, int partnerNo, int donNo, String startTime, String endTime,
			String dayOff) {
		super();
		this.shopNo = shopNo;
		this.reserveNo = reserveNo;
		this.shopName = shopName;
		this.shopShortAddr = shopShortAddr;
		this.shopAddr = shopAddr;
		this.shopTarget = shopTarget;
		this.shopProduct = shopProduct;
		this.shopType = shopType;
		this.shopFileName = shopFileName;
		this.shopFilePath = shopFilePath;
		this.shopFileSize = shopFileSize;
		this.shopUploadTime = shopUploadTime;
		this.shopPhone = shopPhone;
		this.shopParkingYn = shopParkingYn;
		this.shopMaxReservation = shopMaxReservation;
		this.shopContent = shopContent;
		this.shopLat = shopLat;
		this.shopLng = shopLng;
		this.shopPoint = shopPoint;
		this.partnerNo = partnerNo;
		this.donNo = donNo;
		this.startTime = startTime;
		this.endTime = endTime;
		this.dayOff = dayOff;
	}

	public int getShopNo() {
		return shopNo;
	}

	public void setShopNo(int shopNo) {
		this.shopNo = shopNo;
	}

	public int getReserveNo() {
		return reserveNo;
	}

	public void setReserveNo(int reserveNo) {
		this.reserveNo = reserveNo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopShortAddr() {
		return shopShortAddr;
	}

	public void setShopShortAddr(String shopShortAddr) {
		this.shopShortAddr = shopShortAddr;
	}

	public String getShopAddr() {
		return shopAddr;
	}

	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr;
	}

	public String getShopTarget() {
		return shopTarget;
	}

	public void setShopTarget(String shopTarget) {
		this.shopTarget = shopTarget;
	}

	public String getShopProduct() {
		return shopProduct;
	}

	public void setShopProduct(String shopProduct) {
		this.shopProduct = shopProduct;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getShopFileName() {
		return shopFileName;
	}

	public void setShopFileName(String shopFileName) {
		this.shopFileName = shopFileName;
	}

	public String getShopFilePath() {
		return shopFilePath;
	}

	public void setShopFilePath(String shopFilePath) {
		this.shopFilePath = shopFilePath;
	}

	public long getShopFileSize() {
		return shopFileSize;
	}

	public void setShopFileSize(long shopFileSize) {
		this.shopFileSize = shopFileSize;
	}

	public Timestamp getShopUploadTime() {
		return shopUploadTime;
	}

	public void setShopUploadTime(Timestamp shopUploadTime) {
		this.shopUploadTime = shopUploadTime;
	}

	public int getShopPhone() {
		return shopPhone;
	}

	public void setShopPhone(int shopPhone) {
		this.shopPhone = shopPhone;
	}

	public String getShopParkingYn() {
		return shopParkingYn;
	}

	public void setShopParkingYn(String shopParkingYn) {
		this.shopParkingYn = shopParkingYn;
	}

	public int getShopMaxReservation() {
		return shopMaxReservation;
	}

	public void setShopMaxReservation(int shopMaxReservation) {
		this.shopMaxReservation = shopMaxReservation;
	}

	public String getShopContent() {
		return shopContent;
	}

	public void setShopContent(String shopContent) {
		this.shopContent = shopContent;
	}

	public String getShopLat() {
		return shopLat;
	}

	public void setShopLat(String shopLat) {
		this.shopLat = shopLat;
	}

	public String getShopLng() {
		return shopLng;
	}

	public void setShopLng(String shopLng) {
		this.shopLng = shopLng;
	}

	public int getShopPoint() {
		return shopPoint;
	}

	public void setShopPoint(int shopPoint) {
		this.shopPoint = shopPoint;
	}

	public int getPartnerNo() {
		return partnerNo;
	}

	public void setPartnerNo(int partnerNo) {
		this.partnerNo = partnerNo;
	}

	public int getDonNo() {
		return donNo;
	}

	public void setDonNo(int donNo) {
		this.donNo = donNo;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDayOff() {
		return dayOff;
	}

	public void setDayOff(String dayOff) {
		this.dayOff = dayOff;
	}

	@Override
	public String toString() {
		return "Shop [shopNo=" + shopNo + ", reserveNo=" + reserveNo + ", shopName=" + shopName + ", shopShortAddr="
				+ shopShortAddr + ", shopAddr=" + shopAddr + ", shopTarget=" + shopTarget + ", shopProduct="
				+ shopProduct + ", shopType=" + shopType + ", shopFileName=" + shopFileName + ", shopFilePath="
				+ shopFilePath + ", shopFileSize=" + shopFileSize + ", shopUploadTime=" + shopUploadTime
				+ ", shopPhone=" + shopPhone + ", shopParkingYn=" + shopParkingYn + ", shopMaxReservation="
				+ shopMaxReservation + ", shopContent=" + shopContent + ", shopLat=" + shopLat + ", shopLng=" + shopLng
				+ ", shopPoint=" + shopPoint + ", partnerNo=" + partnerNo + ", donNo=" + donNo + ", startTime="
				+ startTime + ", endTime=" + endTime + ", dayOff=" + dayOff + "]";
	}
	
}
