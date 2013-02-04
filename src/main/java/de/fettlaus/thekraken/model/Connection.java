package de.fettlaus.thekraken.model;

import java.net.InetAddress;

public interface Connection {
	void close();

	boolean connect();

	InetAddress getAddress();

	int getPort();

	void sendMessage(Message msg);
}
