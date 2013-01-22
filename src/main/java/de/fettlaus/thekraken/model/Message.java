package de.fettlaus.thekraken.model;

public interface Message {
	enum MessageType{MESS}
	MessageType getType();
	long getTimestamp();
	String getMessage();
}
