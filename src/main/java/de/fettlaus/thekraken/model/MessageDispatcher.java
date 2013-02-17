package de.fettlaus.thekraken.model;

import java.util.SortedSet;

import de.fettlaus.thekraken.events.EventBus;

public class MessageDispatcher implements Runnable {

	private final SortedSet<Message> messages;
	EventBus evt;
	int buffer_size = 0;
	boolean dispatching = true;

	public MessageDispatcher(SortedSet<Message> messages2) {
		messages = messages2;
		evt = EventBus.instance();
	}

	@Override
	public void run() {
		Message msg;
		while (true) {
			if (messages.size() > buffer_size) {
				msg = messages.first();
				messages.remove(msg);
				if(dispatching){
					evt.post(msg);
				}
			} else {
				try {
					Thread.sleep(1);
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}

	public void set_dispatching(boolean dispatching) {
		this.dispatching = dispatching;
	}

	public void set_buffer(int size) {
		buffer_size = size;
		messages.clear();
	}

}
