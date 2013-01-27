package de.fettlaus.thekraken.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BlockingQueue;

public class ThreadedConnection implements Connection, Runnable{
	Socket echoSocket = null;
    BufferedOutputStream out = null;
    BufferedInputStream in = null;
    BlockingQueue<Message> messages = null;
    String address = null;
    int port;

	public ThreadedConnection(String ip, int port, BlockingQueue<Message> messages) {
		this.address = ip;
		this.port = port;
		this.messages = messages;
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
	public void sendMessage(Message msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendPing() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean connect() throws UnknownHostException, IOException {
			echoSocket = new Socket();
            echoSocket.connect(new InetSocketAddress(this.address, this.port),4000);
            out = new BufferedOutputStream(new DataOutputStream(echoSocket.getOutputStream()));
            in = new BufferedInputStream(new DataInputStream(echoSocket.getInputStream()));

		
		// TODO Auto-generated method stub
		new Thread(this).start();
		return true;
	}

	@Override
	public void run() {
		// TODO listen on socket
		Message msg;
		while(true){
			msg = new KrakenMessage();
			try {
				in.read(msg.getHeader(), 0, 7);
				int length = msg.getLength();
				if(length > 0){
					in.read(msg.getBody(),0,length);
				}
				messages.add(msg);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
	
	private void sendMessage(Message.MessageType type, String msg){
		
	}

}
