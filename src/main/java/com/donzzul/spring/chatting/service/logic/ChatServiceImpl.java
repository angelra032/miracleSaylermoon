package com.donzzul.spring.chatting.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donzzul.spring.chatting.domain.Chat;
import com.donzzul.spring.chatting.service.ChatService;
import com.donzzul.spring.chatting.store.ChatStore;

@Service
public class ChatServiceImpl implements ChatService{

	@Autowired
	private ChatStore store;
	
	@Override
	public int insertChatContents(Chat chat) {
		int result = store.insertChatContents(chat);
		return result;
	}

}
