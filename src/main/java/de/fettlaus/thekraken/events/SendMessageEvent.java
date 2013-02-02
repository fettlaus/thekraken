package de.fettlaus.thekraken.events;

public class SendMessageEvent implements ViewEvent {
	String message;
	int targetIndex;

	public SendMessageEvent(String message, int targetIndex) {
		super();
		this.message = message;
		this.targetIndex = targetIndex;
	}

	public String getMessage() {
		return message;
	}

	public int getTargetIndex() {
		return targetIndex;
	}
}
