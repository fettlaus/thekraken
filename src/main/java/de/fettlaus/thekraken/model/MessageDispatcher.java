package de.fettlaus.thekraken.model;

import java.util.concurrent.BlockingQueue;

import de.fettlaus.thekraken.events.EventBus;

public class MessageDispatcher implements Runnable {

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
				EventBus.instance().post(msg);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
