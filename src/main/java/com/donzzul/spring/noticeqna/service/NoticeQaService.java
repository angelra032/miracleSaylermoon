package com.donzzul.spring.noticeqna.service;

import java.util.ArrayList;

import com.donzzul.spring.noticeqna.domain.NoticeQa;

public interface NoticeQaService {
	public ArrayList<NoticeQa> selectAllNotice(); // 공지사항 리스트
	public NoticeQa selectOneNotice(int noticeNo); // 공지사항 조회
	public int insertNotice(NoticeQa notice); // 공지사항 등록
	public int updateNotice(NoticeQa notice); // 공지사항 수정
	public int deleteNotice(int noticeNo); // 공지사항 삭제
	//
	
	public ArrayList<NoticeQa> selectAllQna(); // Qna 리스트
	public NoticeQa selectOneQna(int qnaNo);
	public int insertQna(NoticeQa qna);
	public int updateQna(NoticeQa qna);
	public int deleteQna(NoticeQa qna);
}
