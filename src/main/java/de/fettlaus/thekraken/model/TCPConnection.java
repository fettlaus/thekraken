package de.fettlaus.thekraken.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.SortedSet;
import de.fettlaus.thekraken.events.EventBus;
import de.fettlaus.thekraken.events.LostConnectionEvent;
import de.fettlaus.thekraken.events.NewNotificationEvent;
import de.fettlaus.thekraken.events.NewNotificationEvent.NotificationType;

public class TCPConnection implements Connection {
	Socket echoSocket = null;
	DataOutputStream out = null;
	DataInputStream in = null;
	SortedSet<Message> messages = null;
	InetAddress address = null;
	int port;

	public TCPConnection(InetAddress ip, int port, SortedSet<Message> messages) {
		address = ip;
		this.port = port;
		this.messages = messages;
	}

	@Override
	public void close() {
		if ((echoSocket != null) && !echoSocket.isClosed()) {
			try {
				echoSocket.close();
			} catch (final IOException e) {
				EventBus.instance().post(new NewNotificationEvent(NotificationType.CLOSING_ERROR));
			}
		}
	}

	@Override
	public void connect() throws IOException {
		echoSocket = new Socket();
		echoSocket.setTcpNoDelay(true);
		echoSocket.connect(new InetSocketAddress(address, port), 4000);
		out = new DataOutputStream(new BufferedOutputStream(echoSocket.getOutputStream()));
		in = new DataInputStream(new BufferedInputStream(echoSocket.getInputStream()));
		new Thread(this).start();
	}

	@Override
	public InetAddress getAddress() {
		return address;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public void run() {
		Message msg;
		try {
			while (true) {
				msg = new KrakenMessage(this);
				msg.read(in);
				messages.add(msg);
			}
		} catch (final IOException e) {
			EventBus.instance().post(new LostConnectionEvent(this));
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendMessage(Message msg) {
		try {
			msg.write(out);
		} catch (final IOException e) {
			EventBus.instance().post(new LostConnectionEvent(this));
		}
	}

}
