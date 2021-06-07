package com.donzzul.spring.shop.domain;

import java.sql.Timestamp;

public class Shop {
	
	private int shopNo;
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
	private String shopPhone;
	private String shopParking;
	private int shopMaxReserv;
	private String shopContent;
	private String shopLat;
	private String shopLng;
	private int shopPoint;
	private String shopPointYn;
	private int userNo;
	private String startTime;
	private String endTime;
	private int businessDay;
    private String showShopYN;
    private String userId;
    private String partnerVerify;
    private String partnerWithdraw;
    private String drmReviewContent;
    private String shopLongAddr;
	
	public Shop() {}

	public Shop(int shopNo, String shopName, String shopShortAddr, String shopAddr, String shopTarget,
			String shopProduct, String shopType, String shopFileName, String shopFilePath, long shopFileSize,
			Timestamp shopUploadTime, String shopPhone, String shopParking, int shopMaxReserv, String shopContent,
			String shopLat, String shopLng, int shopPoint, String shopPointYn, int userNo, String startTime,
			String endTime, int businessDay, String showShopYN, String userId, String partnerVerify,
			String partnerWithdraw, String drmReviewContent, String shopLongAddr) {
		super();
		this.shopNo = shopNo;
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
		this.shopParking = shopParking;
		this.shopMaxReserv = shopMaxReserv;
		this.shopContent = shopContent;
		this.shopLat = shopLat;
		this.shopLng = shopLng;
		this.shopPoint = shopPoint;
		this.shopPointYn = shopPointYn;
		this.userNo = userNo;
		this.startTime = startTime;
		this.endTime = endTime;
		this.businessDay = businessDay;
		this.showShopYN = showShopYN;
		this.userId = userId;
		this.partnerVerify = partnerVerify;
		this.partnerWithdraw = partnerWithdraw;
		this.drmReviewContent = drmReviewContent;
		this.shopLongAddr = shopLongAddr;
	}



	public String getShopLongAddr() {
		return shopLongAddr;
	}

	public void setShopLongAddr(String shopLongAddr) {
		this.shopLongAddr = shopLongAddr;
	}

	public int getShopNo() {
		return shopNo;
	}

	public void setShopNo(int shopNo) {
		this.shopNo = shopNo;
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

	public String getShopPhone() {
		return shopPhone;
	}

	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}

	public String getShopParking() {
		return shopParking;
	}

	public void setShopParking(String shopParking) {
		this.shopParking = shopParking;
	}

	public int getShopMaxReserv() {
		return shopMaxReserv;
	}

	public void setShopMaxReserv(int shopMaxReserv) {
		this.shopMaxReserv = shopMaxReserv;
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

	public String getShopPointYn() {
		return shopPointYn;
	}

	public void setShopPointYn(String shopPointYn) {
		this.shopPointYn = shopPointYn;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
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

	public int getBusinessDay() {
		return businessDay;
	}

	public void setBusinessDay(int businessDay) {
		this.businessDay = businessDay;
	}

	public String getShowShopYN() {
		return showShopYN;
	}

	public void setShowShopYN(String showShopYN) {
		this.showShopYN = showShopYN;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPartnerVerify() {
		return partnerVerify;
	}

	public void setPartnerVerify(String partnerVerify) {
		this.partnerVerify = partnerVerify;
	}

	public String getPartnerWithdraw() {
		return partnerWithdraw;
	}

	public void setPartnerWithdraw(String partnerWithdraw) {
		this.partnerWithdraw = partnerWithdraw;
	}
	
	public String getDrmReviewContent() {
		return drmReviewContent;
	}

	public void setDrmReviewContent(String drmReviewContent) {
		this.drmReviewContent = drmReviewContent;
	}

	@Override
	public String toString() {
		return "Shop [shopNo=" + shopNo + ", shopName=" + shopName + ", shopShortAddr=" + shopShortAddr + ", shopAddr="
				+ shopAddr + ", shopTarget=" + shopTarget + ", shopProduct=" + shopProduct + ", shopType=" + shopType
				+ ", shopFileName=" + shopFileName + ", shopFilePath=" + shopFilePath + ", shopFileSize=" + shopFileSize
				+ ", shopUploadTime=" + shopUploadTime + ", shopPhone=" + shopPhone + ", shopParking=" + shopParking
				+ ", shopMaxReserv=" + shopMaxReserv + ", shopContent=" + shopContent + ", shopLat=" + shopLat
				+ ", shopLng=" + shopLng + ", shopPoint=" + shopPoint + ", shopPointYn=" + shopPointYn + ", userNo="
				+ userNo + ", startTime=" + startTime + ", endTime=" + endTime + ", businessDay=" + businessDay
				+ ", showShopYN=" + showShopYN + ", userId=" + userId + ", partnerVerify=" + partnerVerify
				+ ", partnerWithdraw=" + partnerWithdraw + ", drmReviewContent=" + drmReviewContent + ", shopLongAddr="
				+ shopLongAddr + "]";
	}


	

}
