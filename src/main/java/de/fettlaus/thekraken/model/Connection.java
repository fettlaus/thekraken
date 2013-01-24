package de.fettlaus.thekraken.model;

public interface Connection {
	String getAddress();
	int getPort();
	void sendMessage(Message msg);
	void sendPing();
	boolean connect();
}
