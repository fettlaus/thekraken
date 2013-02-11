package de.fettlaus.thekraken.events;

public class TargetEvent implements ViewEvent {
	public enum TargetEventType {
		PING, DISCONNECT, SHUTDOWN;
	}

	TargetEventType type;
	int connection;

	public TargetEvent(TargetEventType type, int connection) {
		super();
		this.type = type;
		this.connection = connection;
	}

	public int getConnection() {
		return connection;
	}

	public TargetEventType getType() {
		return type;
	}
}
