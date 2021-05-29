package com.donzzul.spring.notiqna.service.logic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.common.PageInfo;
import com.donzzul.spring.notiqna.domain.Qna;
import com.donzzul.spring.notiqna.service.QnaService;
import com.donzzul.spring.notiqna.store.QnaStore;

@Service
public class QnaServiceImpl implements QnaService{

	@Autowired
	private QnaStore qnaStore;

	@Override
	public int getListCount() {
		return qnaStore.getListCount();
	}
	
	@Override
	public ArrayList<Qna> selectAllQna() {
		return qnaStore.selectAllQna();
	}
	
	@Override
	public ArrayList<Qna> selectAllQna(PageInfo pi) {
		return qnaStore.selectAllQna(pi);
	}

	@Override
	public Qna selectOneQna(int qaNo) {
		return qnaStore.selectOneQna(qaNo);
	}

	@Override
	public int insertQna(Qna qna) {
		return qnaStore.insertQna(qna);
	}
	
	@Override
	public int updateGroup(Qna qna) {
		return qnaStore.updateGroup(qna);
	}

	@Override
	public int updateQna(Qna qna) {
		return qnaStore.updateQna(qna);
	}

	@Override
	public int deleteQna(int qaNo) {
		return qnaStore.deleteQna(qaNo);
	}

	// 꿈나무 마이페이지
	@Override
	public ArrayList<Qna> dreamQnaUpToThree(int userNo) {
		return qnaStore.dreamQnaUpToThree(userNo);
	}

	@Override
	public ArrayList<Qna> qnaListBydream(int userNo, PageInfo pi) {
		ArrayList<Qna> qList = qnaStore.qnaListBydream(userNo, pi);
		return qList;
	}

	@Override
	public int dreamListCount(int userNo) {
		return qnaStore.dreamListCount(userNo);
	}
	
	// 사업자 마이페이지
	@Override
	public ArrayList<Qna> shopQnaUpToThree(int shopNo) {
		return qnaStore.shopQnaUpToThree(shopNo);
	}
	
	@Override
	public ArrayList<Qna> qnaListByPartner(int shopNo, PageInfo pi) {
		ArrayList<Qna> qList = qnaStore.qnaListByPartner(shopNo, pi);
		return qList;
	}

	@Override
	public int partnerListCount(int shopNo) {
		return qnaStore.partnerListCount(shopNo);
	}

	// 관리자 페이지
	@Override
	public ArrayList<Qna> adminQnaList(PageInfo pi) {
		return qnaStore.adminQnaList(pi);
	}

	@Override
	public int insertReply(Qna replyqna) {
		return qnaStore.insertReply(replyqna);
	}

	@Override
	public int updateQnaReply(int qnaNo) {
		return qnaStore.updateQnaReply(qnaNo);
	}
}
