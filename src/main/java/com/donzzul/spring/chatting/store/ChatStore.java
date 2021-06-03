package com.donzzul.spring.chatting.store;

import com.donzzul.spring.chatting.domain.Chat;

public interface ChatStore {

	public int insertChatContents(Chat chat);
}
