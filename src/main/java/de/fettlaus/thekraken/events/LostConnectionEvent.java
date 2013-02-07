package de.fettlaus.thekraken.events;

import de.fettlaus.thekraken.model.Connection;

public class LostConnectionEvent implements ModelEvent {

	Connection connection;

	public LostConnectionEvent(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

}
