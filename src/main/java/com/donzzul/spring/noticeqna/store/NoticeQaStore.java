package com.donzzul.spring.noticeqna.store;

import java.sql.Connection;
import java.util.ArrayList;

import com.donzzul.spring.noticeqna.domain.NoticeQa;

public interface NoticeQaStore {
	public ArrayList<NoticeQa> selectAllNotice(); // 공지사항 리스트
	public NoticeQa selectOneNotice(Connection conn, int noticeNo); // 공지사항 조회
	public int insertNotice(Connection conn, NoticeQa notice); // 공지사항 등록
	public int updateNotice(Connection conn, NoticeQa notice); // 공지사항 수정
	public int deleteNotice(Connection conn, int noticeNo); // 공지사항 삭제
	//
	
	public ArrayList<NoticeQa> selectAllQna(); // Qna 리스트
	public NoticeQa selectOneQna(Connection conn, int qnaNo);
	public int insertQna(Connection conn, NoticeQa qna);
	public int updateQna(Connection conn, NoticeQa qna);
	public int deleteQna(Connection conn, NoticeQa qna);
	
	
}
