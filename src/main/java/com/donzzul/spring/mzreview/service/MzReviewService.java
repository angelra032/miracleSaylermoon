package com.donzzul.spring.mzreview.service;

import java.util.ArrayList;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.mzreview.domain.MzReviewPhoto;
import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.shop.domain.Shop;

public interface MzReviewService {
	// 리스트, 디테일, 추가, 수정, 삭제 (이후 페이징)
	public ArrayList<MzReview> selectAllReview();
	public ArrayList<MzReview> selectAllReview(PageInfo pi);
	public MzReview selectOneReview(int mzReviewNo);
	public int insertMzReview(MzReview mzReview, Reservation reservation);
	public int updateMzReview(MzReview mzReview);
	public int deleteMzReview(int mzReviewNo);
    public ArrayList<MzReview> selectAllMzReview(int shopNo);
	public int getListCount();
	public ArrayList<MzReview> selectThreeReview();
	// 사진
	public int insertPhoto(MzReviewPhoto mzReviewPhoto);
	public ArrayList<MzReviewPhoto> selectPhoto(int mzReviewNo);
	public int deleteBeforePhoto(int mzReviewNo);
}
