package de.fettlaus.thekraken.model;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import com.google.common.eventbus.Subscribe;

import de.fettlaus.thekraken.events.EventBus;
import de.fettlaus.thekraken.events.LostConnectionEvent;
import de.fettlaus.thekraken.events.NewNotificationEvent;
import de.fettlaus.thekraken.events.NewNotificationEvent.NotificationType;

public class KrakenModel implements Model {
	public static final int BUFFER_SIZE = 100;
	List<Connection> connections;
	SortedSet<Message> messages;
	MessageDispatcher disp;
	EventBus evtbus;
	UDPConnection udp;

	public KrakenModel() throws SocketException {
		connections = new ArrayList<Connection>();
		messages = Collections.synchronizedSortedSet(new TreeSet<Message>());
		disp = new MessageDispatcher(messages);
		new Thread(disp).start();
		evtbus = EventBus.instance();
		evtbus.register(this);
		udp = new UDPConnection();
		udp.connect();
		new Thread(udp).start();
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
	public void handleClosedConnection(LostConnectionEvent evt) {
		final Connection connection = evt.getConnection();
		connection.close();
		connections.remove(connection);
		evtbus.post(connections);
	}

	@Override
	public void newConnection(String ip, int port) {
		try {
			final Connection con = new TCPConnection(InetAddress.getByName(ip), port, messages);
			try {
				con.connect();
				connections.add(con);
				evtbus.post(new NewNotificationEvent(NotificationType.NEW_CONNECTION, ip + ":" + port));
				evtbus.post(connections);
			} catch (final IOException e) {
				evtbus.post(new NewNotificationEvent(NotificationType.CANT_CONNECT_TO_HOST, ip + ":" + port));
				con.close();
			}
		} catch (final UnknownHostException e1) {
			evtbus.post(new NewNotificationEvent(NotificationType.NO_HOST_FOUND));
		}

	}

	@Override
	public void synchronizeClients() {
		TimeKeeper.reset();
		for (final Connection con : connections) {
			try {
				for(int i = 0;i<21;i++)
					udp.sendPing(con);
			} catch (final IOException e) {
				evtbus.post(new NewNotificationEvent(NotificationType.CANT_SEND_UDP));
			}
		}

	}

}
