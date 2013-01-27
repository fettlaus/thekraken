package de.fettlaus.thekraken.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import de.fettlaus.thekraken.model.Connection;
import de.fettlaus.thekraken.model.Model;
import de.fettlaus.thekraken.view.View;

public class ConnectClientEvent extends ViewListener implements ActionListener{

	public ConnectClientEvent(Model model, View view) {
		super(model, view);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(arg0.getSource());
		int port;
		String host;
		try{
			port = Integer.parseInt(view.getNewClientPort());
			host = view.getNewClientIP();
			model.newConnection(host, port);
			view.setNotification("Connection established!");
		}catch(NumberFormatException e){
			view.setNotification("Wrong Port specified");
		}catch (UnknownHostException e) {
			view.setNotification("Can't find host!");
		}catch (IOException e){
			view.setNotification("Can't establish connection");
		}
		List<Connection> connections = model.getConnections();
		int connections_size = connections.size();
		String[] connections_txt = new String[connections_size];
		System.out.println(connections_size);
		for(int i = 0;i<connections_size;++i){
			Connection this_con = connections.get(i);
			connections_txt[i] = this_con.getAddress()+":"+this_con.getPort();
		}
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
		view.setClients(connections_txt);
		
	}

}
