package de.fettlaus.thekraken.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.google.common.eventbus.Subscribe;

import de.fettlaus.thekraken.events.EventBus;
import de.fettlaus.thekraken.events.NewNotificationEvent;
import de.fettlaus.thekraken.events.NewNotificationEvent.NotificationType;

public class KrakenModel implements Model {
	public static final int BUFFER_SIZE = 20;
	List<Connection> connections;
	BlockingQueue<Message> messages;
	MessageDispatcher disp;
	EventBus evt;

	public KrakenModel() {
		connections = new ArrayList<Connection>();
		messages = new ArrayBlockingQueue<Message>(BUFFER_SIZE);
		disp = new MessageDispatcher(messages);
		new Thread(disp).start();
		evt = EventBus.instance();
		evt.register(this);
	}

	@Override
	public void broadcastMessage(Message msg) {
		for (final Connection con : connections) {
			con.sendMessage(msg);
		}

	}

	/**
	 * Returns the Connection on specified index. Returns null on out of bounds
	 * or Connection not present.
	 * 
	 * @param index
	 *            Index of Connection
	 * @return Requested Connection
	 */
	@Override
	public Connection getConnection(int index) {
		if ((index < 0) || (index > (connections.size() - 1))) {
			return null;
		}
		return connections.get(index);
	}

	@Override
	public List<Connection> getConnections() {
		return connections;
	}

	@Subscribe
	public void handleClosedConnection(Connection con) {
		connections.remove(con);
		evt.post(connections);
	}

	@Override
	public void newConnection(String ip, int port) {
		final ThreadedConnection con = new ThreadedConnection(ip, port, messages);
		if (con.connect() != false) {
			connections.add(con);
			evt.post(new NewNotificationEvent(NotificationType.NEW_CONNECTION, ip + ":" + port));
			evt.post(connections);
		}

	}

	@Override
	public void synchronizeClients() {
		// TODO Auto-generated method stub

	}

}
