package de.fettlaus.thekraken.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import de.fettlaus.thekraken.events.ModelListener;


public class KrakenModel implements Model {
	ArrayList<Connection> connections;
	ModelListener newConnectionListener;
	BlockingQueue<Message> messages;
	public KrakenModel(){
		connections = new ArrayList<Connection>();
	}

	@Override
	public void subscribeNewConnection(ModelListener lst) {
		newConnectionListener = lst;
		
	}

	@Override
	public void subscribeNewMessage(ModelListener lst) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void subscribeNewUART(ModelListener lst) {
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
	public void newConnection(String ip, int port) {
		Connection con = new ThreadedConnection(ip, port);
		if(con.connect() != false){
			connections.add(con);
			newConnectionListener.fireEvent(this);
		}else{
		}
		
	}

	@Override
	public Connection getConnection(int index) {
		// TODO Auto-generated method stub
		return null;
	}

}
