package de.fettlaus.thekraken.model;

import java.io.IOException;
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

	@Override
	public void closeConnection(Connection con) {
		try {
			con.close();
		} catch (final IOException e) {
			evt.post(new NewNotificationEvent(NotificationType.CLOSING_ERROR));
		}
	}

	@Override
	public Connection getConnection(int index) {
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
		try {
			if (con.connect() != false) {
				connections.add(con);
				evt.post(new NewNotificationEvent(NotificationType.NEW_CONNECTION, ip + ":" + port));
				evt.post(connections);
			}
		} catch (final IOException e) {
			evt.post(new NewNotificationEvent(NotificationType.NO_HOST_FOUND, ip + ":" + port));
		}

	}

	@Override
	public void synchronizeClients() {
		// TODO Auto-generated method stub

	}

}
