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
		view.addLogmessage("000", "Von", "Text!");
		view.addUARTMessage("00", "Von", "Auch Text!");
		String[] st = {
				"Peter","Andreas","Annika","Robäääärt"
		};
		int tmp = view.getCurrentClientIndex();
		String out;
		if(tmp<0){
			out = "Nichts";
		}else{
			out = st[tmp];
		}
		view.addLogmessage("001", "Mir", "Ausgewählt: "+out);
		view.setClients(st);
	}

}
