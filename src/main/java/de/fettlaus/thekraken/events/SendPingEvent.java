package de.fettlaus.thekraken.events;

public class SendPingEvent implements ViewEvent {
	int connection;

	public SendPingEvent(int connection) {
		super();
		this.connection = connection;
	}

	public int getConnection() {
		return connection;
	}
}
