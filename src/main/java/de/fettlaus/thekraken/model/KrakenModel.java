package de.fettlaus.thekraken.model;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.google.common.eventbus.Subscribe;

import de.fettlaus.thekraken.events.EventBus;
import de.fettlaus.thekraken.events.ModelListener;

public class KrakenModel implements Model {
	public static final int BUFFER_SIZE = 20;
	List<Connection> connections;
	ModelListener connectionLostObserver;
	BlockingQueue<Message> messages;
	MessageDispatcher disp;

	public KrakenModel() {
		connections = new ArrayList<Connection>();
		messages = new ArrayBlockingQueue<Message>(BUFFER_SIZE);
		disp = new MessageDispatcher(messages);
		new Thread(disp).start();
		EventBus.instance().register(this);
	}

	@Override
	@Subscribe
	public void closeConnection(Connection con) throws IOException {
		con.close();
		connections.remove(con);
		EventBus.instance().post(connections);
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
			connections.add(con);
			EventBus.instance().post(connections);
			// newConnectionListener.fireEvent(this);
		}

	}

	@Override
	public void synchronizeClients() {
		// TODO Auto-generated method stub

	}

}
