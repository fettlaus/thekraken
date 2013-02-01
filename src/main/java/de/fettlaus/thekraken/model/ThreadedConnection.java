package de.fettlaus.thekraken.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.concurrent.BlockingQueue;

public class ThreadedConnection extends Observable implements Connection, Runnable {
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
	public void close() throws IOException {
		echoSocket.close();

	}

	@Override
	public boolean connect() throws UnknownHostException, IOException {
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
		boolean running = true;
		while (running) {
			msg = new KrakenMessage();
			try {
				msg.read(in);
				msg.setConnection(this);
				messages.add(msg);
			} catch (final IOException e) {
				running = false;
				setChanged();
				notifyObservers(this); // connection lost
			} catch (final ClassNotFoundException e) {
				running = false;
				e.printStackTrace();
			}
		}
	}

	@Override
	public void sendMessage(Message msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendPing() {
		// TODO Auto-generated method stub

	}

	private void sendMessage(MessageType type, String msg) {

	}

}
