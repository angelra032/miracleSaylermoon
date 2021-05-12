package com.donzzul.spring.mzrecommendation.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.mzrecommendation.domain.MzRecommendation;
import com.donzzul.spring.mzrecommendation.service.MzRecommendService;
import com.donzzul.spring.mzrecommendation.store.MzRecommendStore;

@Service
public class MzRecommendImpl implements MzRecommendService {

	@Autowired
	private MzRecommendStore mzStore;

	@Override
	public ArrayList<MzRecommendation> selectAllRecommend() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MzRecommendation selectOneRecommend(int recommendationNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertRecommend(MzRecommendation recommend) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateRecommend(MzRecommendation recommend) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteRecommend(int recommendationNo) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
