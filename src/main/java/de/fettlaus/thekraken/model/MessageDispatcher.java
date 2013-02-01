package de.fettlaus.thekraken.model;

import java.util.Observable;
import java.util.concurrent.BlockingQueue;

public class MessageDispatcher extends Observable implements Runnable {

	private final BlockingQueue<Message> messages;

	public MessageDispatcher(BlockingQueue<Message> messages) {
		this.messages = messages;
	}

	@Override
	public void run() {
		Message msg;
		while (true) {
			try {
				msg = messages.take();
				setChanged();
				notifyObservers(msg);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
