package de.fettlaus.thekraken.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface Message {
public static final int HEADER_LENGTH = 7;
public static final int MAX_BODY_LENGTH = 512;
	MessageType getType();
	Connection getConnection();
	void setConnection(Connection conn);
	long getTimestamp();
	String getMessage();
	void read(DataInputStream arg0) throws IOException, ClassNotFoundException,
			IllegalArgumentException;
	void write(DataOutputStream arg0) throws IOException;

}
