package de.fettlaus.thekraken.events;

import java.util.concurrent.Executor;

public class EventBus extends com.google.common.eventbus.AsyncEventBus {
	private static EventBus instance = new EventBus(new Executor() {
		
		@Override
		public void execute(Runnable r) {
			new Thread(r).start();
			
		}
	});

	public static EventBus instance() {
		return instance;
	}

	private EventBus(Executor exec) {
		super(exec);
	}

}
