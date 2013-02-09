package de.fettlaus.thekraken.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface Message extends Comparable<Message>{
	public static final int HEADER_LENGTH = 11;
	public static final int MAX_BODY_LENGTH = 512;

	Connection getSourceConnection();

	String getMessage();

	long getTimestamp();

	MessageType getType();

	void read(DataInputStream arg0) throws IOException, ClassNotFoundException, IllegalArgumentException;

	void write(DataOutputStream arg0) throws IOException;

}
