package com.donzzul.spring.mzrecommendation.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.mzrecommendation.domain.MzRecommendation;
import com.donzzul.spring.mzrecommendation.service.MzRecommendationService;
import com.donzzul.spring.mzrecommendation.store.MzRecommendationStore;

@Service
public class MzRecommendationImpl implements MzRecommendationService {

	@Autowired
	private MzRecommendationStore mzStore;
	
	@Override
	public ArrayList<MzRecommendation> selectAllRecommendation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MzRecommendation selectOneRecommendation(int recommendationNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertRecommendation(MzRecommendation recommend) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateRecommendation(MzRecommendation recommend) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteRecommendation(int recommendationNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
