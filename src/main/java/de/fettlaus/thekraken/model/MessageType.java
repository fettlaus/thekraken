package de.fettlaus.thekraken.model;

public enum MessageType {
	ERROR(0x00), MESS(0x01), // Nachricht vom Server.
	UART(0x02), // Datenpaket mit UART-Daten
	SPID(0x03), // Datenpaket mit SPI-Daten
	STIM(0x04), // Setze Zeit in Remote auf Zeit des angehängten Zeitstempels [UDP]
	PING(0x05), // Anfrage auf Pong-Antwort (Kann für explizite Zeit-Anfrage [UDP]
				// benutzt werden)
	PONG(0x06), // Antwort auf Ping-Anfrage [UDP]
	GSET(0x07), // Fordere Einstellungen von Remote an [unused]
	SSET(0x08), // Setze Einstellungen in Remote [unused]
	SETT(0x09), // Einstellungen von Remote; [unused]
	EXIT(0x10); // Shutdown server
	public static MessageType fromInt(int val) {
		for (final MessageType x : MessageType.values()) {
			if (val == x.getValue()) {
				return x;
			}
		}
		throw new IllegalArgumentException("MessageType with value" + val);
	}

	private final int id;

	MessageType(int id) {
		this.id = id;
	}

	public int getValue() {
		return id;
	}
}
