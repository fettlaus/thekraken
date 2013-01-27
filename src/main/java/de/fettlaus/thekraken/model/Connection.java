package de.fettlaus.thekraken.model;

import java.io.IOException;
import java.net.UnknownHostException;

public interface Connection {
	String getAddress();
	int getPort();
	void sendMessage(Message msg);
	void sendPing();
	boolean connect() throws UnknownHostException, IOException;
}
