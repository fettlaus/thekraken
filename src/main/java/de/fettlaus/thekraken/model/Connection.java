package de.fettlaus.thekraken.model;

public interface Connection {
	void close();

	boolean connect();

	String getAddress();

	int getPort();

	void sendMessage(Message msg);
}
