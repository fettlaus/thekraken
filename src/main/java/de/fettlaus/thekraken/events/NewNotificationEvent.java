package de.fettlaus.thekraken.events;

import de.fettlaus.thekraken.model.TimeKeeper;

public class NewNotificationEvent implements ModelEvent {
	public enum NotificationType {
		NO_HOST_FOUND, 
		CLOSING_ERROR, 
		NEW_CONNECTION, 
		CANT_SEND_UDP, 
		CANT_CONNECT_TO_HOST, 
		UDP_ERROR,
		BUFFER_CHANGED;
	}

	NotificationType type;

	String message;
	long timestamp;

	public NewNotificationEvent(NotificationType type) {
		this(type, null);
	}

	public NewNotificationEvent(NotificationType type, String message) {
		super();
		this.type = type;
		this.message = message;
		timestamp = TimeKeeper.time();
	}

	public String getMessage() {
		return message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public NotificationType getType() {
		return type;
	}
}
