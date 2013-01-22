package de.fettlaus.thekraken.model;

import java.net.InetAddress;
import java.util.List;

import de.fettlaus.thekraken.events.KrakenListener;


public class KrakenModel implements Model {

	@Override
	public void subscribeNewConnection(KrakenListener lst) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void subscribeNewMessage(KrakenListener lst) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void subscribeNewUART(KrakenListener lst) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Connection> getConnections() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void synchronizeClients() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newConnection(InetAddress ip, int port) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Connection getConnection(int index) {
		// TODO Auto-generated method stub
		return null;
	}

}
