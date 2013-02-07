package de.fettlaus.thekraken.events;

public class NewNotificationEvent implements ModelEvent {
	public enum NotificationType {
		NO_HOST_FOUND, 
		CLOSING_ERROR, 
		NEW_CONNECTION, 
		CANT_SEND_UDP, 
		CANT_CONNECT_TO_HOST, 
		UDP_ERROR;
	}

	NotificationType type;
	String message;

	public NewNotificationEvent(NotificationType type) {
		this(type, null);
	}

	public NewNotificationEvent(NotificationType type, String message) {
		super();
		this.type = type;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public NotificationType getType() {
		return type;
	}
}
