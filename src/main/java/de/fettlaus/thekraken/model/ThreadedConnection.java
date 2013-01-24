package de.fettlaus.thekraken.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ThreadedConnection implements Connection, Runnable{
	Socket echoSocket = null;
    BufferedOutputStream out = null;
    BufferedInputStream in = null;
    String address = null;
    int port;

	public ThreadedConnection(String ip, int port) {
		this.address = ip;
		this.port = port;
	}

	@Override
	public String getAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPort() {
		// TODO Auto-generated method stub
		return 0;
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
	public boolean connect() {


        try {
            echoSocket = new Socket(this.address, this.port);
            out = new BufferedOutputStream(new DataOutputStream(echoSocket.getOutputStream()));
            in = new BufferedInputStream(new DataInputStream(echoSocket.getInputStream()));
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: taranis.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to: taranis.");
            System.exit(1);
        }
		
		// TODO Auto-generated method stub
		new Thread(this).start();
		return true;
	}

	@Override
	public void run() {
		// TODO listen on socket
		while(true){
/*
				in.read(msg.getBuffer(), 0, 7);
				msg.parseHeader();
				in.read(msg.getBuffer(),7,msg.getLength());
				*/
			
		}
		
		
	}
	
	private void sendMessage(Message.MessageType type, String msg){
		
	}

}
