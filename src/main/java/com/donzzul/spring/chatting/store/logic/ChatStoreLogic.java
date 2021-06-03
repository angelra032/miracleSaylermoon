package com.donzzul.spring.chatting.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.donzzul.spring.chatting.domain.Chat;
import com.donzzul.spring.chatting.store.ChatStore;

@Repository
public class ChatStoreLogic implements ChatStore{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertChatContents(Chat chat) {
		int result = sqlSession.insert("chatMapper.insertChatContents",chat);
		return result;
	}

}
