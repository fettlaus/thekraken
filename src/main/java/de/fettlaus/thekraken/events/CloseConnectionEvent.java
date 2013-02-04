package de.fettlaus.thekraken.events;

public class CloseConnectionEvent implements ViewEvent {
	int connection;

	public CloseConnectionEvent(int selectedIndex) {
		connection = selectedIndex;
	}

	public int getConnection() {
		return connection;
	}

}
