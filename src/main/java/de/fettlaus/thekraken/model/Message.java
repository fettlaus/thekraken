package de.fettlaus.thekraken.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface Message extends Comparable<Message> {
	public static final int HEADER_LENGTH = 11;
	public static final int MAX_BODY_LENGTH = 512;

	long getDifference();

	String getMessage();

	Connection getSourceConnection();

	long getTimestamp();

	MessageType getType();

	void read(DataInputStream arg0) throws IOException, ClassNotFoundException, IllegalArgumentException;

	void setLastTimestamp(long lastTimestamp);

	void write(DataOutputStream arg0) throws IOException;

}
