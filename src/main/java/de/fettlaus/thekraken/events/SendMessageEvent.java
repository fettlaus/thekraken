package de.fettlaus.thekraken.events;

public class SendMessageEvent implements ViewEvent {
	String message;
	int targetIndex;
	boolean uart;

	public SendMessageEvent(String message, int targetIndex, boolean uart) {
		super();
		this.message = message;
		this.targetIndex = targetIndex;
		this.uart = uart;
	}

	public String getMessage() {
		return message;
	}

	public int getTargetIndex() {
		return targetIndex;
	}

	public boolean isUart() {
		return uart;
	}
}
