package de.fettlaus.thekraken.events;

import de.fettlaus.thekraken.ModelInterface;
import de.fettlaus.thekraken.ViewInterface;

public class ConnectClientEvent extends KrakenListener{

	public ConnectClientEvent(ModelInterface model) {
		super(model);
	}

	@Override
	public void fireEvent(ViewInterface view) {
		view.setNotification("Verbindung herstellen!");
	}

}
