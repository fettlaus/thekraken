package de.fettlaus.thekraken.model;

import java.io.IOException;
import java.net.InetAddress;

public interface Connection extends Runnable {

	void close();

	void connect() throws IOException;

	InetAddress getAddress();

	int getPort();

	void sendMessage(Message msg);

}
