package de.fettlaus.thekraken.events;

public class NewConnectionEvent implements ViewEvent {
	String Address;

	String Port;

	public NewConnectionEvent(String address, String port) {
		super();
		Address = address;
		Port = port;
	}

	public String getAddress() {
		return Address;
	}

	public String getPort() {
		return Port;
	}
}
