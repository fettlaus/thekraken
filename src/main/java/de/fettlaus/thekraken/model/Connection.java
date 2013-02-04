package de.fettlaus.thekraken.model;

import java.io.IOException;
import java.net.UnknownHostException;

public interface Connection {
	void close();

	boolean connect() throws UnknownHostException, IOException;

	String getAddress();

	int getPort();

	void sendMessage(Message msg);

	void sendPing();
}
