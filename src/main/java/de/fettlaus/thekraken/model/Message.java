package de.fettlaus.thekraken.model;

public interface Message {

	enum MessageType{MESS,UART,SPID,STIM,PING,PONG,GSET,SSET,SETT}
	MessageType getType();
	Connection getTarget();
	long getTimestamp();
	String getMessage();
	byte[] getBuffer();
	void parseHeader();
	int getLength();
}
