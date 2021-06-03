package com.donzzul.spring.chatting.domain;

public class Chat {
	private int chatRoomNo;
	private int chatBoxNo;
	private int userNo;
	private String messageContent;
	private String messageTime;
	
	public Chat() {}

	public Chat(int chatRoomNo, int chatBoxNo, int userNo, String messageContent, String messageTime) {
		super();
		this.chatRoomNo = chatRoomNo;
		this.chatBoxNo = chatBoxNo;
		this.userNo = userNo;
		this.messageContent = messageContent;
		this.messageTime = messageTime;
	}

	public int getChatRoomNo() {
		return chatRoomNo;
	}

	public void setChatRoomNo(int chatRoomNo) {
		this.chatRoomNo = chatRoomNo;
	}

	public int getChatBoxNo() {
		return chatBoxNo;
	}

	public void setChatBoxNo(int chatBoxNo) {
		this.chatBoxNo = chatBoxNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}

	@Override
	public String toString() {
		return "chat [chatRoomNo=" + chatRoomNo + ", chatBoxNo=" + chatBoxNo + ", userNo=" + userNo
				+ ", messageContent=" + messageContent + ", messageTime=" + messageTime + "]";
	}
}
