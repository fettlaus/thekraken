package de.fettlaus.thekraken.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import de.fettlaus.thekraken.events.EventBus;
import de.fettlaus.thekraken.events.NewNotificationEvent;
import de.fettlaus.thekraken.events.NewNotificationEvent.NotificationType;

public class UDPConnection implements Runnable {
	// BlockingQueue<Message> messages;
	DatagramSocket socket;
	// InetAddress address;
	// int port;
	ByteArrayOutputStream baos;
	// Semaphore socketInUse;
	// byte[] pingmessage;
	// private ConnectionBundle parent;
	DataOutputStream dos;

	public UDPConnection() {
		super();
		baos = new ByteArrayOutputStream();
	}

	public void connect() throws SocketException {
		socket = new DatagramSocket();
		dos = new DataOutputStream(baos);
	}

	@Override
	public void run() {

		final byte[] buffer = new byte[Message.HEADER_LENGTH + Message.MAX_BODY_LENGTH];
		final DataInputStream dis = new DataInputStream(new ByteArrayInputStream(buffer));
		Message msg = new KrakenMessage();
		while (true) {
			try {
				socket.receive(new DatagramPacket(buffer, buffer.length));
				msg.read(dis);
				if (msg.getType() == MessageType.PONG) {
					msg = new KrakenMessage(MessageType.STIM, msg.getTimestamp(), "");
					System.out.println("STIM!");
				}
			} catch (final Exception e) {
				EventBus.instance().post(new NewNotificationEvent(NotificationType.UDP_ERROR));
			}
		}

	}

	public synchronized void sendPing(Connection con) throws IOException {
		// create high-precision ping
		baos.reset();
		final Message ping = new KrakenMessage(MessageType.PING, TimeKeeper.time(), "");
		ping.write(dos);
		final byte[] out = baos.toByteArray();
		socket.send(new DatagramPacket(out, out.length, con.getAddress(), con.getPort()));

	}

}
