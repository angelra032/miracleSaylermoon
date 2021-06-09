package com.donzzul.spring.mzreview.service.logic;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.dreamreview.domain.DreamReview;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.mzreview.domain.MzReviewPhoto;
import com.donzzul.spring.mzreview.service.MzReviewService;
import com.donzzul.spring.mzreview.store.MzReviewStore;
import com.donzzul.spring.reservation.domain.Reservation;
import com.donzzul.spring.reservation.store.ReservationStore;
import com.donzzul.spring.shop.domain.Shop;

@Service
public class MzReviewServiceImpl implements MzReviewService {
	@Autowired
	private MzReviewStore mStore;
	@Autowired
	private ReservationStore rStore;
	
	@Override
	public ArrayList<MzReview> selectAllReview() {
		return mStore.selectAllReview();
	}
	
	@Override
	public ArrayList<MzReview> selectAllReview(PageInfo pi) {
		return mStore.selectAllReview(pi);
	}
	
	@Override
	public int getListCount() {
		return mStore.getListCount();
	} 

	@Override
	public MzReview selectOneReview(int mzReviewNo) {
		return mStore.selectOneReview(mzReviewNo);
	}

	@Override
	public int insertMzReview(MzReview mzReview, Reservation reservation) {
		//트랜잭션
		rStore.updateRstate(reservation);
		return mStore.insertMzReview(mzReview);
	}

	@Override
	public int updateMzReview(MzReview mzReview) {
		return mStore.updateMzReview(mzReview);
	}

    
    @Override //D selectAllReview (사진 포함)
    public ArrayList<MzReview> selectAllMzReview(int shopNo) {
        return mStore.selectAllMzReview(shopNo);
    }
    
	@Override
	public int deleteMzReview(int mReviewNo) {
		return mStore.deleteMzReview(mReviewNo);
	}

	// 메인페이지용 세개 가져오기
	@Override
	public ArrayList<MzReview> selectThreeReview() {
		return mStore.selectThreeReview();
	}
	// 마이페이지용 세개 가져오기
	@Override
	public ArrayList<MzReview> selectThreeReviewToMyPage(int userNo) {
		return mStore.selectThreeReviewToMyPage(userNo);
	}
	
	// 사진
	@Override
	public int insertPhoto(MzReviewPhoto mzReviewPhoto) {
		return mStore.insertPhoto(mzReviewPhoto);
	}

	@Override
	public ArrayList<MzReviewPhoto> selectPhoto(int mzReviewNo) {
		return mStore.selectPhoto(mzReviewNo);
	}

	@Override
	public int deleteBeforePhoto(int mzReviewNo) {
		return mStore.deleteBeforePhoto(mzReviewNo);
	}

	
	// 더보기 - 가게 상세 맛집후기
	@Override
	public ArrayList<MzReview> selectAllMzReview(HashMap<String, Object> searchParam) {
		return mStore.selectAllMzReview(searchParam);
	}

	// 더보기 - 가게 상세 맛집후기 갯수
	@Override
	public int selectMzReviewCount(int shopNo) {
		return mStore.selectMzReviewCount(shopNo);
	}
	
	// 조회수 증가
	@Override
	public int updateHit(int mzReviewNo) {
		return mStore.updateHit(mzReviewNo);
	}

	// mz마이페이지에 전체목록 출력 
	@Override
	public ArrayList<MzReview> selectAllReviewToMyPage(int userNo, PageInfo pi) {
		return mStore.selectAllReviewToMyPage(userNo, pi);
	}

	// MZ마이페이지 페이징
	@Override
	public int getListCountToMyPage(int userNo) {
		return mStore.getListCountToMyPage(userNo);
	}

	@Override
	public ArrayList<MzReviewPhoto> selectRecentPhoto(ArrayList<MzReview> reviewList) {
		ArrayList<MzReviewPhoto> mzPhotoList = new ArrayList<MzReviewPhoto>();
		for(int i = 0; i < reviewList.size(); i++) {
			MzReviewPhoto mReviewPhoto = new MzReviewPhoto();
			int mzReviewNo = reviewList.get(i).getmReviewNo();
			System.out.println("mzReviewNo : " + mzReviewNo);
			mReviewPhoto = mStore.selectRecentPhoto(mzReviewNo);
			
			mzPhotoList.add(mReviewPhoto);
		}
		return mzPhotoList;
	}


	
}
