package de.fettlaus.thekraken.events;

import de.fettlaus.thekraken.model.Model;
import de.fettlaus.thekraken.view.View;

public class ConnectClientEvent extends KrakenListener{

	public ConnectClientEvent(Model model) {
		super(model);
	}

	@Override
	public void fireEvent(View view) {
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
