package de.fettlaus.thekraken.model;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import de.fettlaus.thekraken.events.ModelListener;

public class KrakenModel extends Observable implements Model, Observer {
	public static final int BUFFER_SIZE = 20;
	ArrayList<Connection> connections;
	ModelListener connectionLostObserver;
	BlockingQueue<Message> messages;
	MessageDispatcher disp;

	public KrakenModel() {
		connections = new ArrayList<Connection>();
		messages = new ArrayBlockingQueue<Message>(BUFFER_SIZE);
	}

	@Override
	public void closeConnection(Connection con) throws IOException {
		con.close();
		connections.remove(con);
	}

	@Override
	public Connection getConnection(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Connection> getConnections() {
		return connections;
	}

	@Override
	public void newConnection(String ip, int port) throws UnknownHostException, IOException {
		final ThreadedConnection con = new ThreadedConnection(ip, port, messages);
		if (con.connect() != false) {
			con.addObserver(this);
			connections.add(con);
			// newConnectionListener.fireEvent(this);
		}

	}

	@Override
	public void subscribeConnectionLost(ModelListener lst) {
		addObserver(lst);
	}

	@Override
	public void subscribeNewMessage(ModelListener lst) {
		disp = new MessageDispatcher(messages);
		disp.addObserver(lst);
		new Thread(disp).start();
	}

	@Override
	public void synchronizeClients() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Observable o, Object arg) {
		// Connection con = (Connection) o;
		connections.remove(o);
		setChanged();
		notifyObservers();

	}

}
