package de.fettlaus.thekraken.model;

public interface Message {
public static final int HEADER_LENGTH = 7;
public static final int MAX_BODY_LENGTH = 512;
	enum MessageType{MESS,UART,SPID,STIM,PING,PONG,GSET,SSET,SETT}
	MessageType getType();
	Connection getTarget();
	long getTimestamp();
	String getMessage();
	byte[] getHeader();
	byte[] getBody();
	int getLength();
}
