package de.fettlaus.thekraken.events;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EventBus extends com.google.common.eventbus.AsyncEventBus {
	private static EventBus instance = new EventBus( Executors.newFixedThreadPool(1));

	public static EventBus instance() {
		return instance;
	}

	private EventBus(Executor exec) {
		super(exec);
	}

}
