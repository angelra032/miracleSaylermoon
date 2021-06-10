package com.donzzul.spring.dreamreview.service.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.dreamreview.domain.DreamReview;
import com.donzzul.spring.dreamreview.service.DreamReviewService;
import com.donzzul.spring.dreamreview.store.DreamReviewStore;
import com.donzzul.spring.mzreview.domain.MzReview;
import com.donzzul.spring.shop.domain.Shop;

@Service
public class DreamReviewServiceImpl implements DreamReviewService {

	@Autowired
	private DreamReviewStore drStore;
	
	@Override
	public int getListCount() {
		return drStore.selectListCount();
	}

	@Override
	public ArrayList<DreamReview> selectAllDreamReview() {
		return drStore.selectAllDreamReview();
	}
	
	@Override
	public ArrayList<DreamReview> selectAllDreamReview(PageInfo pi) {
		return  drStore.selectAllDreamReview(pi);
	}
	
	@Override
	public DreamReview selectOneDreamReview(int drmRviewNo) {
		return drStore.selectOneDreamReview(drmRviewNo);
	}

	@Override
	public int insertDreamReview(DreamReview dreamReview) {
		int result = drStore.insertDreamReview(dreamReview);
		return result;
	}

	@Override
	public int updateDreamReview(DreamReview dreamReview) {
		return drStore.updateDreamReview(dreamReview);
	}

	@Override
	public int deleteDreamReview(int drmRviewNo) {
		return drStore.deleteDreamReview(drmRviewNo);
	}

    // 꿈나무 마이 페이지
	@Override
	public ArrayList<DreamReview> drmRwUptoThree(int userNo) {
		return drStore.drmRwUptoThree(userNo);
	}

	@Override
	public int dreamGetListCount(int userNo) {
		return drStore.dreamGetListCount(userNo);
	}

	@Override
	public ArrayList<DreamReview> reviewListByDream(int userNo, PageInfo pi) {
		return drStore.reviewListByDream(userNo, pi);
	}

	// 메인페이지에 하나 뿌려주기
	@Override
	public DreamReview selectOneDreamReview() {
		return drStore.selectOneDreamReview();
	}
	
	@Override
	public ArrayList<DreamReview> selectAllDreamReview(int shopNo) {
		return drStore.selectAllDreamReview(shopNo);
	}

	@Override //D 가게 전체 후기 중 한 개 가져오기
	public ArrayList<DreamReview> selectDMReviewOne(ArrayList<Shop> themeList) {
		ArrayList<DreamReview> reviewOneList = new ArrayList<DreamReview>();
		for(int i = 0; i < themeList.size(); i++) {
			DreamReview recentReview = new DreamReview();
			int shopNo = themeList.get(i).getShopNo();
			System.out.println("shopNo : " + shopNo); // 확인용
			recentReview = drStore.selectDMReviewOne(shopNo);
			System.out.println("최근후기 : " +recentReview);
			
			reviewOneList.add(recentReview);
		}
		return reviewOneList;
	}
	
	@Override //D 가게 후기 랭킹 가져오기
	public ArrayList<Integer> selectReviewRanking() {
		return drStore.selectReviewRanking();
	}

	
	// 더보기 - 전체 후기 가져오기
	@Override
	public ArrayList<DreamReview> selectDMReviewAll(HashMap<String, Object> searchParam) {
		System.out.println("서비스"+searchParam);
		return drStore.selectDMReviewAll(searchParam);
	}

	// 더보기 - 감사 후기 가져오기
	@Override
	public ArrayList<DreamReview> selectAllDreamReview(HashMap<String, Object> searchParam) {
		return drStore.selectAllDreamReview(searchParam);
	}

	// 더보기 - 전체 후기 갯수 가져오기
	@Override
	public int selectDMReviewCount(int shopNo) {
		return drStore.selectDMReviewCount(shopNo);
	}

	// 더보기 - 감사 후기 갯수 가져오기
	@Override
	public int selectDreamReviewCount(int shopNo) {
		return drStore.selectDreamReviewCount(shopNo);
	}
	
	// 조회수
	@Override
	public int updateHit(int drmReviewNo) {
		return drStore.updateHit(drmReviewNo);
	}

	@Override
	public ArrayList<DreamReview> deleteAndSelectPick(int drmRviewNo, int userNo, PageInfo pi) {
		int deleteResult = drStore.deleteDreamReview(drmRviewNo);
		List<DreamReview> result = null;
		if(deleteResult>0) {
			result = drStore.reviewListByDream(userNo, pi);
		}
		return (ArrayList<DreamReview>)result;
	}

}
