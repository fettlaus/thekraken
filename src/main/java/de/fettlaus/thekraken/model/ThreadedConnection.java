package de.fettlaus.thekraken.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.BlockingQueue;

import de.fettlaus.thekraken.events.EventBus;
import de.fettlaus.thekraken.events.NewNotificationEvent;
import de.fettlaus.thekraken.events.NewNotificationEvent.NotificationType;

public class ThreadedConnection implements Connection, Runnable {
	Socket echoSocket = null;
	DataOutputStream out = null;
	DataInputStream in = null;
	BlockingQueue<Message> messages = null;
	String address = null;
	int port;

	public ThreadedConnection(String ip, int port, BlockingQueue<Message> messages) {
		address = ip;
		this.port = port;
		this.messages = messages;
	}

	@Override
	public void close() {
		try {
			echoSocket.close();
			EventBus.instance().post(this); // connection lost
		} catch (IOException e) {
			EventBus.instance().post(new NewNotificationEvent(NotificationType.CLOSING_ERROR));
		}

	}

	@Override
	public boolean connect() throws IOException, SocketTimeoutException {
		echoSocket = new Socket();
		echoSocket.setTcpNoDelay(true);
		echoSocket.connect(new InetSocketAddress(address, port), 4000);
		out = new DataOutputStream(new BufferedOutputStream(echoSocket.getOutputStream()));
		in = new DataInputStream(new BufferedInputStream(echoSocket.getInputStream()));

		// TODO Auto-generated method stub
		new Thread(this).start();
		return true;
	}

	@Override
	public String getAddress() {
		return echoSocket.getInetAddress().toString();
	}

	@Override
	public int getPort() {
		return echoSocket.getPort();
	}

	@Override
	public void run() {
		// TODO listen on socket
		Message msg;
		try {
			while (true) {
				msg = new KrakenMessage(this);
				msg.read(in);
				messages.add(msg);
			}
		} catch (final IOException e) {
			if ((echoSocket != null) && !echoSocket.isClosed()) {
				close();
			}

		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendMessage(Message msg) {
		try {
			msg.write(out);
		} catch (final IOException e) {
			if ((echoSocket != null) && !echoSocket.isClosed()) {
				close();
			}
		}
	}

	@Override
	public void sendPing() {
		// TODO Auto-generated method stub

	}

}
