package de.fettlaus.thekraken.model;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import de.fettlaus.thekraken.events.ModelListener;


public class KrakenModel implements Model {
	public static final int BUFFER_SIZE = 20;
	ArrayList<Connection> connections;
	ModelListener newConnectionListener;
	ModelListener newMessageListener;
	BlockingQueue<Message> messages;
	MessageDispatcher disp;
	public KrakenModel(){
		connections = new ArrayList<Connection>();
		messages = new ArrayBlockingQueue<Message>(BUFFER_SIZE);
	}

	@Override
	public void subscribeNewConnection(ModelListener lst) {
		newConnectionListener = lst;
		
	}

	@Override
	public void subscribeNewMessage(ModelListener lst) {
		disp = new MessageDispatcher(messages,lst);
		new Thread(disp).start();
	}

	@Override
	public void subscribeNewUART(ModelListener lst) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Connection> getConnections() {
		return connections;
	}

	@Override
	public void synchronizeClients() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newConnection(String ip, int port) throws UnknownHostException, IOException {
		Connection con = new ThreadedConnection(ip, port,messages);
		if(con.connect() != false){
			connections.add(con);
			//newConnectionListener.fireEvent(this);
		}
		
	}

	@Override
	public Connection getConnection(int index) {
		// TODO Auto-generated method stub
		return null;
	}

}
