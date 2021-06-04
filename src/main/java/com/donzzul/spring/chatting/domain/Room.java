package com.donzzul.spring.chatting.domain;

public class Room {
	private int roomNumber;
	private String roomName;
	
	public Room () {}

	public Room(int roomNumber, String roomName) {
		super();
		this.roomNumber = roomNumber;
		this.roomName = roomName;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	@Override
	public String toString() {
		return "Room [roomNumber=" + roomNumber + ", roomName=" + roomName + "]";
	}
	
}
